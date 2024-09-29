package todo.web;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.entity.Todo;
import utils.BooleanUtils;
import utils.DateUtils;


@WebServlet("/member/T_update")
public class TodoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TodoUpdateServlet() {
        super();
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			//String[] users = request.getParameterValues("users");
			int id = Integer.parseInt(request.getParameter("id"));
			String date_from = request.getParameter("date_from");
			String date_to = request.getParameter("date_to");
			String title = request.getParameter("title");
			String detail = request.getParameter("detail");
			int done_flag = Integer.parseInt(request.getParameter("done_flag"));
			int userId = Integer.parseInt(request.getParameter("userId"));

			Date date_from2 = DateUtils.datesql(date_from);
			Date date_to2 = DateUtils.datesql(date_to);
			Boolean done_flag2 = BooleanUtils.sqlboolean(done_flag);



			Todo dto = new Todo();
			dto.setId(id);
			dto.setDate_from(date_from2);
			dto.setDate_to(date_to2);
			dto.setTitle(title);
			dto.setDetail(detail);
			dto.setDone_flag(done_flag2);
			dto.setUserId(userId);

			try(TodoDAO dao = new TodoDAO()){
				dao.t_update(dto);


			}catch(Exception e) {
				throw new ServletException(e);
			}
			String message = "更新しました";
			setMessage(request,message);
			request.setAttribute("dto", dto);
			RequestDispatcher rd = request.getRequestDispatcher("/todoUpdateConfirm.jsp");
			rd.forward(request, response);
	}

    private void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);

	}



}
