package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import todo.dao.MemberDAO;
import todo.entity.Member;
import todo.web.M_registerServlet;

class registerTest extends M_registerServlet{

	@Test
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int id = 56;
		String name_sei = "菅";
		String name_mei = "義偉";
		String login_id = "user88";
		String password = "password";


		Member dto = new Member();
		dto.setId(id);
		dto.setName_sei(name_sei);
		dto.setName_mei(name_mei);
		dto.setLogin_id(login_id);
		dto.setPassword(password);


		
		try (MemberDAO dao = new MemberDAO()) {
			int result = dao.m_register(dto);
			System.out.println("返ってきたresultの数は"+ result + "です");
		} catch (Exception e) {
			e.getStackTrace();
			throw new ServletException(e);
		}
	}
}
