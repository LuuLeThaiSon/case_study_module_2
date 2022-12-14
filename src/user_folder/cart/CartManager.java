package user_folder.cart;

import admin_folder.Product;
import admin_folder.product_name.Laptop;
import admin_folder.product_name.Phone;
import admin_folder.product_name.Tablet;
import read_write_file.ReadWriteFile;
import user_folder.user_account.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.jakewharton.fliptables.*;

public class CartManager implements Serializable {
    ArrayList<Cart> carts;

    ArrayList<Product> products;

    ArrayList<OderCart> productCartArrayList;

    ReadWriteFile<ArrayList<Product>> readWriteFileProduct = new ReadWriteFile<>();
    ReadWriteFile<ArrayList<OderCart>> readWriteFileOderCart = new ReadWriteFile<>();
    ReadWriteFile<ArrayList<Cart>> readWriteFileCart = new ReadWriteFile<>();

    public CartManager(User user) {
        if (readFileCart() == null) {
            carts = new ArrayList<>();
        } else {
            carts = readFileCart();
        }

        if (readFileProduct() == null) {
            products = new ArrayList<>();
        } else {
            products = readFileProduct();
        }

        if (readFileOderCart() == null) {
            productCartArrayList = new ArrayList<>();
        } else {
            productCartArrayList = readFileOderCart();
        }
    }

    public void writeFile() {
        readWriteFileCart.writeFile(carts, "src/user_folder/cart/my_cart/my_cart.txt");
    }

    public void writeFileProduct() {
        readWriteFileProduct.writeFile(products, "src/admin_folder/product_data/product_list.txt");
    }

    public ArrayList<Cart> readFileCart() {
        return readWriteFileCart.readFile("src/user_folder/cart/my_cart/my_cart.txt");
    }

    public ArrayList<OderCart> readFileOderCart() {

        return readWriteFileOderCart.readFile("src/user_folder/cart/my_cart/my_oder_cart.txt");
    }

    public void writeFileOderCart() {
        readWriteFileOderCart.writeFile(productCartArrayList, "src/user_folder/cart/my_cart/my_oder_cart.txt");
    }

    public ArrayList<Product> readFileProduct() {
        return readWriteFileProduct.readFile("src/admin_folder/product_data/product_list.txt");
    }

    public void addCart(Scanner scanner, User user) {
        boolean flag = false;
        System.out.print("Nh???p m?? s???n ph???m: ");
        String id = scanner.nextLine();

        System.out.print("Nh???p s??? l?????ng: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        for (Product p : products) {
            if (p.getId().equals(id)) {
                if (quantity > p.getQuantity()) {
                    System.out.println("S??? l?????ng s???n ph???m trong kho hi???n c?? l??: " + p.getQuantity());
                    System.out.println("M???i nh???p l???i");
                    break;
                } else {
                    OderCart oderCart = new OderCart(p, quantity);
                    productCartArrayList.add(oderCart);
                    int index = indexUser(user);
                    Cart cart = new Cart(user, productCartArrayList);
                    carts.set(index, cart);
                    flag = true;
                }
            }
        }
        if (flag) {
            System.out.println("???? th??m m?? [" + id + "] v??o gi??? h??ng");
        } else {
            System.out.println("Kh??ng c?? s???n ph???m m?? " + id);
        }

        writeFile();
        writeFileOderCart();
    }

    public void payCart(Scanner scanner, User user) {
        int indexUser = indexUser(user);
        double totalPay = 0;
        if (checkCartEmpty(user)) {
            System.out.print("?????ng ?? mua h??ng (Y / N): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                for (int i = 0; i < carts.get(indexUser).getCart().size(); i++) {
                    totalPay += carts.get(indexUser).getCart().get(i).getProduct().getPrice() *
                            carts.get(indexUser).getCart().get(i).getProduct().getQuantity();
                    for (Product product : products) {
                        if (product.getId().equals(carts.get(indexUser).getCart().get(i).getProduct().getId())) {
                            product.setQuantity(product.getQuantity() - carts.get(indexUser).getCart().get(i).getQuantity());
                        }
                    }
                }
                carts.get(indexUser).setCart(null);
                productCartArrayList.clear();
                System.out.println("Gi?? b???n ph???i thanh to??n l??: " + totalPay + "VN??");
            } else {
                System.out.println("???? h???y l???nh thanh to??n");
            }
        } else {
            System.out.println("Gi??? h??ng r???ng");
        }

        writeFile();
        writeFileOderCart();
        writeFileProduct();
    }

    public void displayCart(User user) {
        int indexUser = indexUser(user);
        if (checkCartEmpty(user)) {
            String[] headers = {"M?? s???n ph???m", "Lo???i s???n ph???m", "T??n s???n ph???m", "Nh??n h??ng", "Gi??", "S??? l?????ng"};
            Object[][] data = new Object[carts.get(indexUser).getCart().size()][6];
            for (int i = 0; i < carts.get(indexUser).getCart().size(); i++) {
                if (carts.get(indexUser).getCart().get(i).getProduct() instanceof Phone) {
                    data[i] = new Object[]{carts.get(indexUser).getCart().get(i).getProduct().getId(), "??i???n tho???i",
                            carts.get(indexUser).getCart().get(i).getProduct().getName(),
                            carts.get(indexUser).getCart().get(i).getProduct().getBrand(),
                            carts.get(indexUser).getCart().get(i).getProduct().getPrice(),
                            carts.get(indexUser).getCart().get(i).getQuantity()};
                }
                if (carts.get(indexUser).getCart().get(i).getProduct() instanceof Laptop) {
                    data[i] = new Object[]{carts.get(indexUser).getCart().get(i).getProduct().getId(), "Laptop",
                            carts.get(indexUser).getCart().get(i).getProduct().getName(),
                            carts.get(indexUser).getCart().get(i).getProduct().getBrand(),
                            carts.get(indexUser).getCart().get(i).getProduct().getPrice(),
                            carts.get(indexUser).getCart().get(i).getQuantity()};
                } else if (carts.get(indexUser).getCart().get(i).getProduct() instanceof Tablet) {
                    data[i] = new Object[]{carts.get(indexUser).getCart().get(i).getProduct().getId(), "M??y t??nh b???ng",
                            carts.get(indexUser).getCart().get(i).getProduct().getName(),
                            carts.get(indexUser).getCart().get(i).getProduct().getBrand(),
                            carts.get(indexUser).getCart().get(i).getProduct().getPrice(),
                            carts.get(indexUser).getCart().get(i).getQuantity()};
                }
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        } else {
            System.out.println("Gi??? h??ng r???ng");
        }
    }

    public int indexUser(User user) {
        if (checkUser(user)) {
            for (int i = 0; i < carts.size(); i++) {
                if (carts.get(i).getCartID().getAccount().equals(user.getAccount())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean checkUser(User user) {
        for (Cart cart : carts) {
            if (cart.getCartID().getAccount().equals(user.getAccount())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCartEmpty(User user) {
        if (carts.get(indexUser(user)).getCart() == null) {
            return false;
        }
        return true;
    }

}
