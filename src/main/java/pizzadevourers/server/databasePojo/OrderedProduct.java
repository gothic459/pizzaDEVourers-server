package pizzadevourers.server.databasePojo;

/**
 * Class used to map products ordered by the user.
 */
public class OrderedProduct {
    /** ID of ordered product */
    private String product_id;
    /** quantity of ordered product */
    private int quantity;

    public OrderedProduct(String product_id, int amount) {
        this.product_id = product_id;
        this.quantity = amount;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "productId='" + product_id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
