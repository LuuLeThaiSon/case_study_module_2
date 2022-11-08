package user_folder.user_account;
import admin_folder.product_name.ProductSystem;
import read_write_file.ReadWriteFile;
import user_folder.cart.Cart;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager implements Serializable, Regex {
    ArrayList<User> userArrayList;
    ProductSystem productSystem;
    ArrayList<Cart> carts;
    ReadWriteFile<ArrayList<User>> readWriteFileUser = new ReadWriteFile<>();
    ReadWriteFile<ArrayList<Cart>> readWriteFileCart = new ReadWriteFile<>();

    public UserManager() {
        if (readFile() == null) {
            userArrayList = new ArrayList<>();
        } else {
            userArrayList = readFile();
        }
        productSystem = new ProductSystem();
        if (readFileCart() == null) {
            carts = new ArrayList<>();
        } else {
            carts = readFileCart();
        }
    }

    public void writeFile() {
        readWriteFileUser.writeFile(userArrayList, "src/login/user_data/user_data.txt");
    }

    public void writeFileCart() {
        readWriteFileCart.writeFile(carts, "src/user_folder/cart/my_cart/my_cart.txt");
    }

    public ArrayList<User> readFile() {
        return readWriteFileUser.readFile("src/login/user_data/user_data.txt");
    }

    public ArrayList<Cart> readFileCart() {
        return readWriteFileCart.readFile("src/user_folder/cart/my_cart/my_cart.txt");
    }

    public void creatAccount(Scanner scanner) {
        System.out.println("TẠO TÀI KHOẢN");
        System.out.print("Họ và tên: ");
        String name = scanner.nextLine();
        System.out.print("Tuổi: ");
        int age = Integer.parseInt(scanner.nextLine());
        String email;
        do {
            System.out.print("Email (xxx@xxx.xxx): ");
            email = scanner.nextLine();
            if (checkRegexEmail(email)) {
                System.out.println("Nhập lại email có định dạng : [xxx@xxx.xxx]!");
            }
        } while (checkRegexEmail(email));

        String phoneNumber;
        do {
            System.out.print("Nhập số điện thoại: ");
            phoneNumber = scanner.nextLine();
            if (checkRegexPhoneNumber(phoneNumber)) {
                System.out.println("Nhập lại số điện thoại có định dạng: ");
                System.out.println("1234567890");
                System.out.println("123-456-7890");
                System.out.println("(123)456-7890");
                System.out.println("(123)4567890");
            }
        } while (checkRegexPhoneNumber(phoneNumber));

        String account;
        do {
            System.out.print("Tên đăng nhập: ");
            account = scanner.nextLine();
            if ((checkAccount(account) == -1) || (account.equals("admin"))) {
                System.out.println("Tên đăng nhập đã tồn tại. Mời nhập lại");
            }
        } while (checkAccount(account) == -1 || (account.equals("admin")));

        String passWord;
        do {
            System.out.println("Nhập mật khẩu ([0-9], [a-z], [A-Z], có tối thiểu 1 số, 1 chữ in hoa và 1 kí tự đặc biệt):");
            passWord = scanner.nextLine();
            if (checkRegexPassWord(passWord)) {
                System.out.println("Mật khẩu sai định dạng. Mời nhập lại");
            }
        } while (checkRegexPassWord(passWord));

        System.out.println("Tạo tài khoản thành công");
        User user = new User(name, age, email, phoneNumber, account, passWord);
        userArrayList.add(user);
        carts.add(new Cart(user));

        writeFile();
        writeFileCart();
    }

    public boolean checkRegexEmail(String str) {
        Pattern pattern = Pattern.compile("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*");
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }

    public boolean checkRegexPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }

    public boolean checkRegexPassWord(String str) {
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }

    public int checkAccount(String str) {
        for (User user : userArrayList) {
            if (user.getAccount().equals(str)) {
                return -1;
            }
        }
        return 1;
    }

    public void logout() {
        UserSystem userSystem = new UserSystem();
        userSystem.bigMenu();
    }

}
