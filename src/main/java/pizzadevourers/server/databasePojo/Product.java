package pizzadevourers.server.databasePojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Class used to map Products in the database */
@Document
public class Product {
    /** ID of the product in database */
    @Id
    private String _id;

    /** Name of the product */
    private String name;
    /** Description of the product */
    private String desc;
    /** Image of the product */
    private String image;
    /** Price of the product */
    private int price;
    /** Tax of the product */
    private int vat;

    public Product(String _id, String name, String desc, String image, int price, int vat) {
        this._id = _id;
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.price = price;
        this.vat = vat;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", vat=" + vat +
                '}';
    }
}
