package utils;

public final class BooleanUtils
{
private BooleanUtils() {}

public static Boolean sqlboolean(int flag) {
    {
      if(flag == 1)
      {
          return true;
      }else {
          return false;
      }
  }
}

}
