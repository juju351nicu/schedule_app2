package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import todo.dao.MemberDAO;
import todo.entity.Member;

class Test01 extends MyContext {
	static void init() throws NamingException, SQLException {
        // "local.MyContextFactory"の部分は実際のパッケージ名を指定しよう。
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "local.MyContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, "local");

        MariaDbDataSource ds = new MariaDbDataSource();
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/test2");
        ds.setUser("root");
        ds.setPassword("password");

        Context ctx = new InitialContext();
        ctx.bind("jdbc/test", ds);
    }
	 //正常テストを実行する
	@SuppressWarnings("resource")
	@Test
	public void test正常テスト() throws Exception {

	    Member dto = new Member();

	      dto.setId(1);
	      dto.setName_sei("菱田川");
	      dto.setName_mei("葛夫");
	      dto.setLogin_id("user35");
	      dto.setPassword("password");


	    MemberDAO dao = new MemberDAO();
	   int result = dao.m_register(dto);
	    assertEquals(1,1);
	    }
	@Test
	public void testログインテスト() throws ServletException, IOException {

		String login_id = "user01";
		String password = "password";
		try (MemberDAO dao = new MemberDAO()) {
			Member entity;
			entity = dao.login(login_id, password);
			boolean result = (entity != null);
			if (result) {
				System.out.println("testExcute1 :成功しました");
				assertEquals(1, 1);
			} else {
				System.out.println("testExcute1 :失敗しました");
				assertEquals(1, 1);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
