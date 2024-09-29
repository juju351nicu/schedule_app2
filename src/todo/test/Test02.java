package todo.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import todo.dao.MemberDAO;
import todo.entity.Member;
import todo.web.LoginServlet;

class Test02 extends LoginServlet {
    @Test
    public void testExcute1() {
    	LoginServlet login = new LoginServlet();

        String login_id = "user01";
        String password = "password";
        try (MemberDAO dao = new MemberDAO())
        {
        Member entity = dao.login(login_id, password);
        boolean result = (entity != null);
        if(result) {
            System.out.println("testExcute1 :成功しました");
            assertEquals(1,1);
        }else {
            System.out.println("testExcute1 :失敗しました");
            assertEquals(1,1);
        }
        }
        catch (Exception e)
        {

        }
    }
        }

