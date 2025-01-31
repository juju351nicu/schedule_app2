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
import utils.DateUtils;

/**
 * todoAdd.jspに対応するServlet。
 *
 * @author d5riv
 * @since 2020/11/29
 */
@WebServlet("/todo/add_confirm")
public class TodoAddConfirmServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public TodoAddConfirmServlet()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        TodoDto dto = (TodoDto) session.getAttribute("todo");
        session.removeAttribute("todo");

        request.setAttribute("dto", dto);
        RequestDispatcher rd = request.getRequestDispatcher("/todoAddConfirm.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String type = request.getParameter("button");
        int id = Integer.parseInt(request.getParameter("id"));
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
            response.sendRedirect("./add"); // PRGパターン（Post>Redirt>Get）
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
            entity.setDone_flag(Boolean.parseBoolean(done_flag));
            entity.setUserId(userId);

            System.out.println(entity.getUserId());
            System.out.println(entity.getDate_to());

            try (TodoDAO dao = new TodoDAO())
            {
                dao.todoRegister(entity);
            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
            String message = "新規にTodoの" + id + date_from + date_to + title + detail + done_flag + "を追加しました。";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("./Top"); // PRGパターン（Post>Redirt>Get）
        }
    }
}
