package todo.web;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dao.MemberDAO;
import todo.entity.Member;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public LoginServlet()
    {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");

//        Member dto = new Member();
//        dto.setLogin_id(login_id);
//        dto.setPassword(password);
        HttpSession session = request.getSession();
        try (MemberDAO dao = new MemberDAO())
        {
            Member entity = dao.login(login_id, password);
//            boolean isValidLogin = (entity != null && login_id.equals(entity.getLogin_id()) && password.equals(entity.getPassword()));
            boolean isValidLogin = (entity != null);
            System.out.println("loginServlet-doPost login_id="+login_id);
            if (isValidLogin)
            {
                session.setAttribute("user", entity);
//                RequestDispatcher rd = request.getRequestDispatcher("/top.jsp");
//                rd.forward(request, response);
                response.sendRedirect("./todo/Top"); // PRGパターン（Post>Redirt>Get）

                System.out.println("セッション最終アクセス時刻：" + new Timestamp( session.getLastAccessedTime() ) );
            }
            else
            {
                request.setAttribute("error", "ログインかパスワードが違います");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            System.out.println("loginServlet-doPost password="+password);
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

}