package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public final class DateUtils
{
    public DateUtils() {}

    public static LocalDate StrToLocalDate(String dateStr)
    {
        return LocalDate.parse(dateStr);
    }

    public static Date LocalDateToSQlDate(LocalDate date)
    {
        return java.sql.Date.valueOf(date);
    }

    public static Date StrToSqlDate(String dateStr)
    {
        return LocalDateToSQlDate(StrToLocalDate(dateStr));
    }
    /**
     * Date_fromかDate_toをフロント画面で入力するとString型に変換されるため、
     * String型のものをsqlのDate型に変換するメソッドを用意した。
     * @param strdate
     * @return Date型で返される。
     */
    public static Date datesql(String strdate) {
        /*
         * LocalDate localdate = LocalDate.parse(date); java.sql.Date sqlDate =
         * java.sql.Date.valueOf(localdate); return sqlDate;
         */
        try {
        LocalDate date = LocalDate.parse(strdate);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        return sqlDate;

        }catch(DateTimeParseException e) {
            LocalDate nowdate = LocalDate.parse("2019-09-11");
            java.sql.Date sqlnow = java.sql.Date.valueOf(nowdate);
            System.out.println("今日の日付は" + strdate);
            return sqlnow;

        }
    }
    /**
     * register_deteかupdate_date,last_loginをフロント画面で入力するとString型に変換されるため、
     * String型のものをsqlのTimestamp型に変換するメソッドを用意した。
     * @param strTimestamp
     * @return
     */
    public static Timestamp sqlTimestamp(String strTimestamp) {
    	try
    	{
    	//String型からTimestamp型へ変換をする
		LocalDateTime dateTime = LocalDateTime.parse(strTimestamp);
	    //LocalDateTime dateTime = date.atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        	return timestamp;

	    }catch(DateTimeParseException e) {
	    	//何も入力しなかった場合はDateTimeParseExceptionが発生するので現在時刻で更新する
	    	LocalDateTime now = LocalDateTime.now();
	    	Timestamp sqlnow = Timestamp.valueOf(now);
	    	return sqlnow;
	    }
    }

}

