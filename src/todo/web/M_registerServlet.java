package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.MemberDAO;
import todo.dto.Member;

@WebServlet("/member/M_register")
public class M_registerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public M_registerServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name_sei = request.getParameter("name_sei");
        String name_mei = request.getParameter("name_mei");
        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");
        int delete_flag = Integer.parseInt(request.getParameter("delete_flag"));
        String registered_date = request.getParameter("registered_date");
        String updated_date = request.getParameter("updated_date");
        String last_login = request.getParameter("last_login");

        Member dto = new Member();
        dto.setId(id);
        dto.setName_sei(name_sei);
        dto.setName_mei(name_mei);
        dto.setLogin_id(login_id);
        dto.setPassword(password);
        dto.setDelete_flag(delete_flag);
        dto.setRegistered_date(registered_date);
        dto.setUpdated_date(updated_date);
        dto.setLast_login(last_login);

        System.out.println("doPost Name_sei=" + name_sei);
        try (MemberDAO dao = new MemberDAO()) {
            dao.m_register(dto);

        } catch (Exception e) {
            e.getStackTrace();
            throw new ServletException(e);
        }
        String message = "êVãKÉÅÉìÉoÅ[ìoò^Ç™äÆóπÇµÇ‹ÇµÇΩ";
        setMessage(request, message);
        request.setAttribute("dto", dto);
        RequestDispatcher rd = request.getRequestDispatcher("/m_register_confirem.jsp");
        rd.forward(request, response);
    }

    private void setMessage(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
    }

}
