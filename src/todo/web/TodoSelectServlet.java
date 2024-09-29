package todo.web;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.entity.Todo;
import utils.DateUtils;

/**
 * Servlet implementation class T_selectServlet
 */
@WebServlet("/member/T_select")
public class TodoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TodoSelectServlet() {
        super();
    }
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		String date_from = request.getParameter("date_from");

		Date date_from2 = DateUtils.datesql(date_from);
		/*// 入力チェック
		LocalDate localDate = null;
		try
		{
		    localDate = DateUtils.StrToLocalDate(date_from);
		}
		catch (DateTimeParseException e)
		{
		    request.setAttribute("error", "XXXXXXX"); // TODO
		    request.getRequestDispatcher("/top.jsp").forward(request, response);
		}
		*/
		Todo dto = new Todo();
		dto.setTitle(title);
		//dto.setDate_from(DateUtils.LocalDateToSQlDate(localDate));
		dto.setDate_from(date_from2);
		List<Todo> list = new ArrayList<>();

		try(TodoDAO dao = new TodoDAO()){

			list = dao.todoSearch(dto);
			request.setAttribute("todoList",list);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/top.jsp");
		rd.forward(request,response);

	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
