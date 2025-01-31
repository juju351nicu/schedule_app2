package todo.web.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.entity.Todo;

/**
 * Servlet implementation class TodoDetailReadOnlyServlet
 */
@WebServlet("/todo/readOnly")
public class TodoDetailReadOnlyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoDetailReadOnlyServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String paramId = request.getParameter("id");

        Todo entity = new Todo();
        try (TodoDAO dao = new TodoDAO())
        {
            int id = Integer.parseInt(paramId);
            entity = dao.todoDetail(id);
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
        request.setAttribute("dto", entity);
        RequestDispatcher rd = request.getRequestDispatcher("/todoDetailReadOnly.jsp");
        rd.forward(request, response);
    }

}
