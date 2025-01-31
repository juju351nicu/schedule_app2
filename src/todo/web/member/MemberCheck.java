package todo.web.member;

import java.util.ArrayList;
import java.util.List;

/**
 * 入力チェックを行うクラスを用意しました。
 *
 * @author PC0010
 * @since 12月3日
 */
public class MemberCheck
{


    /**
     * Memberクラスの入力チェックを行うためのValueCheckメソッド
     *
     * @param dto
     * @return
     * @since 12月3日
     */
    public static List<String> memberValueCheck(MemberDto dto)
    {
        // エラーメッセージの初期化
    	List<String> errorMessages = new ArrayList<>();
        // id
        if (dto.getId() < 0)
        {
            errorMessages.add("0より小さいIdは存在しません。不正な処理です。");
        }
        // name_sei
        if (dto.getName_sei() == null || dto.getName_sei().isEmpty())
        {
            errorMessages.add("苗字の項目が未入力です。");
            System.out.print("こちらの入力は" + dto.getName_sei());
        }
        else if (dto.getName_sei().length() > 256)
        {
            errorMessages.add("苗字が長すぎです。");
        }
        // name_mei
        if (dto.getName_mei() == null || dto.getName_mei().isEmpty())
        {
            errorMessages.add("名前の項目が未入力です。");
            System.out.print("こちらの入力は" + dto.getName_mei());
        }
        else if (dto.getName_mei().length() > 256)
        {
            errorMessages.add("苗字が長すぎです。");
        }

        return errorMessages;
    }
}
