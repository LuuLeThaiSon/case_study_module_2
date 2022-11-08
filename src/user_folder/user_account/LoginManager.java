package user_folder.user_account;

import admin_folder.product_name.ProductSystem;
import read_write_file.ReadWriteFile;
import user_folder.UserMenuSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginManager implements Serializable {
    ArrayList<User> userArrayList;
    ProductSystem productSystem;
    UserManager userManager;
    UserMenuSystem userMenuSystem;
    ReadWriteFile<ArrayList<User>> readWriteFile = new ReadWriteFile<>();

    public LoginManager() {
        userManager = new UserManager();
        if (readFile() == null) {
            userArrayList = new ArrayList<>();
        } else {
            userArrayList = readFile();
        }
        productSystem = new ProductSystem();
        userMenuSystem = new UserMenuSystem();
    }

    public ArrayList<User> readFile() {
        return readWriteFile.readFile("src/login/user_data/user_data.txt");
    }

    public void login(Scanner scanner) {
        userArrayList = readFile();
        System.out.print("Nhập tên đăng nhập: ");
        String account = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String passWord = scanner.nextLine();

        if (account.equals("admin") && passWord.equals("admin")) {
            productSystem.productAdminMenu();
        } else {
            for (User user : userArrayList) {
                if (checkAccountExist(account)) {
                    if (checkAccountAndPassword(account, passWord)) {
                        userMenuSystem.userMenu(checkAccount(account, passWord));
                    } else if (user.getAccount().equals(account) && !user.getPassWord().equals(passWord)) {
                        System.out.println("Sai mật khẩu");
                    }
                } else {
                    System.out.println("Tài khoản không tồn tại");
                }
            }
        }
    }

    public boolean checkAccountAndPassword(String account, String passWord) {
        readFile();
        for (User user : userArrayList) {
            if ((user.getAccount().equals(account)) && (user.getPassWord().equals(passWord))) {
                return true;
            }
        }
        return false;
    }

    public User checkAccount(String account, String passWord) {
        readFile();
        for (User user : userArrayList) {
            if ((user.getAccount().equals(account)) && (user.getPassWord().equals(passWord))) {
                return user;
            }
        }
        return null;
    }

    public boolean checkAccountExist(String account) {
        readFile();
        for (User user : userArrayList) {
            if ((user.getAccount().equals(account))) {
                return true;
            }
        }
        return false;
    }

}
