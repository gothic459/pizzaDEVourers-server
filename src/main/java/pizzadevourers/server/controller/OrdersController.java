package pizzadevourers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pizzadevourers.server.DAO.OrdersDAO;
import pizzadevourers.server.Orders;

@RestController
public class OrdersController {
    @Autowired
    private OrdersDAO ordersDao;

    @PostMapping("/sendOrder")
    public void addOrder(@RequestBody final Orders order){
        ordersDao.save(order);
    }
}
