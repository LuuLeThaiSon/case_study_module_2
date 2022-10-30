package admin_folder.product_name;

import admin_folder.ProductManager;

import java.util.Scanner;

public class ProductSystem {
    private final Scanner scanner;

    private final ProductManager productManager;

    public ProductSystem() {
        scanner = new Scanner(System.in);
        productManager = new ProductManager();
    }

    public void productAdminMenu() {
        do {
            System.out.println("MENU");

            try {
                System.out.println("👉[1] Thêm sản phẩm mới");
                System.out.println("👉[2] Thay đổi thông tin sản phẩm");
                System.out.println("👉[3] Xóa sản phẩm");
                System.out.println("👉[4] Hiển thị danh sách điện thoại");
                System.out.println("👉[5] Hiển thị danh sách laptop");
                System.out.println("👉[6] Hiển thị danh sách máy tính bảng");
                System.out.println("👉[7] Hiển thị tất cả sản phẩm");
                System.out.println("👉[0] Đăng xuất");

                System.out.print("Nhập lựa chọn: ");
                int choice1 = Integer.parseInt(scanner.nextLine());

                switch (choice1) {
                    case 1:
                        addProductSubMenu();
                        break;
                    case 2:
                    case 3:
                    case 7:
                        productManager.display();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }

    public void addProductSubMenu() {
        do {
            try {
                System.out.println("👉[1] Điện thoại");
                System.out.println("👉[2] Laptop");
                System.out.println("👉[3] Máy tính bảng");
                System.out.println("👉[0] Thoát");

                System.out.print("Nhập lựa chọn: ");
                int choice2 = Integer.parseInt(scanner.nextLine());

                if (choice2 == 0) {
                    break;
                } else productManager.addProduct(choice2, scanner);

            } catch (Exception e) {
                System.err.println("Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }
}
