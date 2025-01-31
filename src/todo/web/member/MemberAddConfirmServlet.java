package todo.web.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dao.MemberDAO;
import todo.entity.Member;

/**
 * Servlet implementation class MemberAddConfirmServlet
 */
@WebServlet("/member/add_confirm")
public class MemberAddConfirmServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddConfirmServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        MemberDto dto = (MemberDto) session.getAttribute("member");
        session.removeAttribute("member");

        request.setAttribute("dto", dto);
        RequestDispatcher rd = request.getRequestDispatcher("/memberAddConfirm.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String type = request.getParameter("button");
        int id = Integer.parseInt(request.getParameter("id"));
        String name_sei = request.getParameter("name_sei");
        String name_mei = request.getParameter("name_mei");
        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");
        String delete_flag = (request.getParameter("delete_flag"));

        // C³‚Ìê‡
        if (type.equals("C³"))
        {
            MemberDto dto = new MemberDto();
            dto.setId(id);
            dto.setName_sei(name_sei);
            dto.setName_mei(name_mei);
            dto.setLogin_id(login_id);
            dto.setPassword(password);
            dto.setDelete_flag(delete_flag);

            request.getSession().setAttribute("member", dto);
            response.sendRedirect("./add");
        }
        // “o˜^‚Ìê‡
        else
        {
            Member entity = new Member();
            entity.setId(id);
            entity.setName_sei(name_sei);
            entity.setName_mei(name_mei);
            entity.setLogin_id(login_id);
            entity.setPassword(password);
            entity.setDelete_flag(Boolean.getBoolean(delete_flag));

            try (MemberDAO dao = new MemberDAO())
            {
                int result = dao.memberRegister(entity);
                System.out.println("•Ô‚Á‚Ä‚«‚½result‚Ì”‚Í" + result + "‚Å‚·");

            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
            String message = "V‹K‚ÉMember‚Ì" + id + name_sei + name_mei + login_id + password + delete_flag + "‚ğ“o˜^‚µ‚Ü‚µ‚½B";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("/Member/todo/Top"); // PRGƒpƒ^[ƒ“iPost>Redirt>Getj
        }
    }
}
