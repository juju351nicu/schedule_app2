package todo.criteria;

import java.sql.Date;

/**
 * 検索用データを保持するDTO
 * @author d5riv
 *
 */
public class TodoCriteria
{
    private String title;
    private Date date_from;
    private Date date_to;
    private Boolean done;

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public Date getDate_from()
    {
        return date_from;
    }
    public void setDate_from(Date date_from)
    {
        this.date_from = date_from;
    }
    public Date getDate_to()
    {
        return date_to;
    }
    public void setDate_to(Date date_to)
    {
        this.date_to = date_to;
    }
    public Boolean getDone()
    {
        return done;
    }
    public void setDone(Boolean done)
    {
        this.done = done;
    }
}
