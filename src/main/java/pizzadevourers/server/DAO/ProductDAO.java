package pizzadevourers.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pizzadevourers.server.databasePojo.Product;

import java.util.List;

@Repository
public class ProductDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Product> findAll(){
        return mongoTemplate.findAll(Product.class);
    }

}
