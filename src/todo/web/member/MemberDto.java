package todo.web.member;

public class MemberDto {

    private int id;
    private String name_sei;
    private String name_mei;
    private String login_id;
    private String password;
    private String registered_date;
    private String updated_date;
    private String last_login;
    private String delete_flag;

    public MemberDto() {
         super();
    }
    public MemberDto(int id, String name_sei, String name_mei, String login_id, String password, String registered_date,
            String updated_date, String last_login, String delete_flag) {
        super();
        this.id = id;
        this.name_sei = name_sei;
        this.name_mei = name_mei;
        this.login_id = login_id;
        this.password = password;
        this.registered_date = registered_date;
        this.updated_date = updated_date;
        this.last_login = last_login;
        this.delete_flag = delete_flag;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName_sei() {
        return name_sei;
    }
    public void setName_sei(String name_sei) {
        this.name_sei = name_sei;
    }
    public String getName_mei() {
        return name_mei;
    }
    public void setName_mei(String name_mei) {
        this.name_mei = name_mei;
    }
    public String getLogin_id() {
        return login_id;
    }
    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRegistered_date() {
        return registered_date;
    }
    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }
    public String getUpdated_date() {
        return updated_date;
    }
    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
    public String getLast_login() {
        return last_login;
    }
    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
    public String getDelete_flag() {
        return delete_flag;
    }
    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }


}
