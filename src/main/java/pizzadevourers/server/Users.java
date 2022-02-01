package pizzadevourers.server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Document
public class Users {
    @Id
    private String _id;

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String telephone;
    private String created_on;



    public Users() {
    }

    public String hash(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }

    public boolean verifyHash(String password, String hash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.matches(password, hash);
    }


    public boolean containsAllRequiredFields(){
        Pattern singleWord3to50chars = Pattern.compile("^[A-Za-z]{3,50}$");
        Pattern multipleWords3to100chars = Pattern.compile("^.{3,100}$");
        Pattern phonePattern = Pattern.compile("(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)");
        ArrayList<Matcher> matcher = new ArrayList<>();

        matcher.add(singleWord3to50chars.matcher(username));
        matcher.add(multipleWords3to100chars.matcher(password));
        matcher.add(singleWord3to50chars.matcher(first_name));
        matcher.add(multipleWords3to100chars.matcher(last_name));
        matcher.add(multipleWords3to100chars.matcher(address));
        matcher.add(phonePattern.matcher(telephone));

        for (Matcher m: matcher) {
            if(!m.matches()){
                return false;
            }
        }
        return true;
    }




    public String get_id() {
        return _id;
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

    public void setPassword(String password) {this.password = hash(password);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

}
