package user_folder.user_account;

import com.jakewharton.fliptables.FlipTable;

import java.io.Serializable;
import java.util.Scanner;

public class UserSystem implements Serializable {
    private final LoginManager manager;
    private final Scanner scanner;

    private final UserManager userManager;

    public UserSystem() {
        manager = new LoginManager();
        scanner = new Scanner(System.in);
        userManager = new UserManager();
    }

    public void bigMenu() {
        do {
            try {
                String[] headers = {"WELCOME TO MY SHOP"};
                String[][] data = {{ "[1] Đăng nhập " }, {"[2] Đăng ký tài khoản mới "}, {"[0] Thoát chương trình"} };
                System.out.println(FlipTable.of(headers, data));

                System.out.print("▷▶︎ Nhập lựa chọn: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> manager.login(scanner);
                    case 2 -> userManager.creatAccount(scanner);
                    case 0 -> System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        } while (true);
    }

}
