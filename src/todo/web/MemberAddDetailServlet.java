package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.entity.Member;

/**
 * Servlet implementation class MemberInput
 */
@WebServlet("/member/M_input")
public class MemberAddDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddDetailServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //voの作成
        Member dto = new Member();
        //新規登録であることを判別するためid=0としている。
        dto.setId(0);
        //
        request.setAttribute("dto", dto);

        //詳細画面を表示する
        RequestDispatcher rd = request.getRequestDispatcher("/memberAddDetail.jsp");
        rd.forward(request, response);
    }


}
