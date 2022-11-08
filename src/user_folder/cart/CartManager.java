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
import user_folder.user_account.UserManager;

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
        System.out.print("Nhập mã sản phẩm: ");
        String id = scanner.nextLine();

        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        for (Product p : products) {
            if (p.getId().equals(id)) {
                OderCart oderCart = new OderCart(p, quantity);
                oderCart.setQuantity(quantity);
                productCartArrayList.add(oderCart);
                int index = indexUser(user);
                Cart cart = new Cart(user, productCartArrayList);
                carts.set(index, cart);
                flag = true;
            }
        }
        if (flag) {
            System.out.println("Đã thêm mã [" + id + "] vào giỏ hàng");
        } else {
            System.out.println("Không có sản phẩm mã " + id);
        }
        writeFile();
        writeFileOderCart();
    }

    public void payCart(Scanner scanner, User user) {
        int indexUser = indexUser(user);
        double totalPay = 0;
        System.out.print("Đồng ý mua hàng (Y / N): ");
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
            System.out.println("Giá bạn phải thanh toán là: " + totalPay + "VNĐ");
        } else {
            System.out.println("Đã hủy lệnh thanh toán");
        }
        writeFile();
        writeFileOderCart();
        writeFileProduct();
    }

    public void displayCart(User user) {
        int indexUser = indexUser(user);
        if (checkCartEmpty(user)) {
            String[] headers = {"Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Nhãn hàng", "Giá", "Số lượng"};
            Object[][] data = new Object[carts.get(indexUser).getCart().size()][6];
            for (int i = 0; i < carts.get(indexUser).getCart().size(); i++) {
                if (carts.get(indexUser).getCart().get(i).getProduct() instanceof Phone) {
                    data[i] = new Object[]{carts.get(indexUser).getCart().get(i).getProduct().getId(), "Điện thoại",
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
                    data[i] = new Object[]{carts.get(indexUser).getCart().get(i).getProduct().getId(), "Máy tính bảng",
                            carts.get(indexUser).getCart().get(i).getProduct().getName(),
                            carts.get(indexUser).getCart().get(i).getProduct().getBrand(),
                            carts.get(indexUser).getCart().get(i).getProduct().getPrice(),
                            carts.get(indexUser).getCart().get(i).getQuantity()};
                }
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        } else {
            System.out.println("Giỏ hàng rỗng");
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
