package todo.dto;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Member
{
    private int id;
    private String name_sei;
    private String name_mei;
    private String login_id;
    private String password;
    private Timestamp registered_date;
    private Timestamp updated_date;
    private Timestamp last_login;
    private boolean delete_flag;

    public Member()
    {

    }


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
    public Timestamp getRegistered_date()
    {
        return registered_date;
    }
    public void setRegistered_date(Timestamp registered_date)
    {
        this.registered_date = registered_date;
    }
    public void setRegistered_date(String registered_date)
    {
    	//StringŒ^‚©‚çTimestampŒ^‚Ö•ÏŠ·‚ð‚·‚é
    	//Java8‚©‚ç‚ÌDateTimeFommatter‚ðŽg‚Á‚Ä“ü—Í‚·‚é
		LocalDateTime dateTime = LocalDateTime.parse(registered_date);
	    //LocalDateTime dateTime = date.atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        this.registered_date = timestamp;
    }
    public Timestamp getUpdated_date()
    {
        return updated_date;
    }
    public void setUpdated_date(Timestamp Updated_date)
    {
    	this.updated_date =Updated_date;
    }
    public void setUpdated_date(String updated_date)
    {
    	//StringŒ^‚©‚çTimestampŒ^‚Ö•ÏŠ·‚ð‚·‚é
    	//LocalDate‚ðŽg—p‚µ‚ÄvalueOf‚ÅLocalDate ‚©‚çTimestamp‚É•ÏŠ·‚·‚é
    	LocalDateTime dateTime = LocalDateTime.parse(updated_date);
        //LocalDateTime dateTime = date.atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        this.updated_date = timestamp;
    }
    public Timestamp getLast_login()
    {
        return last_login;
    }
    public void setLast_login(Timestamp last_login)
    {

        this.last_login = last_login;
    }
    public void setLast_login(String last_login)
    {
       //LocalDate date = LocalDate.parse(last_login);
    	LocalDateTime dateTime = LocalDateTime.parse(last_login);
       Timestamp timestamp = Timestamp.valueOf(dateTime);
       this.last_login = timestamp;
    }
	public boolean getDelete_flag()
	{
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		if(delete_flag == 1)
		{
			this.delete_flag = true;
		}else
		{
		this.delete_flag = false;
		}
	}




}
