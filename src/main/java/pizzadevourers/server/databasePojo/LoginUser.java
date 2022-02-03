package pizzadevourers.server.databasePojo;

/** Class used to map data coming from /login endpoint  */
public class LoginUser {
    /** User's login */
    private String username;
    /** User's password*/
    private String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
