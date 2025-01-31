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

import todo.dao.TodoDAO;
import todo.entity.Todo;

/**
 * todoUpdate.jspに対応するサーブレット
 *
 * @author PC0010
 * @since 11月30日
 */
@WebServlet("/todo/update")
public class TodoUpdateServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public TodoUpdateServlet()
    {
        super();
    }

    /**
     * Todoクラスのidを使い、詳細画面に表示する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 確認画面からredirectされたときの場合はsessionで渡される。
        HttpSession session = request.getSession();
        TodoDto dtos = (TodoDto) session.getAttribute("todo");

        // Top.jspから来た時
        if (dtos == null)
        {
            int id = Integer.parseInt(request.getParameter("id"));
            Todo entity = new Todo();
            try (TodoDAO dao = new TodoDAO())
            {
                entity = dao.todoDetail(id);
            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
            // todoUpdate.jspにAttributeを渡す
            session.removeAttribute("todo");
            request.setAttribute("dto", entity);
        }
        // 確認画面からredirectされたときの場合はdtosを渡す。
        else
        {
            request.setAttribute("dto", dtos);
        }
        // 詳細画面を表示する
        RequestDispatcher rd = request.getRequestDispatcher("/todoUpdate.jsp");
        rd.forward(request, response);
    }

    /**
     * todoUpdate.jspで更新メソッドを呼び出すdoPostメソッド
     *
     * @author PC0010
     * @since 11月30日
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        // HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        // int id = ((Member) session.getAttribute("user")).getId();
        String date_from = request.getParameter("date_from");
        String date_to = request.getParameter("date_to");
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        // boolean done_flag = Boolean.getBoolean(request.getParameter("done_flag"));
        String done_flag = request.getParameter("done_flag");

        TodoDto dto = new TodoDto(id, date_from, date_to, title, detail, done_flag, id);

        // 入力チェック
        List<String> errMsgs = TodoCheck.todoValueCheck(dto);
        if (!errMsgs.isEmpty())
        {
            request.setAttribute("errorMessages", errMsgs);
            request.setAttribute("dto", dto);
            // 詳細画面を再表示する
            RequestDispatcher rd = request.getRequestDispatcher("/todoUpdate.jsp");
            rd.forward(request, response);
            return;
        }

        request.getSession().setAttribute("todo2", dto);
        response.sendRedirect("./update_confirm"); // PRGパターン（Post>Redirt>Get）
    }

}
