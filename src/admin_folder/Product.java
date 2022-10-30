package admin_folder;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private String id;
    private String brand;
    private String name;
    private long price;
    private int quantity;
    private String describe;

    public Product() {}

    public Product(String id, String brand, String name, long price,int quantity, String describe) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.describe = describe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Mã sản phẩm: '" + id + '\'' +
                ", Hãng: '" + brand + '\'' +
                ", Tên sản phẩm'" + name + '\'';
    }
}
