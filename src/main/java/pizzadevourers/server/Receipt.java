package pizzadevourers.server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document
public class Receipt {
    @Id
    private String _id;

    private String user_id;
    private List<OrderedProduct> ordered_products;
    private int amount;
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
