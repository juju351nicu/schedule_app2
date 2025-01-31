package todo.web.todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.entity.Member;

/**
 * todoAdd.jspに対応するServlet。
 *
 * @author d5riv
 * @since 2020/11/29
 */
@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public TodoAddServlet()
    {
        super();
    }

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 確認画面からredirectされたときの場合はsessionで渡される。
        HttpSession session = request.getSession();
        TodoDto dto = (TodoDto) session.getAttribute("todo");
        session.removeAttribute("todo");

        // Top画面から来たときの場合
        if (dto == null)
        {
            // voの作成
            dto = new TodoDto();
            // 新規登録であることを判別するためid=0としている。
//            dto.setId(0);
        }

        request.setAttribute("dto", dto);

        // 詳細画面を表示する
        RequestDispatcher rd = request.getRequestDispatcher("/todoAdd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String date_from = request.getParameter("date_from");
        String date_to = request.getParameter("date_to");
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        String done_flag = request.getParameter("done_flag");
        int userId = ((Member) request.getSession().getAttribute("user")).getId();

        TodoDto dto = new TodoDto(id, date_from, date_to, title, detail, done_flag, userId);

        // 入力チェック
        List<String> errMsgs = TodoCheck.todoValueCheck(dto);
        if (!errMsgs.isEmpty())
        {
            request.setAttribute("errorMessages", errMsgs);
            request.setAttribute("dto", dto);
            // 詳細画面を再表示する
            RequestDispatcher rd = request.getRequestDispatcher("/todoAdd.jsp");
            rd.forward(request, response);
            return;
        }

        request.getSession().setAttribute("todo", dto);
        response.sendRedirect("./add_confirm"); // PRGパターン（Post>Redirt>Get）
    }
}
