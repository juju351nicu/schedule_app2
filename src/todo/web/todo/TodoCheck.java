package todo.web.todo;

import java.util.ArrayList;
import java.util.List;

/**
 * 入力チェックを行うクラスを用意しました。
 *
 * @author PC0010
 * @since 12月3日
 */
public class TodoCheck
{
    /**
     * TodoDtoクラスの入力チェックを行うためのValueCheckメソッド
     *
     * @param dto
     * @return
     * @since 12月4日
     */
    public static List<String> todoValueCheck(TodoDto dto)
    {
        // エラーメッセージの初期化
        List<String> errorMessages = new ArrayList<String>();

        // idのチェック
        if (dto.getId() < 0)
        {
            errorMessages.add("0より小さいIdは存在しません。不正な処理です。");
        }
        // タイトルの文字数チェック
        if (dto.getTitle() == null || dto.getTitle().isEmpty())
        {
            errorMessages.add("タイトルの項目が未入力です。");
        }
        else if (dto.getTitle().length() > 45)
        {
            errorMessages.add("タイトルは45文字以内で入力してください。");
        }
        // 詳細情報のチェック
        if (dto.getDetail() == null || dto.getDetail().isEmpty())
        {
            errorMessages.add("詳細情報の項目が未入力です。");
        }
        else if (dto.getDetail().length() > 1000)
        {
            errorMessages.add("詳細情報は1000文字以内で入力してください。");
        }
        //
        if (dto.getDate_from() == null || dto.getDate_from().isEmpty())
        {
            errorMessages.add("日付(date_from)の項目が未入力です。");
        }
        else if (dto.getDate_from().length() > 11)
        {
            errorMessages.add("日付(date_from)の長さが長すぎです。");
        }
        if (dto.getDate_to() == null || dto.getDate_to().isEmpty())
        {
            errorMessages.add("日付(date_to)の項目が未入力です。");
        }
        else if (dto.getDate_to().length() > 11)
        {
            errorMessages.add("日付(date_to)の長さが長すぎです。");
        }
        if (dto.getDone_flag() == null || !(dto.getDone_flag().equals("0") || dto.getDone_flag().equals("1")))
        {
            errorMessages.add("done_flagの選択値が不正です");
        }

        return errorMessages;
    }
}
