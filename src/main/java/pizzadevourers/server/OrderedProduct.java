package pizzadevourers.server;

public class OrderedProduct {
    private String product_id;
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
