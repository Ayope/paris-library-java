import javax.xml.crypto.Data;
import java.sql.Connection;

public class User {
    private String fullname;
    private String email;
    private String password;

    public void register(String fullname, String email, String password){
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        System.out.println("You registered successfully");
    }

    public boolean login(String fullname, String password){
        if(this.fullname != null && this.password != null){
            if(this.fullname.equalsIgnoreCase(fullname) && this.password.equalsIgnoreCase(password)){
                System.out.println("Welcome " + fullname);
                return true;
            }else{
                System.out.println("Wrong Inputs");
            }
        }else {
            System.out.println("Register First");
        }

        return false;
    }
}
