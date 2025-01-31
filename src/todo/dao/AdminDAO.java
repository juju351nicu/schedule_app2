package todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import todo.entity.Admin;

public class AdminDAO extends DAO
{

    /**
    *
    * @param loginId  ログイン画面で入力した、ログインIdの情報が渡されます。
    * @param password ログイン画面で入力した、パスワードの情報が渡されます
    * @return
    * @throws Exception ログイン画面でログインIDとパスワードを入力したら、ログイン処理が行われます。
    */
   public Admin login(String loginId, String password) throws Exception
   {
       String sql = "SELECT * FROM admin WHERE login_id = ? AND password = ?";
       PreparedStatement statement = getPreparedStatement(sql);
       statement.setString(1, loginId);
       statement.setString(2, password);
       ResultSet rs = statement.executeQuery();
       Admin entity = null;
       if (rs.next())
       {
           entity = new Admin();
           entity.setId(rs.getInt(1));
           entity.setName_sei(rs.getString("name_sei"));
           entity.setName_mei(rs.getString("name_mei"));
           entity.setLogin_id(rs.getString("login_id"));
           entity.setPassword(rs.getString("password"));
           entity.setLast_login(Timestamp.valueOf(LocalDateTime.now()));
       }
       return entity;
   }
   /**
   *
   * @param dto ログインしたユーザのId情報と、ログインした時刻の情報を取得し、更新する
   * @return
   * @throws Exception session.getLastAccessedTime()から情報を受け取り、last_loginのフィールドの値を更新する。
   */
  public int lastlogin(Timestamp last_login, int id) throws Exception
  {
      String sql = "UPDATE admin SET last_login = ? WHERE id = ?";
      int result = 0;
      try
      {
          PreparedStatement statement = getPreparedStatement(sql);
          statement.setTimestamp(1, last_login);
          statement.setInt(2, id);

          result = statement.executeUpdate();
          // コミットを行う
          super.commit();
      }
      catch (Exception e)
      {
          // ロールバックを行う
          super.rollback();
          throw e;
      }
      return result;
  }

}
