package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.entity.Todo;


@WebServlet("/member/T_updetail")
public class TodoUpdateDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TodoUpdateDetailServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");

		Todo dto = new Todo();
		try (TodoDAO dao = new TodoDAO()){
			int id = Integer.parseInt(paramId);
			dto = dao.t_updetail(id);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		request.setAttribute("dto",dto);
		RequestDispatcher rd = request.getRequestDispatcher("/todoUpdateDetail.jsp");
		rd.forward(request,response);
	}



}
