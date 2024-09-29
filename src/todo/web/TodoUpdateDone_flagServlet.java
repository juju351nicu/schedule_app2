package todo.web;

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
 */
@WebServlet("/member/T_Done_flag")
public class TodoUpdateDone_flagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoUpdateDone_flagServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Todo dto = new Todo();
			dto.setId(id);

			try(TodoDAO dao = new TodoDAO()){
				dao.todoupdateflag(dto);

			}catch(Exception e) {
				throw new ServletException(e);
			}
			response.sendRedirect("../todo/Top");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
