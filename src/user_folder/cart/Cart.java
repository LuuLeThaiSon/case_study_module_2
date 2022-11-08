package user_folder.cart;

import user_folder.user_account.User;
import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private User cartID;

    private ArrayList<OderCart> cart;

    public Cart(User user) {
        this.cartID = user;
    }

    public Cart(User user, ArrayList<OderCart> cart) {
        this.cartID = user;
        this.cart = cart;
    }

    public Cart() {
    }

    public User getCartID() {
        return cartID;
    }

    public void setCartID(User user) {
        this.cartID = user;
    }


    public ArrayList<OderCart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<OderCart> cart) {
        this.cart = cart;
    }

    public void setCart(boolean add) {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "UserName=" + getCartID().getName() +
                "cart=" + getCart() +
                '}';
    }
}
