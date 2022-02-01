package pizzadevourers.server;

public class OrderedProduct {
    private int productId;
    private int amount;

    public OrderedProduct(int productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }
}
