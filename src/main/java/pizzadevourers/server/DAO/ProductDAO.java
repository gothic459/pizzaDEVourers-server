package pizzadevourers.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pizzadevourers.server.databasePojo.Product;

import java.util.List;

/**
 * Class used to exercise control over endpoint connected with users and their authentication.
 */
@Repository
public class ProductDAO {
    /**
     * Instance of mongoTemplate
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Function processing data coming from /menu endpoint.
     * @return List of Product objects which server found in the database.
     */
    public List<Product> findAll(){
        return mongoTemplate.findAll(Product.class);
    }

}
