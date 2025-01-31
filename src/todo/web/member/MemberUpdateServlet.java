package todo.web.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dao.MemberDAO;
import todo.entity.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public MemberUpdateServlet()
    {
        super();
    }

    /**
     * GETメソッドを使って詳細画面に飛ぶ
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        MemberDto dtos = (MemberDto) session.getAttribute("member");

        if (dtos == null)
        {
            // int id = ((Member) session.getAttribute("user")).getId();
            int id = Integer.parseInt(request.getParameter("id"));
            Member entity = new Member();
            try (MemberDAO dao = new MemberDAO())
            {

                entity = dao.memberDetail(id);
            }
            catch (Exception e)
            {
                throw new ServletException(e);
            }
            session.removeAttribute("member");
            request.setAttribute("dto", entity);
        }
        else
        {
            request.setAttribute("dto", dtos);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/memberUpdate.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        // int id = ((Member) session.getAttribute("user")).getId();
        int id = Integer.parseInt(request.getParameter("id"));

        String name_sei = request.getParameter("name_sei");
        String name_mei = request.getParameter("name_mei");
        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");
        String delete_flag = request.getParameter("delete_flag");

        MemberDto dto = new MemberDto();
        dto.setId(id);
        dto.setName_sei(name_sei);
        dto.setName_mei(name_mei);
        dto.setLogin_id(login_id);
        dto.setPassword(password);
        dto.setDelete_flag(delete_flag);

        // MemberDto 入力チェックを行う
        List<String> errMsgs = MemberCheck.memberValueCheck(dto);
        if (!errMsgs.isEmpty())
        {
            request.setAttribute("errorMessages", errMsgs);
            request.setAttribute("dto", dto);
            // 詳細画面を表示する
            RequestDispatcher rd = request.getRequestDispatcher("/memberUpdate.jsp");
            rd.forward(request, response);
            return;
        }
        request.getSession().setAttribute("member", dto);
        response.sendRedirect("./update_confirm");

    }

}
