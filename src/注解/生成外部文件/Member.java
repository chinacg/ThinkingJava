package 注解.生成外部文件;

/**
 * Created by wulei on 16/3/31.
 * 定义数据库表Member
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30) String firstName;
    @SQLString(50) String lastName;
    @SQLInteger Integer age;
    @SQLString(value = 30,constraints = @Constraints(primaryKey = true))
    String handle;
    static int memberCount;
    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getHandle() {
        return handle;
    }
    public String toString(){
        return handle;
    }
}
