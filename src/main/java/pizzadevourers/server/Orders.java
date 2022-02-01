package pizzadevourers.server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Orders {
    @Id
    private String _id;

    private String order_id;
    private String user_id;
    private List<OrderedProduct> ordered_products;
    private int amount;

    public Orders(String _id, String order_id, String user_id, List<OrderedProduct> productsList, int amount) {
        this._id = _id;
        this.order_id = order_id;
        this.user_id = user_id;
        this.ordered_products = productsList;
        this.amount = amount;
    }

    public String get_id() {
        return _id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

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
}
