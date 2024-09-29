package todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import todo.entity.Member;
import todo.entity.Todo;
import utils.BooleanUtils;

public class MemberDAO extends DAO {
	/**
	 *
	 * @param loginId ログイン画面で入力した、ログインIdの情報が渡されます。
	 * @param password ログイン画面で入力した、パスワードの情報が渡されます
	 * @return
	 * @throws Exception
	 * ログイン画面でログインIDとパスワードを入力したら、ログイン処理が行われます。
	 */
	public Member login(String loginId, String password) throws Exception {
		//    String sql = "SELECT mt.id,mt.name_sei,mt.name_mei,mt.login_id,mt.password,SUBSTR(st.title,1,100),SUBSTR(st.detail,1,100) FROM member mt INNER JOIN todo st ON mt.login_id = st.login_id WHERE mt.login_id = ? AND password = ?";
		String sql = "SELECT * FROM member m WHERE m.login_id = ? AND password = ?";
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setString(1, loginId);
		statement.setString(2, password);
		ResultSet rs = statement.executeQuery();
		Member dto = null;
		if (rs.next()) {
			dto = new Member();
			dto.setId(rs.getInt(1));
			dto.setName_sei(rs.getString("name_sei"));
			dto.setName_mei(rs.getString("name_mei"));
			dto.setLogin_id(rs.getString("login_id"));
			dto.setPassword(rs.getString("password"));
			dto.setLast_login(rs.getTimestamp("last_login"));
			//             dto.setDetail2(rs.getString("SUBSTR(st.detail,1,100)"));
			//             dto.setTitle2(rs.getString("SUBSTR(st.title,1,100)"));
			//             dto.setDetail2(rs.getString("detail"));
			//             dto.setTitle2(rs.getString("title"));
		}
		return dto;
	}
	/**
	 *
	 * @return
	 * @throws Exception
	 * メンバーの一覧情報が取得されます。
	 */
	public List<Member> SearchList() throws Exception {
		List<Member> resultList = new ArrayList<Member>();
		//String sql = "SELECT id,name_sei,name_mei,login_id,password,registered_date,updated_date,last_login,delete_flag FROM test2.member WHERE last_login BETWEEN (CURDATE() - INTERVAL 10 DAY) AND (CURDATE() + INTERVAL 1 DAY)";
		String sql = "SELECT id,name_sei,name_mei,login_id,password,registered_date,updated_date,last_login,delete_flag FROM test2.member ";
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をTodoインスタンスへ格納する
		while (rs.next()) {
			Member dto = new Member();
			dto.setId(rs.getInt("id"));
			dto.setName_sei(rs.getString("name_sei"));
			dto.setName_mei(rs.getString("name_mei"));
			dto.setLogin_id(rs.getString("login_id"));
			dto.setPassword(rs.getString("password"));
			dto.setRegistered_date(rs.getTimestamp("registered_date"));
			dto.setUpdated_date(rs.getTimestamp("updated_date"));
			dto.setLast_login(rs.getTimestamp("last_login"));
			dto.setDelete_flag(rs.getByte("delete_flag"));
			resultList.add(dto);
		}

		return resultList;
	}
	/**
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 * 登録画面で苗字、名前、ログインID、パスワード、削除フラッグ、登録日、更新日、最終更新日を入力する。
	 * その後、m_registerメソッドを通して、データベースに登録されます。
	 */
	public int m_register(Member dto) throws Exception {
		String sql = "INSERT INTO member(name_sei,name_mei,login_id,password,delete_flag,registered_date,updated_date,last_login) VALUES(?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, dto.getName_sei());
			statement.setString(2, dto.getName_mei());
			statement.setString(3, dto.getLogin_id());
			statement.setString(4, dto.getPassword());
			statement.setBoolean(5, dto.getDelete_flag());
			statement.setTimestamp(6, dto.getRegistered_date());
			statement.setTimestamp(7, dto.getUpdated_date());
			statement.setTimestamp(8, dto.getLast_login());

			result = statement.executeUpdate();
			//コミットを行う
			super.commit();
		} catch (Exception e) {
			//ロールバックを行う
			super.rollback();
			throw e;
		}
		return result;
	}
	/**
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 * 更新画面で苗字、名前、ログインID、パスワード、削除フラッグ、登録日、更新日、最終更新日を入力する。
	 * その後、m_registerメソッドを通して、データベースに登録されます。
	 */
	public int m_update(Member dto) throws Exception {
		String sql = "UPDATE member SET name_sei = ?,name_mei = ?,login_id = ?,password = ?,registered_date = ?,updated_date = ?,last_login = ?,delete_flag = ? WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, dto.getName_sei());
			statement.setString(2, dto.getName_mei());
			statement.setString(3, dto.getLogin_id());
			statement.setString(4, dto.getPassword());
			statement.setTimestamp(5, dto.getRegistered_date());
			statement.setTimestamp(6, dto.getUpdated_date());
			statement.setTimestamp(7, dto.getLast_login());
			statement.setBoolean(8, dto.getDelete_flag());
			statement.setInt(9, dto.getId());

			result = statement.executeUpdate();
			//コミットを行う
			super.commit();
		} catch (Exception e) {
			//ロールバックを行う
			super.rollback();
			throw e;
		}
		return result;
	}

	/**
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 * ログインした後、トップページに飛ぶとTodoクラスの情報が一覧情報で表示されます。
	 */
	public List<Todo> getTodoSummary(int userId) throws Exception {
		//String sql = "SELECT id,title,SUBSTR(detail,1,10) AS 'detail',date_from,date_to,done_flag FROM test2.todo  WHERE user_id = ?";
		String sql = "SELECT * FROM test2.todo  WHERE user_id = ? AND done_flag = false ORDER BY date_from DESC";
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, userId);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 検索結果の行数分フェッチを行い、取得結果をTodoインスタンスへ格納する
		List<Todo> resultList = new ArrayList<>();
		while (rs.next()) {
			Todo dto = new Todo();
			dto.setId(rs.getInt("id"));
			dto.setDate_from(rs.getDate("date_from"));
			dto.setDate_to(rs.getDate("date_to"));
			dto.setDetail(rs.getString("detail"));
			dto.setTitle(rs.getString("title"));
			dto.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));
			resultList.add(dto);
		}

		return resultList;

	}
	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * ログインメソッドで渡された、id情報を取得します。
	 * 渡されたidを元にメンバー情報を更新するための詳細画面が表示されます。
	 */
	public Member m_updetail(int id) throws Exception {
		String sql = "SELECT * FROM test2.member WHERE id = ?";
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, id);
		Member dto = null;
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			dto = new Member();
			dto.setId(rs.getInt("id"));
			dto.setName_sei(rs.getString("name_sei"));
			dto.setName_mei(rs.getString("name_mei"));
			dto.setName_sei(rs.getString("name_sei"));
			dto.setName_mei(rs.getString("name_mei"));
			dto.setLogin_id(rs.getString("login_id"));
			dto.setPassword(rs.getString("password"));
			dto.setRegistered_date(rs.getTimestamp("registered_date"));
			dto.setUpdated_date(rs.getTimestamp("updated_date"));
			dto.setLast_login(rs.getTimestamp("last_login"));
			dto.setDelete_flag(rs.getByte("delete_flag"));

		}

		return dto;
	}
	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * チェックボックスで複数のidを選択する。
	 * その後、削除ボタンを押すとチェックした1件～複数の項目が削除される。
	 */
	public int m_delete(String id[]) throws Exception {
		String[] remove = id;
		int result = 0;
		if (remove.length > 0) {
			for (int i = 0; i < remove.length; i++) {
				String sql = "DELETE FROM test2.member WHERE id =  ? ";

				//プリペアードステートメントを取得し、実行SQLを渡す
				PreparedStatement statement = getPreparedStatement(sql);
				statement.setInt(1, Integer.parseInt(remove[i]));
				System.out.println("m_delete：" + sql + ">");
				result = statement.executeUpdate();
				System.out.println(result);
			}
		}
		try {
			//コミットを行う
			super.commit();

		} catch (Exception e) {
			super.rollback();
		}

		return result;
	}
	/**
	 *
	 * @param dto ログインしたユーザのId情報と、ログインした時刻の情報を取得し、更新する
	 * @return
	 * @throws Exception
	 * session.getLastAccessedTime()から情報を受け取り、last_loginのフィールドの値を更新する。
	 */
	public int lastlogin(Timestamp last_login, int id) throws Exception {
		String sql = "UPDATE member SET last_login = ? WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setTimestamp(1, last_login);
			statement.setInt(2, id);

			result = statement.executeUpdate();
			//コミットを行う
			super.commit();
		} catch (Exception e) {
			//ロールバックを行う
			super.rollback();
			throw e;
		}
		return result;
	}


}
