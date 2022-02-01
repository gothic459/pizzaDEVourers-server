package pizzadevourers.server.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pizzadevourers.server.DAO.ReceiptDAO;
import pizzadevourers.server.DAO.ProductDAO;
import pizzadevourers.server.OrderFromUser;
import pizzadevourers.server.Product;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ReceiptController {
    @Autowired
    private ReceiptDAO ordersDao;
    @Autowired
    private ProductDAO productDAO;

    @PostMapping("/sendOrder")
    public void addOrder(@RequestBody final OrderFromUser orderFromUser) throws DocumentException, FileNotFoundException {
        List<Product> availableProducts = productDAO.findAll();
        ordersDao.save(availableProducts, orderFromUser);
    }
}
