package pizzadevourers.server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pizzadevourers.server.DAO.ReceiptDAO;
import pizzadevourers.server.DAO.ProductDAO;
import pizzadevourers.server.databasePojo.OrderFromUser;
import pizzadevourers.server.databasePojo.Product;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ReceiptController {
    @Autowired
    private ReceiptDAO receiptDao;
    @Autowired
    private ProductDAO productDAO;

    @PostMapping("/sendOrder")
    public String addOrder(@RequestBody final OrderFromUser orderFromUser) throws DocumentException, FileNotFoundException {
        String userJWT = orderFromUser.get_userJWT();
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .acceptExpiresAt(30)
                    .build();
            DecodedJWT jwt = verifier.verify(userJWT);

            List<Product> availableProducts = productDAO.findAll();
            String orderUUID = receiptDao.save(jwt.getKeyId(), availableProducts, orderFromUser);
            return orderUUID;
        } catch (JWTVerificationException exception){
            return "false";
        }
    }
}
