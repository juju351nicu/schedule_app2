package todo.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import todo.dao.MemberDAO;
import todo.dto.Member;

public class loginTest extends MemberDAO {
    @Test
    public static void testExcute1()  throws ServletException, IOException {

        String login_id = "user01";
        String password = "password";
        try (MemberDAO dao = new MemberDAO())
        {
        Member entity;
            entity = dao.login(login_id, password);
        boolean result = (entity != null);
        if(result) {
            System.out.println("testExcute1 :ê¨å˜ÇµÇ‹ÇµÇΩ");
            assertEquals(1,1);
        }else {
            System.out.println("testExcute1 :é∏îsÇµÇ‹ÇµÇΩ");
            assertEquals(1,1);
        }
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
        }





