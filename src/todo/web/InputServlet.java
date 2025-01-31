package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dto.Member;


@WebServlet("/member/Input")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public InputServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //vo‚Ìì¬
        Member dto = new Member();
        //V‹K“o˜^‚Å‚ ‚é‚±‚Æ‚ğ”»•Ê‚·‚é‚½‚ßid=0‚Æ‚µ‚Ä‚¢‚éB
        dto.setId(0);
        //
        request.setAttribute("dto", dto);

        //Ú×‰æ–Ê‚ğ•\¦‚·‚é
        RequestDispatcher rd = request.getRequestDispatcher("/register_detail");
        rd.forward(request, response);
    }


}
