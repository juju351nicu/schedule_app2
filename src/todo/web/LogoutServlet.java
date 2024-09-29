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

/**
 *
 * @author PC0010
 *
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogoutServlet() {
        super();
    }
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//sessionから最終サクセス時刻を取得し、Timestamp型のlast_loginに格納する
		Member user = (Member) request.getSession().getAttribute("user");
		Timestamp last_login = new Timestamp(session.getLastAccessedTime());
		/*        Member dto = new Member();
		dto.setLast_login(last_login);
		dto.setId(user.getId());*/

		try(MemberDAO dao = new MemberDAO())
        {
        	dao.lastlogin(last_login,user.getId());
        	//LogoutServletでsessionを破棄する
        	// top.jspでログアウトボタンを押すとlogin.jspへリダイレクトされる

        	session.invalidate();
        	response.sendRedirect("/Member/login.jsp");
        }catch(Exception e) {
		  e.printStackTrace();

        }
	}



	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
