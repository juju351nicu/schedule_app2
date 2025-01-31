package utils;

public final class BooleanUtils
{
private BooleanUtils() {}
/**
 *String型で受け取った変数をsqlのBoolean型に変更するメソッド
 * @param flag
 * @return
 */
public static Boolean sqlboolean(String done_flag) {
    {
        int flag = Integer.parseInt(done_flag);
      if(flag == 1)
      {
          System.out.println("TRUEが入りました");
          return true;
      }else if(flag == 0) {
          System.out.println("falseが入りました" + flag);
          return false;
      }else {
          return false;
      }
  }
}

}
