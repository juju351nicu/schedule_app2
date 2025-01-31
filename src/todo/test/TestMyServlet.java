 package todo.test;

import static org.junit.Assert.assertEquals;

import todo.dao.MemberDAO;
import todo.dto.Member;

public class TestMyServlet {
    public static void main(String[] args) {
        try
        {
            testExcute1();
        }
        catch (Exception e)
        {
            // TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
            e.printStackTrace();
        }
    }
    public static void testExcute1() throws Exception  {

        String login_id = "user01";
        String password = "password";
        MemberDAO dao = new MemberDAO();

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
    }


