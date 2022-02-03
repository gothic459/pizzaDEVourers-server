package pizzadevourers.server.databasePojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pizzadevourers.server.databasePojo.OrderedProduct;

import java.util.List;

/** Class used to collect objects in database. */
@Document
public class Receipt {
    /** ID of an record within the database */
    @Id
    private String _id;

    /** ID of user ordering */
    private String user_id;
    /** List of ordered products */
    private List<OrderedProduct> ordered_products;
    /** Total in PLN */
    private int amount;
    /** Total tax in PLN */
    private int tax;

    public Receipt(String _id, String user_id, List<OrderedProduct> productsList, int amount, int tax) {
        this._id = _id;
        this.user_id = user_id;
        this.ordered_products = productsList;
        this.amount = amount;
        this.tax = tax;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id){this._id = _id;}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<OrderedProduct> getProductsList() {
        return ordered_products;
    }

    public void setProductsList(List<OrderedProduct> productsList) {
        this.ordered_products = productsList;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTax(int tax){this.tax = tax;}

    public int getTax(){return tax;}

    @Override
    public String toString() {
        return "Orders{" +
                "_id='" + _id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", ordered_products=" + ordered_products +
                ", amount=" + amount +
                '}';
    }
}
