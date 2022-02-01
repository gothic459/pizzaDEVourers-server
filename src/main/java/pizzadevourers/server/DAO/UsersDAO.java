package pizzadevourers.server.DAO;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pizzadevourers.server.databasePojo.LoginUser;
import pizzadevourers.server.databasePojo.Users;

import javax.management.InstanceAlreadyExistsException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public class UsersDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> findAll(){
        return mongoTemplate.findAll(Users.class);
    }

    public String login(LoginUser loginUser){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(loginUser.getUsername()));
        List<Users> users = mongoTemplate.find(query, Users.class);

        if(users.isEmpty()){
            return "false";
        }
        else
        {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withKeyId(users.get(0).get_id())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (20 * 60 * 1000)))
                    .sign(algorithm);

            return token;
        }
    }

    public boolean register(Users user){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user.getUsername()));
        List<Users> usersFoundInDatabase = mongoTemplate.find(query, Users.class);

        if(usersFoundInDatabase.isEmpty() && user.containsAllRequiredFields()){
            mongoTemplate.insert(user);
            return true;
        }
        else
        {
            return false;
        }
    }
}
