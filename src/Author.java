import java.util.ArrayList;

public class Author {
    private String fullname;
    private String email;
    private ArrayList<Book> books;

    public Author(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
