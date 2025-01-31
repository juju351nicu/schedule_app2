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
 * Servlet implementation class MemberUpdateConfirmServlet
 */
@WebServlet("/member/update_confirm")
public class MemberUpdateConfirmServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateConfirmServlet()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        MemberDto dto = (MemberDto) session.getAttribute("member");
        // session.removeAttribute("member");

        request.setAttribute("dto", dto);
        RequestDispatcher rd = request.getRequestDispatcher("/memberUpdateConfirm.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String type = request.getParameter("button");
        int id = ((MemberDto) session.getAttribute("member")).getId();
        // int id = Integer.parseInt(request.getParameter("id"));
        String name_sei = request.getParameter("name_sei");
        String name_mei = request.getParameter("name_mei");
        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");
        String delete_flag = request.getParameter("delete_flag");

        // 修正の場合
        if (type.equals("修正"))
        {
            MemberDto dto = new MemberDto();
            dto.setId(id);
            dto.setName_sei(name_sei);
            dto.setName_mei(name_mei);
            dto.setLogin_id(login_id);
            dto.setPassword(password);
            dto.setDelete_flag(delete_flag);

            request.getSession().setAttribute("member", dto);
            response.sendRedirect("./update");
        }
        // 更新の場合
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
                dao.memberUpdate(entity);

            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
            String message = "新規にMemberの" + id + name_sei + name_mei + login_id + password + delete_flag + "を更新しました。";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("/Member/todo/Top"); // PRGパターン（Post>Redirt>Get）
        }
    }
}
