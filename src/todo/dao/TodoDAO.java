package todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import todo.criteria.TodoCriteria;
import todo.entity.Todo;
import utils.BooleanUtils;

public class TodoDAO extends DAO
{
//    /**
//     *
//     * @param select
//     * @return
//     * @throws Exception トップページで検索ボタンで検索すると、一覧情報が表示されます。
//     */
//    public List<Todo> todoSelectByTitleAndFrom(Todo select) throws Exception
//    {
//        List<Todo> returnList = new ArrayList<Todo>();
//        String sql = "SELECT * FROM todo WHERE done_flag = ? AND title LIKE ? AND date_from BETWEEN (? - INTERVAL 1 WEEK) AND now()";
//        // プリペアードステートメントを取得し、実行SQLを渡す
//        PreparedStatement statement = getPreparedStatement(sql);
//        statement.setBoolean(1, select.isDone_flag());
//        statement.setString(2, "%" + select.getTitle() + "%");
//        statement.setDate(3, select.getDate_from());
//        // SQLを実行しその結果を取得する
//        ResultSet rs = statement.executeQuery();
//        // System.out.println("done_flagに渡された値が" + select.isDone_flag());
//        while (rs.next())
//        {
//            Todo entity = new Todo();
//            entity.setId(rs.getInt("id"));
//            entity.setDate_from(rs.getDate("date_from"));
//            entity.setDate_to(rs.getDate("date_to"));
//            entity.setTitle(rs.getString("title"));
//            entity.setDetail(rs.getString("detail"));
//            // entity.setDone_flag(Boolean.getBoolean(rs.getString("done_flag")));
//            // entity.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));
//            entity.setDone_flag(BooleanUtils.sqlboolean(rs.getString("done_flag")));
//            returnList.add(entity);
//        }
//        return returnList;
//    }

    /**
     *
     * @param criteria
     * @return
     * @throws Exception
     * @since 2020/12/09
     *        トップページでDate_fromとDate_toで検索すると、Date_fromからDate_toまでの一覧情報が表示されます。
     */
    public List<Todo> todoSelectByTitleAndFromTo(TodoCriteria criteria) throws Exception
    {
//        String sql = "SELECT * FROM todo WHERE done_flag = ? AND title LIKE ? AND date_from BETWEEN ? AND ? ";
        StringBuilder sql = new StringBuilder("SELECT * FROM todo WHERE 1 = 1 ");
        List<Object> parameters = new ArrayList<>();
        if (!criteria.getTitle().isEmpty())
        {
            sql.append("AND title LIKE ? ");
            parameters.add(criteria.getTitle());
        }

        if (criteria.getDate_from() != null && criteria.getDate_to() != null)
        {
            sql.append("AND date_from BETWEEN ? AND ? ");
            parameters.add(criteria.getDate_from());
            parameters.add(criteria.getDate_to());
            sql.append("OR ((date_to BETWEEN ? AND ?) OR (date_from <= ? AND date_to >= ?)) ");
            parameters.add(criteria.getDate_from());
            parameters.add(criteria.getDate_to());
            parameters.add(criteria.getDate_from());
            parameters.add(criteria.getDate_to());
        }
        else if (criteria.getDate_to() != null)
        {
//            sql.append("AND date_to BETWEEN (Select Min(date_to) From todo) AND ? ");
            sql.append("AND date_from <= ? ");
            parameters.add(criteria.getDate_to());
        }
        else if (criteria.getDate_from() != null)
        {
//            sql.append("AND date_from BETWEEN (? - INTERVAL 1 WEEK) AND now() ");
            sql.append("AND date_to >= ? ");
            parameters.add(criteria.getDate_from());
        }

        if(criteria.getDone() != null)
        {
            sql.append("AND done_flag = ? ");
            parameters.add(criteria.getDone());
        }

        sql.append("ORDER BY date_to ASC ");

//        String sql = "SELECT * FROM todo WHERE done_flag = ? AND title LIKE ? AND date_from BETWEEN ? AND ? ";

        List<Todo> returnList = new ArrayList<Todo>();
        // プリペアードステートメントを取得し、実行SQLを渡す
        PreparedStatement statement = getPreparedStatement(sql.toString());
        for (int i = 0; i < parameters.size(); i++)
        {
            Object param = parameters.get(i);
            statement.setObject(i + 1, param);
        }

        /*
         * statement.setBoolean(1, criteria.isDone_flag()); statement.setString(2, "%" +
         * criteria.getTitle() + "%"); statement.setDate(3, criteria.getDate_from());
         * statement.setDate(4, criteria.getDate_to());
         */
        // SQLを実行しその結果を取得する
        System.out.println("SQL:" + statement.toString());

        ResultSet rs = statement.executeQuery();
        System.out.println("date_fromに渡された値が" + criteria.getDate_from());
        System.out.println("date_toに渡された値が" + criteria.getDate_to());
        while (rs.next())
        {
            Todo entity = new Todo();
            entity.setId(rs.getInt("id"));
            entity.setDate_from(rs.getDate("date_from"));
            entity.setDate_to(rs.getDate("date_to"));
            entity.setTitle(rs.getString("title"));
            entity.setDetail(rs.getString("detail"));
            // entity.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));
            // entity.setDone_flag(Boolean.getBoolean(rs.getString("done_flag")));
            entity.setDone_flag(rs.getBoolean("done_flag"));
            returnList.add(entity);
        }
        return returnList;
    }

//    /**
//     *
//     * @param select
//     * @return
//     * @throws Exception
//     * @since 2020/12/09 トップページでDate_toのみで検索すると、一番最初の日付からDate_toまでの一覧情報が降順表示されます。
//     */
//    public List<Todo> todoSelectByTitleAndTo(Todo select) throws Exception
//    {
//        List<Todo> returnList = new ArrayList<Todo>();
//        String sql = "SELECT * FROM todo WHERE done_flag = ? AND title LIKE ? AND date_to  BETWEEN (Select Min(date_to) From todo) AND ? ORDER BY date_to ASC";
//        // プリペアードステートメントを取得し、実行SQLを渡す
//        PreparedStatement statement = getPreparedStatement(sql);
//        statement.setBoolean(1, select.isDone_flag());
//        statement.setString(2, "%" + select.getTitle() + "%");
//        statement.setDate(3, select.getDate_to());
//        // SQLを実行しその結果を取得する
//        ResultSet rs = statement.executeQuery();
//        while (rs.next())
//        {
//            Todo entity = new Todo();
//            entity.setId(rs.getInt("id"));
//            entity.setDate_from(rs.getDate("date_from"));
//            entity.setDate_to(rs.getDate("date_to"));
//            entity.setTitle(rs.getString("title"));
//            entity.setDetail(rs.getString("detail"));
//            // entity.setDone_flag(Boolean.getBoolean(rs.getString("done_flag")));
//            // entity.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));
//            entity.setDone_flag(BooleanUtils.sqlboolean(rs.getString("done_flag")));
//            returnList.add(entity);
//        }
//        return returnList;
//    }
//
//    /**
//     *
//     * @param select
//     * @return
//     * @throws Exception
//     * @since 2020/12/09 トップページでDate_toのみで検索すると、一番最初の日付からDate_toまでの一覧情報が表示されます。
//     */
//    public List<Todo> todoSelectByTitle(Todo select) throws Exception
//    {
//        List<Todo> returnList = new ArrayList<Todo>();
//        String sql = "SELECT * FROM todo WHERE done_flag = ? AND title LIKE ? ";
//        // プリペアードステートメントを取得し、実行SQLを渡す
//        PreparedStatement statement = getPreparedStatement(sql);
//        statement.setBoolean(1, select.isDone_flag());
//        statement.setString(2, "%" + select.getTitle() + "%");
//        // SQLを実行しその結果を取得する
//        ResultSet rs = statement.executeQuery();
//        while (rs.next())
//        {
//            Todo entity = new Todo();
//            entity.setId(rs.getInt("id"));
//            entity.setDate_from(rs.getDate("date_from"));
//            entity.setDate_to(rs.getDate("date_to"));
//            entity.setTitle(rs.getString("title"));
//            entity.setDetail(rs.getString("detail"));
//            // entity.setDone_flag(Boolean.getBoolean(rs.getString("done_flag")));
//            entity.setDone_flag(BooleanUtils.sqlboolean(rs.getString("done_flag")));
//            returnList.add(entity);
//        }
//        return returnList;
//    }

    /**
     *
     * @param entity
     * @return
     * @throws Exception ログインしたメンバーのuserIdを登録画面でdate_from,date_to、タイトル、詳細情報、done_flagを入力する。
     *                   その後、t_registerメソッドを通して、データベースに登録されます。
     */
    public int todoRegister(Todo entity) throws Exception
    {
        String sql = "INSERT INTO todo(date_from,date_to,title,detail,done_flag,user_id) VALUES(?,?,?,?,?,?)";
        int result = 0;
        try
        {
            PreparedStatement statement = getPreparedStatement(sql);
            statement.setDate(1, entity.getDate_from());
            statement.setDate(2, entity.getDate_to());
            statement.setString(3, entity.getTitle());
            statement.setString(4, entity.getDetail());
            statement.setBoolean(5, entity.isDone_flag());
            statement.setInt(6, entity.getUserId());

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

    /**
     *
     * @param id
     * @return
     * @throws Exception ログインメソッドで渡された、id情報を取得します。
     *                   渡されたidを元にメンバー情報を更新するための詳細画面が表示されます。
     */
    public Todo todoDetail(int id) throws Exception
    {
        String sql = "SELECT * FROM todo WHERE id = ?";
        PreparedStatement statement = getPreparedStatement(sql);
        statement.setInt(1, id);
        Todo entity = null;
        ResultSet rs = statement.executeQuery();
        if (rs.next())
        {
            entity = new Todo();
            entity.setId(rs.getInt("id"));
            entity.setDate_from(rs.getDate("date_from"));
            entity.setDate_to(rs.getDate("date_to"));
            entity.setTitle(rs.getString("title"));
            entity.setDetail(rs.getString("detail"));
            // entity.setDone_flag(BooleanUtils.sqlboolean(rs.getByte("done_flag")));
            entity.setDone_flag(BooleanUtils.sqlboolean(rs.getString("done_flag")));
            // dto.setUserId(rs.getInt("userId"));
        }
        return entity;
    }

    /**
     *
     * @param entity
     * @return
     * @throws Exception 更新画面で苗字、名前、ログインID、パスワード、削除フラッグ、登録日、更新日、最終更新日を入力する。
     *                   その後、m_registerメソッドを通して、データベースに登録されます。
     */
    public int todoUpdate(Todo entity) throws Exception
    {
        // String sql = "UPDATE test2.todo SET date_from = ?,date_to = ?,title =
        // ?,detail = ?,user_id = ? WHERE id = ?";
        String sql = "UPDATE todo SET date_from = ?,date_to = ?,title = ?,detail = ?,done_flag = ? WHERE id = ?";
        int result = 0;
        try
        {
            PreparedStatement statement = getPreparedStatement(sql);
            statement.setDate(1, entity.getDate_from());
            statement.setDate(2, entity.getDate_to());
            statement.setString(3, entity.getTitle());
            statement.setString(4, entity.getDetail());
            // statement.setInt(5, dto.getUserId());
            statement.setBoolean(5, entity.isDone_flag());
            statement.setInt(6, entity.getId());

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

    public int todoUpdateDone_flag(Todo entity) throws Exception
    {
        String sql = "UPDATE todo SET done_flag = true WHERE id = ?";
        int result = 0;
        try
        {
            PreparedStatement statement = getPreparedStatement(sql);
            // statement.setBoolean(1, dto.isDone_flag());
            statement.setInt(1, entity.getId());

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
