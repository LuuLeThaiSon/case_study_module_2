package user_folder;

import admin_folder.ProductManager;
//import user_folder.cart.CartManager;
import com.jakewharton.fliptables.FlipTable;
import user_folder.cart.CartManager;
import user_folder.user_account.User;
import user_folder.user_account.UserManager;

import java.io.Serializable;
import java.util.Scanner;

public class UserMenuSystem implements Serializable {
    private final UserManager userManager;
    private final Scanner scanner;
    private final ProductManager productManager;

    public UserMenuSystem() {
        userManager = new UserManager();
        scanner = new Scanner(System.in);
        productManager = new ProductManager();
    }

    public void userMenu(User user) {
        CartManager cartManager = new CartManager(user);
        do {
            try {
                String[] headers = {"MENU " + user.getName()};
                String[][] data = {{"[1] Hiển thị danh sách điện thoại "}, {"[2] Hiển thị danh sách laptop "},
                        {"[3] Hiển thị danh sách máy tính bảng "},
                        {"[4] Hiển thị tất cả sản phẩm "},
                        {"[5] Sắp xếp sản phẩm theo giá"},
                        {"[6] Tìm sản phẩm theo tên "},
                        {"[7] Giỏ hàng "},
                        {"[0] Đăng xuất "}};
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶︎ Nhập lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> {
                        productManager.readFile();
                        productManager.displayPhoneList();
                    }
                    case 2 -> {
                        productManager.readFile();
                        productManager.displayLaptopList();
                    }
                    case 3 -> {
                        productManager.readFile();
                        productManager.displayTabletList();
                    }
                    case 4 -> {
                        productManager.readFile();
                        productManager.display();
                    }
                    case 5 -> {
                        productManager.sortProductByPrice();
                    }
                    case 6 -> {productManager.searchByName(scanner);}
                    case 7 -> {
                        productManager.displayPhoneList();
                        productManager.displayLaptopList();
                        productManager.displayTabletList();
                        cartSubMenu(user);
                    }
                    case 0 -> userManager.logout();
                }
            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }

    public void cartSubMenu(User user) {
        CartManager cartManager = new CartManager(user);
        do {
            try {
                String[] headers = {"GIỎ HÀNG CỦA " + user.getName()};
                String[][] data = {{"[1] Xem các sản phẩm trong giỏ hàng "}, {"[2] Thêm vào giỏ hàng "},
                        {"[3] Thanh toán "},
                        {"[0] Thoát "}};
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶︎ Nhập lựa chọn: ");

                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 5 || choice < 0) {
                    System.out.println("❌ Nhập lại lựa chọn");
                } else {
                    if (choice == 0) {
                        productManager.readFile();
                        break;
                    } else {
                        switch (choice) {
                            case 1 -> {
//                                cartManager.readFileCart();
                                cartManager.displayCart(user);
                            }
                            case 2 -> cartManager.addCart(scanner, user);
                            case 3 -> cartManager.payCart(scanner, user);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }
}
