package todo.web;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.MemberDAO;
import todo.entity.Member;
import utils.DateUtils;

/**
 * Servlet implementation class MemberAdd
 */
@WebServlet("/member/M_register")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddServlet() {
        super();
    }
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name_sei = request.getParameter("name_sei");
		String name_mei = request.getParameter("name_mei");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int delete_flag = Integer.parseInt(request.getParameter("delete_flag"));
		String registered_date = request.getParameter("registered_date");
		String updated_date = request.getParameter("updated_date");
		String last_login = request.getParameter("last_login");

		//String型をTimeStamp型に変換する
		Timestamp registered_date2 = DateUtils.sqlTimestamp(registered_date);
		Timestamp updated_date2 = DateUtils.sqlTimestamp(updated_date);
		Timestamp last_login2 = DateUtils.sqlTimestamp(last_login);

		Member dto = new Member();
		dto.setId(id);
		dto.setName_sei(name_sei);
		dto.setName_mei(name_mei);
		dto.setLogin_id(login_id);
		dto.setPassword(password);
		dto.setDelete_flag(delete_flag);
		dto.setRegistered_date(registered_date2);
		dto.setUpdated_date(updated_date2);
		dto.setLast_login(last_login2);

		System.out.println("doPost Name_sei=" + name_sei);
		try (MemberDAO dao = new MemberDAO()) {
			int result = dao.m_register(dto);
			System.out.println("返ってきたresultの数は"+ result + "です");
		} catch (Exception e) {
			e.getStackTrace();
			throw new ServletException(e);
		}
		String message = "新規メンバー登録が完了しました";
		setMessage(request, message);
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("/memberAddConfirm.jsp");
		rd.forward(request, response);
	}

	private void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}

}
