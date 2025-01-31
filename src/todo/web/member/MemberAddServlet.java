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

/**
 * add.jspを表示させるサーブレット Servlet implementation class MemberAdd
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public MemberAddServlet()
    {
        super();
    }

    /**
     * Memberクラスのidを使い、詳細画面を表示する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 確認画面からredirectされたときの場合はsessionで渡される。
        HttpSession session = request.getSession();
        MemberDto dto = (MemberDto) session.getAttribute("member");
        session.removeAttribute("member");

        // Top.jspから来た時
        if (dto == null)
        {
            dto = new MemberDto();
            // 新規登録であることを判別するためid=0としている。
//            dto.setId(0);
        }

        // 詳細画面を表示する
        request.setAttribute("dto", dto);
        RequestDispatcher rd = request.getRequestDispatcher("/memberAdd.jsp");
        rd.forward(request, response);
    }

    /**
     * doPostメソッドを使って、新規登録をしていく
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

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
            RequestDispatcher rd = request.getRequestDispatcher("/memberAdd.jsp");
            rd.forward(request, response);
            return;
        }

        request.getSession().setAttribute("dto", dto);
        response.sendRedirect("./add_confirm");
    }

}
