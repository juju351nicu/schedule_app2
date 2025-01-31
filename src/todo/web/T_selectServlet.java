package todo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.dto.Todo;

/**
 * Servlet implementation class T_selectServlet
 */
@WebServlet("/member/T_select")
public class T_selectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public T_selectServlet() {
        super();
    }
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		String date_from = request.getParameter("date_from");

		Todo dto = new Todo();
		dto.setTitle(title);
		dto.setDate_from(date_from);
		List<Todo> list = new ArrayList<>();

		try(TodoDAO dao = new TodoDAO()){

			list = dao.todoSearch(dto);
			request.setAttribute("selectList",list);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/t_search.jsp");
		rd.forward(request,response);

	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
