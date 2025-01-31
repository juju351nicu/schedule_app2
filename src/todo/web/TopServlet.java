package todo.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dao.MemberDAO;
import todo.entity.Member;
import todo.entity.Todo;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/todo/Top")
public class TopServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	//ログインの際に取得したメンバー情報をMember userに格納する
    	Member user = (Member) request.getSession().getAttribute("user");
        //ログインされたら、last_loginに最終サクセス時刻を更新する
        HttpSession session = request.getSession(false);
        Timestamp last_login = new Timestamp(session.getLastAccessedTime());
		/*        Member dto = new Member();
		dto.setLast_login(last_login);
		dto.setId(user.getId());*/



        try(MemberDAO dao = new MemberDAO())
        {
        	int result = dao.lastlogin(last_login,user.getId());
            List<Todo> todoSummary = dao.getTodoSummary(user.getId());
            System.out.println(result);
            request.setAttribute("todoList", todoSummary);
            request.getRequestDispatcher("/top.jsp")
                .forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
