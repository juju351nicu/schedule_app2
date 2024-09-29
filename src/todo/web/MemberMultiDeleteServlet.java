package todo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.MemberDAO;

/**
 * Servlet implementation class MemberMultiDelete
 */
@WebServlet("/member/M_delete")
public class MemberMultiDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMultiDeleteServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//下記だとStringなのでint型に変える
		String[] id = request.getParameterValues("id");
		System.out.println(id);

		for(int i=0;i<id.length;i++) {
			System.err.println("チェックボックスから" + id[i] + "渡されました");
		}



		try(MemberDAO dao = new MemberDAO()){
			int result =  dao.m_delete(id);
			System.out.println("memberDAOから返ってきた結果は" + result + "です");
		}catch(Exception e) {
			throw new ServletException(e);
		}

		response.sendRedirect("../member/M_search");
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}