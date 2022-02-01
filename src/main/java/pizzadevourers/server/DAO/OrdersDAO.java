package pizzadevourers.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pizzadevourers.server.Orders;

@Repository
public class OrdersDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(final Orders order){
        mongoTemplate.insert(order);
    }
}
