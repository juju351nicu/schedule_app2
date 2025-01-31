package todo.web.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.entity.Todo;

/**
 * Servlet implementation class TodoUpdateDone_flagServlet
 * done_flagのfalseをtrueに変更するメソッドを呼び出すサーブレット
 */
@WebServlet("/todo/doneFlag")
public class TodoUpdateDone_flagServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoUpdateDone_flagServlet()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        Todo entity = new Todo();
        entity.setId(id);

        try (TodoDAO dao = new TodoDAO())
        {
            dao.todoUpdateDone_flag(entity);

        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
        response.sendRedirect("../todo/Top");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
