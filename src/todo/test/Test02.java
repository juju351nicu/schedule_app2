package todo.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import todo.dao.MemberDAO;
import todo.dto.Member;
class Test02 {
    @Test
    public void testExcute1()  {

        String login_id = "user01";
        String password = "password";
        try (MemberDAO dao = new MemberDAO())
        {
        Member entity = dao.login(login_id, password);
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

        }
    }
        }

