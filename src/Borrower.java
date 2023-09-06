import java.util.ArrayList;

public class Borrower extends User{
    private String telephone;
    private ArrayList<Book> books;

    public Borrower(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
