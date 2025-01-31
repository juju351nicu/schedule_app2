package todo.web.todo;

/**
 * Todo詳細/登録/更新画面に表示するTodoデータを保持するDTO
 * @author d5riv
 * @since 2020/11/28
 */
public class TodoDto
{
    private int id;
    private String date_from;
    private String date_to;
    private String title;
    private String detail;
    private String done_flag;
    private int userId;

    public TodoDto()
    {
        super();
    }
    public TodoDto(int id, String date_from, String date_to, String title,
            String detail, String done_flag, int userId)
    {
        super();
        this.id = id;
        this.date_from = date_from;
        this.date_to = date_to;
        this.title = title;
        this.detail = detail;
        this.done_flag = done_flag;
        this.userId = userId;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getDate_from()
    {
        return date_from;
    }
    public void setDate_from(String date_from)
    {
        this.date_from = date_from;
    }
    public String getDate_to()
    {
        return date_to;
    }
    public void setDate_to(String date_to)
    {
        this.date_to = date_to;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    public String getDone_flag()
    {
        return done_flag;
    }
    public void setDone_flag(String done_flag)
    {
        this.done_flag = done_flag;
    }
    public int getUserId()
    {
        return userId;
    }
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}
