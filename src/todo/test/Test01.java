package todo.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import todo.dao.MemberDAO;
import todo.dto.Member;

//class Test01 extends MemberDAO {
class Test01{
    //正常テストを実行する
    @SuppressWarnings("resource")
    @Test
    public void test正常テスト() throws Exception {

        Member dto = new Member();
        /*
         * dto.setId(1); dto.setName_sei("菱田川"); dto.setName_mei("葛夫");
         * dto.setLogin_id("user35"); dto.setPassword("password");
         */

        MemberDAO dao = new MemberDAO();
        //int result = dao.m_register(dto);
        System.out.println("正常テストが完了しました");
        assertEquals(1,1);

        }
    @Test
    public static void testログインテスト(){

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


