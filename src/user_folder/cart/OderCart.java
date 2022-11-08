package user_folder.cart;

import admin_folder.Product;

import java.io.Serializable;

public class OderCart implements Serializable {
    private Product product;
    private int quantity;

    public OderCart() {
    }

    public OderCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OderCart{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
