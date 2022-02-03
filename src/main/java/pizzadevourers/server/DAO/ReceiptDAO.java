package pizzadevourers.server.DAO;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pizzadevourers.server.databasePojo.OrderFromUser;
import pizzadevourers.server.databasePojo.OrderedProduct;
import pizzadevourers.server.databasePojo.Receipt;
import pizzadevourers.server.pdfGeneration.PdfReceiptGenerator;
import pizzadevourers.server.databasePojo.Product;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Class used to exercise control over endpoint connected with users and their authentication.
 */
@Repository
public class ReceiptDAO {
    /**
     * Instance of mongoTemplate
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Method saves order from /saveOrder endpoint, and creates new instance of PdfReceiptGenerator in order to generate a receipt.
     * @param userID ID of user who has ordered it
     * @param availableProducts list of Product objects from database
     * @param order instance of OrderFromUser class
     * @return generated receiptUUID
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public String save(String userID, List<Product> availableProducts, final OrderFromUser order) throws DocumentException, FileNotFoundException {
        LinkedList<Product> orderedProducts = new LinkedList<>();
        LinkedList<Integer> quantities = new LinkedList<>();
        int total = 0;
        int tax = 0;
        String receiptUUID = UUID.randomUUID().toString();

        for (OrderedProduct op: order.getOrdered_products()) {
            for (Product pr: availableProducts) {
                if(Objects.equals(op.getProductId(), pr.get_id())){
                    orderedProducts.add(pr);
                    quantities.add(op.getQuantity());
                    total += (pr.getPrice() * op.getQuantity());
                    tax += ((pr.getPrice() * op.getQuantity()) * (pr.getVat())) / 100;
                }
            }
        }

        PdfReceiptGenerator pdfReceiptGenerator = new PdfReceiptGenerator(receiptUUID, orderedProducts, quantities , total, tax);
        pdfReceiptGenerator.generateReceipt();

        mongoTemplate.insert(new Receipt(receiptUUID, userID, order.getOrdered_products() , total, tax));
        return receiptUUID;
    }
}
