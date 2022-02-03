package pizzadevourers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pizzadevourers.server.DAO.ProductDAO;
import pizzadevourers.server.databasePojo.Product;

import java.util.List;

/**
 * Class used to exercise control over /menu endpoint.
 */
@RestController
public class ProductController {
    /** Instance of ProductDAO */
    @Autowired
    private ProductDAO productsDAO;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/menu")
    public List<Product> findProducts() {return productsDAO.findAll();}
}
