package todo.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Todo
{
     private int id;
        private Date date_from;
        private Date date_to;
        private String title;
        private String detail;
        private boolean done_flag;
        private int userId;

        public int getId()
        {
            return id;
        }
        public void setId(int id)
        {
            this.id = id;
        }

        public Date getDate_from()
        {
            return date_from;
        }
        public void setDate_from(Date date_from)
        {
            this.date_from = date_from;
        }
        public void setDate_from(String date_from)
        {
            try {
	    	LocalDate date = LocalDate.parse(date_from);
		    java.sql.Date sqlDate = java.sql.Date.valueOf(date);
	        this.date_from = sqlDate;

        	}catch(DateTimeParseException e) {
        	    LocalDate nowdate = LocalDate.parse("2019-09-11");
                java.sql.Date sqlnow = java.sql.Date.valueOf(nowdate);
                this.date_from = sqlnow;
        	}

        }

        public Date getDate_to()
        {
            return date_to;
        }
        public void setDate_to(Date date_to)
        {
            this.date_to = date_to;
        }
        public void setDate_to(String date_to)
        {
        	LocalDate date = LocalDate.parse(date_to);
        	java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        	this.date_to = sqlDate;
        	System.out.println(date_to);
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
        public boolean getDone_flag()
        {
            return done_flag;
        }
        public void setDone_flag(int done_flag)
        {
            if(done_flag == 1)
            {
            	this.done_flag = true;
            }else {
            	this.done_flag = false;
            }
        }
        public int getUserId()
        {
            return userId;
        }
        public void setUserId(int userId)
        {
            this.userId = userId;
        }
        public void setUserId(String userId)
        {
        	int num = Integer.parseInt(userId);
            this.userId = num;
        }
    }

