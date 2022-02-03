package pizzadevourers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizzadevourers.server.DAO.UsersDAO;
import pizzadevourers.server.databasePojo.LoginUser;
import pizzadevourers.server.databasePojo.Users;

import java.util.List;

/**
 * Class used to exercise control over endpoint connected with users and their authentication.
 */
@RestController
public class UserController {
    /**
     * Instance of usersDAO
     */
    @Autowired
    private UsersDAO usersDAO;

    /**
     * Method processing data coming from /register endpoint.
     * @param user object of User class - required in the POST method
     * @return true if register is successful. false if user data is insufficient or does not meet the requirements.
     */
    @CrossOrigin(origins = "https://pizzadev-front.herokuapp.com")
    @PostMapping("/register")
    public boolean register(@RequestBody final Users user){
        return usersDAO.register(user);
    }

    /*
    @GetMapping("/users")
    public List<Users> findUsers(){
        return usersDAO.findAll();
    }
     */

    /**
     * Function processing data coming from /login endpoint.
     * @param loginUser object of LoginUser class - required in the POST method
     * @return JSON Web Token if register is successful. false if credentials are incorrect or username doesn't exist.
     */
    @CrossOrigin(origins = "https://pizzadev-front.herokuapp.com")
    @PostMapping("/login")
    public String login(@RequestBody final LoginUser loginUser) {return usersDAO.login(loginUser);}

}
