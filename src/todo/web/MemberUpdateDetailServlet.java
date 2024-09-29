package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.MemberDAO;
import todo.entity.Member;

/**
 * Servlet implementation class M_updetailServlet
 */
@WebServlet("/member/updetail")
public class MemberUpdateDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberUpdateDetailServlet() {
        super();
    }
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");

		//
		Member dto;
		try {
			@SuppressWarnings("resource")
			MemberDAO dao = new MemberDAO();

			int id = Integer.parseInt(paramId);

			dto = dao.m_updetail(id);
			System.out.println(dto);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		request.setAttribute("dto",dto);
		RequestDispatcher rd = request.getRequestDispatcher("/memberUpdateDetail.jsp");
		rd.forward(request,response);
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
