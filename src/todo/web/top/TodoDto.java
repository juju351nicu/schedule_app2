package todo.web.top;

import java.time.LocalDate;

/**
 * Top画面に表示するTodoデータを保持するDTO
 *
 * @author d5riv
 * @since 2020/11/28
 */
public class TodoDto
{
    private int id;
    private LocalDate date_from;
    private LocalDate date_to;
    private String title;
    private String detail;
    private boolean done_flag;
    private int userId;
    private long remainingDays;

    public TodoDto()
    {
        super();
    }

    public TodoDto(int id, LocalDate date_from, LocalDate date_to, String title, String detail, boolean done_flag,
            int userId, long remainingDays)
    {
        super();
        this.id = id;
        this.date_from = date_from;
        this.date_to = date_to;
        this.title = title;
        this.detail = detail;
        this.done_flag = done_flag;
        this.userId = userId;
        this.remainingDays = remainingDays;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getDate_from()
    {
        return date_from;
    }

    public void setDate_from(LocalDate date_from)
    {
        this.date_from = date_from;
    }

    public LocalDate getDate_to()
    {
        return date_to;
    }

    public void setDate_to(LocalDate date_to)
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

    public boolean isDone_flag()
    {
        return done_flag;
    }

    public void setDone_flag(boolean done_flag)
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

    public long getRemainingDays()
    {
        return remainingDays;
    }

    public void setRemainingDays(long remainingDays)
    {
        this.remainingDays = remainingDays;
    }
}
