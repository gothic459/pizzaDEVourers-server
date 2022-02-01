package pizzadevourers.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import pizzadevourers.server.LoginUser;
import pizzadevourers.server.Users;

import java.util.List;

@Repository
public class UsersDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> findAll(){
        return mongoTemplate.findAll(Users.class);
    }

    public boolean login(LoginUser loginUser){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(loginUser.getUsername()));
        List<Users> users = mongoTemplate.find(query, Users.class);
        if(users.isEmpty()){
            return false;
        }
        else
        {
            Users userInDatabase = users.get(0);
            return userInDatabase.verifyHash(loginUser.getPassword(), userInDatabase.getPassword());
        }
    }

    public boolean register(Users user){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user.getUsername()));
        List<Users> users = mongoTemplate.find(query, Users.class);

        if(users.isEmpty()){
            mongoTemplate.insert(user);
            return true;
        }
        else
        {
            return false;
        }
    }
}
