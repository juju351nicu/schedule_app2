import todo.web.member.MemberDto;

public class Sample
{
    private static Boolean field1;

    public static void main(String[] args)
    {
        System.out.println(field1);

        MemberDto dto = new MemberDto();
        System.out.println(dto.getId());
        System.out.println(dto.getLogin_id());
    }
}
