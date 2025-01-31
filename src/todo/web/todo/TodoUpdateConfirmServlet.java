package todo.web.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dao.TodoDAO;
import todo.entity.Member;
import todo.entity.Todo;
import utils.BooleanUtils;
import utils.DateUtils;

/**
 * todoUpdate.jspに対応するServlet。
 *
 * @author d5riv
 * @since 2020/11/29
 */
@WebServlet("/todo/update_confirm")
public class TodoUpdateConfirmServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public TodoUpdateConfirmServlet()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        TodoDto dto = (TodoDto) session.getAttribute("todo2");
        // session.removeAttribute("todo2");
        request.setAttribute("dto", dto);
        RequestDispatcher rd = request.getRequestDispatcher("/todoUpdateConfirm.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String type = request.getParameter("button");
        // int id = Integer.parseInt(request.getParameter("id"));
        int id = ((TodoDto) session.getAttribute("todo2")).getId();
        String date_from = request.getParameter("date_from");
        String date_to = request.getParameter("date_to");
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        String done_flag = request.getParameter("done_flag");
        int userId = ((Member) request.getSession().getAttribute("user")).getId();

        // 修正の場合
        if (type.equals("修正"))
        {
            TodoDto dto = new TodoDto();
            dto.setId(id);
            dto.setDate_from(date_from);
            dto.setDate_to(date_to);
            dto.setTitle(title);
            dto.setDetail(detail);
            dto.setDone_flag(done_flag);
            dto.setUserId(userId);
            request.getSession().setAttribute("todo", dto);
            response.sendRedirect("./update"); // PRGパターン（Post>Redirt>Get）
        }
        // 登録の場合
        else
        {
            Todo entity = new Todo();
            entity.setId(id);
            entity.setDate_from(DateUtils.datesql(date_from));
            entity.setDate_to(DateUtils.datesql(date_to));
            entity.setTitle(title);
            entity.setDetail(detail);
            // entity.setDone_flag(Boolean.getBoolean(done_flag));
            entity.setDone_flag(BooleanUtils.sqlboolean(done_flag));
            entity.setUserId(userId);

            System.out.println(entity.getUserId());
            System.out.println(entity.getDate_to());

            try (TodoDAO dao = new TodoDAO())
            {
                dao.todoUpdate(entity);
            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
            String message = "Todoの" + id + date_from + date_to + title + detail + done_flag + "を更新しました。";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("./Top"); // PRGパターン（Post>Redirt>Get）
        }
    }
}
