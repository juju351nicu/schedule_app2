package todo.entity;

import java.sql.Timestamp;

public class Admin
{
    private int id;
    private String name_sei;
    private String name_mei;
    private String login_id;
    private String password;
    private Timestamp last_login;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName_sei()
    {
        return name_sei;
    }
    public void setName_sei(String name_sei)
    {
        this.name_sei = name_sei;
    }
    public String getName_mei()
    {
        return name_mei;
    }
    public void setName_mei(String name_mei)
    {
        this.name_mei = name_mei;
    }
    public String getLogin_id()
    {
        return login_id;
    }
    public void setLogin_id(String login_id)
    {
        this.login_id = login_id;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public Timestamp getLast_login()
    {
        return last_login;
    }
    public void setLast_login(Timestamp last_login)
    {
        this.last_login = last_login;
    }

}
