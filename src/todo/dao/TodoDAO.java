package todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import todo.entity.Todo;
import utils.BooleanUtils;

public class TodoDAO extends DAO{
	/**
	 *
	 * @param select
	 * @return
	 * @throws Exception
	 * トップページで検索ボタンで検索すると、一覧情報が表示されます。
	 */
	public List<Todo> todoSearch(Todo select) throws Exception {
		List<Todo> returnList = new ArrayList<Todo>();
		//String sql = "SELECT * FROM test2.todo WHERE title LIKE ? AND date_from = ?";
		//String sql = "SELECT * FROM test2.todo WHERE date_from = ?";
		String sql = "SELECT * FROM test2.todo WHERE title LIKE ? AND date_from BETWEEN (? - INTERVAL 1 WEEK) AND now()";
		//プリペアードステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setString(1, "%" + select.getTitle() + "%");
		statement.setDate(2,select.getDate_from());
		//SQLを実行しその結果を取得する
		ResultSet rs = statement.executeQuery();

		while(rs.next()) {
			Todo dto = new Todo();
			dto.setId(rs.getInt("id"));
			dto.setDate_from(rs.getDate("date_from"));
			dto.setDate_to(rs.getDate("date_to"));
			dto.setTitle(rs.getString("title"));
			dto.setDetail(rs.getString("detail"));
			dto.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));

			returnList.add(dto);
		}
		return returnList;
	}
	/**
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 * ログインしたメンバーのuserIdを登録画面でdate_from,date_to、タイトル、詳細情報、done_flagを入力する。
	 * その後、t_registerメソッドを通して、データベースに登録されます。
	 */
	public int t_register(Todo dto)throws Exception {
		String sql = "INSERT INTO test2.todo(date_from,date_to,title,detail,done_flag,user_id) VALUES(?,?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setDate(1,dto.getDate_from());
			statement.setDate(2,dto.getDate_to());
			statement.setString(3, dto.getTitle());
			statement.setString(4, dto.getDetail());
			statement.setBoolean(5, dto.isDone_flag());
			statement.setInt(6, dto.getUserId());

			result = statement.executeUpdate();
			//コミットを行う
			super.commit();
		}catch(Exception e) {
			//ロールバックを行う
			super.rollback();
			throw e;
	}
		return result;

	}
	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * ログインメソッドで渡された、id情報を取得します。
	 * 渡されたidを元にメンバー情報を更新するための詳細画面が表示されます。
	 */
	public Todo t_updetail(int id)throws Exception {
		String sql = "SELECT * FROM test2.todo WHERE id = ?";
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, id);
		Todo dto = null;
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			dto = new Todo();
			dto.setId(rs.getInt("id"));
			dto.setDate_from(rs.getDate("date_from"));
			dto.setDate_to(rs.getDate("date_to"));
			dto.setTitle(rs.getString("title"));
			dto.setDetail(rs.getString("detail"));
			dto.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));
			//dto.setUserId(rs.getInt("userId"));
		}
		return dto;
	}
	/**
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 * 更新画面で苗字、名前、ログインID、パスワード、削除フラッグ、登録日、更新日、最終更新日を入力する。
	 * その後、m_registerメソッドを通して、データベースに登録されます。
	 */
	public int t_update(Todo dto)throws Exception {
		String sql = "UPDATE test2.todo SET date_from = ?,date_to = ?,title = ?,detail = ?,user_id = ? WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setDate(1, dto.getDate_from());
			statement.setDate(2, dto.getDate_to());
			statement.setString(3, dto.getTitle());
			statement.setString(4, dto.getDetail());
			statement.setInt(5, dto.getUserId());
			statement.setInt(6, dto.getId());

			result = statement.executeUpdate();
			//コミットを行う
			super.commit();
		}catch(Exception e) {
			//ロールバックを行う
			super.rollback();
			throw e;
		}
		return result;
	}
	public int todoupdateflag(Todo dto) throws Exception{
		String sql = "UPDATE test2.todo SET done_flag = true WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			//statement.setBoolean(1, dto.isDone_flag());
			statement.setInt(1, dto.getId());

			result = statement.executeUpdate();
			//コミットを行う
			super.commit();
		}catch(Exception e) {
			//ロールバックを行う
			super.rollback();
			throw e;
		}
		return result;
	}

}
