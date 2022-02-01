package pizzadevourers.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pizzadevourers.server.DAO.ProductDAO;
import pizzadevourers.server.Product;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductDAO productsDAO;

    @GetMapping("/menu")
    public List<Product> findProducts() {return productsDAO.findAll();}
}
