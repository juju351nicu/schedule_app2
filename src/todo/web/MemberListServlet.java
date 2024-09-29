package todo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.MemberDAO;
import todo.entity.Member;


@WebServlet("/member/M_search")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberListServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (MemberDAO dao = new MemberDAO()){
        	//メンバーのリストを一覧で取得し、リクエスト属性に格納する
            List<Member> list = dao.SearchList();

            request.setAttribute("MemberList",list);
        }catch(Exception e) {
            throw new ServletException(e);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/memberList.jsp");
        rd.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
