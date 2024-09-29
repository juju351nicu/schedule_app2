package todo.entity;

import java.sql.Date;
import java.time.temporal.ChronoUnit;

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
        public Date getDate_to()
        {
            return date_to;
        }
        public void setDate_to(Date date_to)
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

        //public String toString(Date date_from,Date date_to) {
        @Override
        public String toString() {
            //LocalDate date1 = date_from.toLocalDate();
            //LocalDate date2 = date_to.toLocalDate();
            //if(date_from.before(date_to)) {
                //Period period = Period.between(date_from.toLocalDate(), date_to.toLocalDate());
        //System.out.println(period.getDays() + "“úŠÔ");
            String localdate = null;
            long day = ChronoUnit.DAYS.between(date_from.toLocalDate(), date_to.toLocalDate());
            System.out.println(String.valueOf(day) + "“úŠÔ");
            localdate = String.valueOf(day);
            return localdate;
        }
}

