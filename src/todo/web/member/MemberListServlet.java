package todo.web.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.MemberDAO;
import todo.entity.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public MemberListServlet()
    {
        super();
    }

    /**
     * メンバー情報の一覧情報の検索処理を実行する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (MemberDAO dao = new MemberDAO())
        {
            // メンバーのリストを一覧で取得し、リクエスト属性に格納する
            List<Member> list = dao.memberList();

            request.setAttribute("MemberList", list);
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/memberList.jsp");
        rd.forward(request, response);
    }

    /**
     * チェックボックスによる複数の削除処理
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 下記だとStringなのでint型に変える
        String[] id = request.getParameterValues("id");
        System.out.println(id);

        for (int i = 0; i < id.length; i++)
        {
            System.err.println("チェックボックスから" + id[i] + "渡されました");
        }

        try (MemberDAO dao = new MemberDAO())
        {
            int result = dao.memberDelete(id);
            System.out.println("memberDAOから返ってきた結果は" + result + "です");
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }

        response.sendRedirect("../member/list");
    }

}
