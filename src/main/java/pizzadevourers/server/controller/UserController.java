package pizzadevourers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizzadevourers.server.DAO.UsersDAO;
import pizzadevourers.server.LoginUser;
import pizzadevourers.server.Users;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UsersDAO usersDAO;

    @PostMapping("/register")
    public boolean register(@RequestBody final Users user){
        return usersDAO.register(user);
    }

    @GetMapping("/users")
    public List<Users> findUsers(){
        return usersDAO.findAll();
    }

    @PostMapping("/login")
    public boolean login(@RequestBody final LoginUser loginUser) {return usersDAO.login(loginUser);}
    /*
    @GetMapping("/users/{userId}")
    public Users findUser(@PathVariable final String userId){
        return usersDAO.findById(userId).orElseGet(Users::new);
    }
     */
}
