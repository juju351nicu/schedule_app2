package todo.web.top;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.criteria.TodoCriteria;
import todo.dao.MemberDAO;
import todo.dao.TodoDAO;
import todo.entity.Member;
import todo.entity.Todo;
import utils.DateUtils;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/todo/Top")
public class TopServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet()
    {
        super();
    }

    /**
     * 初期表示処理。
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 他の画面からredirectされたときの場合、messageがsessionで渡されている可能性がある。
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        session.removeAttribute("message");
        session.removeAttribute("member");
        request.setAttribute("message", message);

        // ログインの際に取得したメンバー情報をMember userに格納する
        Member user = (Member) request.getSession().getAttribute("user");
        try (MemberDAO dao = new MemberDAO())
        {
            List<Todo> todoSummary = dao.getTodoSummary(user.getId());

            List<TodoDto> dtos = new ArrayList<>();
            for (Todo todo : todoSummary)
            {
                TodoDto dto = new TodoDto();
                dto.setId(todo.getId());
                dto.setDate_from(DateUtils.SQLDateToLocalDate(todo.getDate_from()));
                dto.setDate_to(DateUtils.SQLDateToLocalDate(todo.getDate_to()));
                dto.setTitle(todo.getTitle());
                dto.setDetail(todo.getDetail());
                dto.setDone_flag(todo.isDone_flag());
                dto.setUserId(todo.getUserId());

                long day = ChronoUnit.DAYS.between(dto.getDate_from(), dto.getDate_to());
                System.out.println(String.valueOf(day) + "日間");
                dto.setRemainingDays(day);
                dtos.add(dto);
            }

            request.setAttribute("todoList", dtos);
            request.setAttribute("notDone", "");
            request.getRequestDispatcher("/top.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 検索処理。
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String date_from = request.getParameter("date_from");
        String date_to = request.getParameter("date_to");
        String not_done = request.getParameter("not_done");
        String done = request.getParameter("done");

        System.out.printf("[parameters] title=%s, date_from=%s, date_to=%s, not_done=%s, done=%s\n", title, date_from, date_to, not_done, done);

        List<String> messages = new ArrayList<>();

        // 入力チェック
        if (title.length() > 256)
        {
            messages.add("タイトルの長さが文字数制限をオーバーしています");
        }
        if (date_from.length() > 11)
        {
            messages.add("date_fromの日付の文字数が制限をオーバーしています");
        }
        if (date_to.length() > 11)
        {
            messages.add("date_toの日付の文字数が制限をオーバーしています");
        }
        if (date_to != null && !date_to.isEmpty() && !date_to.matches("\\d{4}-\\d{2}-\\d{2}"))
        {
            messages.add("date_toの日付の書式が違います");
        }
        if (date_from.length() > 11)
        {
            messages.add("date_fromの日付の文字数が制限をオーバーしています");
        }
        if (date_from != null && !date_from.isEmpty() && !date_from.matches("\\d{4}-\\d{2}-\\d{2}"))
        {
            messages.add("date_toの日付の書式が違います");
        }
//        if (done != null && not_done != null)
//        {
//            messages.add("完了未完了の両方にチェックされています。");
//        }

        if (done == null && not_done == null)
        {
            messages.add("どちらかをチェックしてください。");
        }

        // ..
        if (!messages.isEmpty())
        {
            request.setAttribute("errorMessages", messages);
            RequestDispatcher rd = request.getRequestDispatcher("/top.jsp");
            rd.forward(request, response);
            return;
        }

        try (TodoDAO dao = new TodoDAO())
        {

            TodoCriteria criteria = new TodoCriteria();
            criteria.setTitle(title);
            criteria.setDate_from(date_from.isEmpty() ? null : DateUtils.datesql(date_from));
            criteria.setDate_to(date_to.isEmpty() ? null : DateUtils.datesql(date_to));
            // 下記のIf文で完了未完了を振り分けている。
            if (not_done != null)
            {
                criteria.setDone(true);
            }
            if (done != null)
            {
                criteria.setDone(false);
            }
            System.out.println(criteria.getDone());

            /*
             * if(not_done.equals("${notDone}")) {
             * entity.setDone_flag(BooleanUtils.sqlboolean("0")); }
             * if(done.equals("${done}")){
             * entity.setDone_flag(BooleanUtils.sqlboolean("1")); }
             * if(not_done.equals("${notDone}") && done.equals("${done}")){
             * entity.setDone_flag(BooleanUtils.sqlboolean("0")); }
             */

            List<Todo> list;
            String message;
            // date_from,date_to,ともに入力されていた場合はtodoSelectメソッドを呼び出す。
            list = dao.todoSelectByTitleAndFromTo(criteria);

            if (date_from != null && !date_from.isEmpty() && date_to != null && !date_to.isEmpty())
            {
                message = ("Date_fromからDate_toまでの一覧情報が表示されました");
            }
            else if (date_to != null && !date_to.isEmpty())
            {
                message = ("過去からDate_toまでの一覧情報が表示されました");
            }
            else if (date_from != null && !date_from.isEmpty())
            {
                message = ("Date_Fromから現在までの一覧情報が表示されました");
            }
            // Titleだけが入力されており、他の項目が未入力の場合
            else
            {
                message = (title + "に該当する項目が呼び出されました");
            }

            List<TodoDto> dtos = new ArrayList<>();
            for (Todo todo : list)
            {
                LocalDate from = DateUtils.SQLDateToLocalDate(todo.getDate_from());
                LocalDate to = DateUtils.SQLDateToLocalDate(todo.getDate_to());
                TodoDto dto = new TodoDto(todo.getId(), from, to, todo.getTitle(), todo.getDetail(), todo.isDone_flag(),
                        todo.getUserId(), ChronoUnit.DAYS.between(from, to));
                dtos.add(dto);
            }

            // JSPにデータを渡す
            request.setAttribute("message", message);
            request.setAttribute("todoList", dtos);
            request.setAttribute("searchTitle", title);
            request.setAttribute("searchDateFrom", date_from);
            request.setAttribute("searchDateTo", date_to);
            request.setAttribute("notDone", not_done);
            request.setAttribute("done", done);
            RequestDispatcher rd = request.getRequestDispatcher("/top.jsp");
            rd.forward(request, response);
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
}
