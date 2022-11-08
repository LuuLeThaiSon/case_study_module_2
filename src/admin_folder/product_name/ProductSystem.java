package admin_folder.product_name;
import admin_folder.ProductManager;
import java.util.Scanner;
import com.jakewharton.fliptables.*;

public class ProductSystem {
    private final Scanner scanner;
    private final ProductManager productManager;
    public ProductSystem() {
        scanner = new Scanner(System.in);
        productManager = new ProductManager();
    }
    public void productAdminMenu() {
        do {
            try {
                String[] headers = {"ADMIN MENU"};
                String[][] data = {{ "[1] Thêm sản phẩm mới " }, {"[2] Thay đổi thông tin sản phẩm "}, {"[3] Xóa sản phẩm "},
                        {"[4] Hiển thị danh sách điện thoại "}, {"[5] Hiển thị danh sách laptop "}, {"[6] Hiển thị danh sách máy tính bảng "},
                        {"[7] Hiển thị tất cả sản phẩm "},{"[8] Hiển thị danh sách khách hàng "}, {"[0] Đăng xuất "}};
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶︎ Nhập lựa chọn: ");
                int choice1 = Integer.parseInt(scanner.nextLine());

                switch (choice1) {
                    case 1 -> addProductSubMenu();
                    case 2 -> updateProductSubMenu();
                    case 3 -> removeProductSubMenu();
                    case 4 -> {
                        productManager.readFile();
                        productManager.displayPhoneList();
                    }
                    case 5 -> {
                        productManager.readFile();
                        productManager.displayLaptopList();
                    }
                    case 6 -> {
                        productManager.readFile();
                        productManager.displayTabletList();
                    }
                    case 7 -> {
                        productManager.readFile();
                        productManager.display();
                    }
                    case 8 -> {productManager.displayUserList();}
                    case 0 -> productManager.logout();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }

    public void addProductSubMenu() {
        do {
            try {
                String[] headers = { "THÊM SẢN PHẨM" };
                String[][] data = { { "[1] Điện thoại" }, { "[2] Laptop" }, { "[3] Máy tính bảng" }, { "[0] Thoát" }  };
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶ Nhập lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice > 3 || choice < 0) {
                    System.out.println("❌ Lựa chọn không đứng. Nhập lại!");
                } else if (choice == 0) {
                    break;
                } else {
                    productManager.addProduct(choice, scanner);
                }

            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }

    public void updateProductSubMenu() {
        do {
            try {
                String[] headers = { "SỬA SẢN PHẨM" };
                String[][] data = { { "[1] Điện thoại" }, { "[2] Laptop" }, { "[3] Máy tính bảng" }, { "[0] Thoát" }  };
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶ Nhập lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice > 3 || choice < 0) {
                    System.out.println("❌ Lựa chọn không đứng. Nhập lại!");
                } else if (choice == 0) {
                    break;
                } else {
                    productManager.updateProduct(choice, scanner);
                }

            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }

    public void removeProductSubMenu() {
        while (true) {
            try {
                String[] headers = { "XÓA SẢN PHẨM" };
                String[][] data = { { "[1] Xóa theo mã sản phẩm" }, { "[2] Xóa hết tất cả sản phẩm hiện có" }, { "[0] Thoát" }  };
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶ Nhập lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice > 2 || choice < 0) {
                    System.out.println("❌ Lựa chọn không đứng. Nhập lại!");
                } else if (choice == 0) {
                    break;
                } else if (choice == 1) {
                    productManager.removeProductById(scanner);
                } else {
                    productManager.removeAll(scanner);
                }
            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        }
    }
}
