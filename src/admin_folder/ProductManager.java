package admin_folder;

import admin_folder.product_name.Laptop;
import admin_folder.product_name.Phone;
import admin_folder.product_name.Tablet;
import de.vandermeer.asciitable.AsciiTable;
import read_write_file.ReadWriteFile;
import user_folder.user_account.User;
import user_folder.user_account.UserSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.jakewharton.fliptables.*;

public class ProductManager implements Serializable {
    ReadWriteFile<ArrayList<Product>> readWriteFile = new ReadWriteFile<>();
    ReadWriteFile<ArrayList<User>> readWriteFileUser = new ReadWriteFile<>();
    ArrayList<Product> products;
    ArrayList<User> users;

    public ProductManager() {
        if (readFile() == null) {
            products = new ArrayList<>();
        } else {
            products = readFile();
        }

        if (readFileUser() == null) {
            users = new ArrayList<>();
        } else {
            users = readFileUser();
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void writeFile() {
        readWriteFile.writeFile(products, "src/admin_folder/product_data/product_list.txt");
    }

    public ArrayList<Product> readFile() {
        return readWriteFile.readFile("src/admin_folder/product_data/product_list.txt");
    }

    public ArrayList<User> readFileUser() {
        return readWriteFileUser.readFile("src/login/user_data/user_data.txt");
    }

    public void addProduct(int choice, Scanner scanner) {
        try {
            System.out.print("Mã sản phẩm: ");
            String id = scanner.nextLine();
            if (checkId(id) == 1) {
                System.out.print("Hãng: ");
                String brand = scanner.nextLine();
                System.out.print("Tên sản phẩm: ");
                String name = scanner.nextLine();
                if (choice == 1) {
                    System.out.print("Nhập loại máy (Androi / Iphone): ");
                    String category = scanner.nextLine();
                    System.out.print("Số lượng sim (1 hoặc 2): ");
                    int simQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Hỗ trợ mạng (4G, 5G): ");
                    String network = scanner.nextLine();
                    Phone phone = new Phone(id, brand, name, simQuantity, network, category);
                    products.add(sameProperty(phone, scanner));
                } else if (choice == 2) {
                    System.out.print("Dung lượng bộ nhớ: ");
                    int memory = Integer.parseInt(scanner.nextLine());
                    System.out.print("Dung lượng ram: ");
                    int memoryRam = Integer.parseInt(scanner.nextLine());
                    Laptop laptop = new Laptop(id, brand, name, memory, memoryRam, addScreen(scanner));
                    products.add(sameProperty(laptop, scanner));
                } else {
                    Tablet tablet = new Tablet(id, brand, name, addScreen(scanner));
                    products.add(sameProperty(tablet, scanner));
                }
            } else if (checkId(id) == -1) {
                System.out.println("❌ Mã sản phẩm đã tồn tại. Nhập lại mã mới!");
            }
        } catch (Exception e) {
            System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
        }
        writeFile();
    }

    public Screen addScreen(Scanner scanner) {
        System.out.print("Kích thước màn hình: ");
        float screenSize = Float.parseFloat(scanner.nextLine());
        System.out.print("Độ phân giải màn hình: ");
        int screenResolution = Integer.parseInt(scanner.nextLine());
        return new Screen(screenSize, screenResolution);
    }

    public void updateProduct(int choice, Scanner scanner) {
        if (checkEmpty() == -1) {
            System.out.println("❌ Danh sách sản phẩm trống");
        } else {
            try {
                System.out.print("Mã sản phẩm: ");
                String id = scanner.nextLine();
                if (id.equals("")) {
                    System.out.println("❌ Mã sản phẩm không được để trống. Nhập lại đê!!");
                } else {
                    if (fineIndex(id) == -1) {
                        System.out.println("❌ Mã sản phẩm không tồn tại. Nhập lại đê!");
                    } else {
                        if (choice == 1) {
                            if (products.get(fineIndex(id)) instanceof Phone) {
                                updateBrandAndName(scanner, id);
                                System.out.print("Nhập loại máy (Androi / Iphone): ");
                                String category = scanner.nextLine();
                                if (!category.equals("")) {
                                    ((Phone) products.get(fineIndex(id))).setCategory(category);
                                }
                                System.out.print("Số lượng sim (1 hoặc 2): ");
                                String simQuantity = scanner.nextLine();
                                if (!simQuantity.equals("")) {
                                    ((Phone) products.get(fineIndex(id))).setSimQuantity(Integer.parseInt(simQuantity));
                                }
                                System.out.print("Hỗ trợ mạng (4G, 5G): ");
                                String network = scanner.nextLine();
                                if (!network.equals("")) {
                                    ((Phone) products.get(fineIndex(id))).setNetwork(network);
                                }
                                updateDescribePriceQuantity(scanner, id);
                                System.out.println("Chỉnh sửa thành công!");
                            } else {
                                System.out.println("Mã sản phẩm " + id + " không thuộc danh sách điện thoại");
                            }
                        } else if (choice == 2) {
                            if (products.get(fineIndex(id)) instanceof Laptop) {
                                System.out.print("Dung lượng bộ nhớ: ");
                                String memory = scanner.nextLine();
                                if (!memory.equals("")) {
                                    ((Laptop) products.get(fineIndex(id))).setMemory(Integer.parseInt(memory));
                                }
                                System.out.print("Dung lượng ram: ");
                                String memoryRam = scanner.nextLine();
                                if (!memoryRam.equals("")) {
                                    ((Laptop) products.get(fineIndex(id))).setMemoryRam(Integer.parseInt(memoryRam));
                                }
                                System.out.print("Kích thước màn hình: ");
                                String screenSize = scanner.nextLine();
                                System.out.print("Độ phân giải màn hình: ");
                                String screenResolution = scanner.nextLine();
                                if (!screenSize.equals("") && !screenResolution.equals("")) {
                                    Screen screen = new Screen(Float.parseFloat(screenSize), Integer.parseInt(screenResolution));
                                    ((Laptop) products.get(fineIndex(id))).setScreen(screen);
                                }
                                updateDescribePriceQuantity(scanner, id);
                                System.out.println("Chỉnh sửa thành công!");
                            } else {
                                System.out.println("Mã sản phẩm " + id + " không thuộc danh sách laptop");
                            }
                        } else {
                            if (products.get(fineIndex(id)) instanceof Tablet) {
                                System.out.print("Kích thước màn hình: ");
                                String screenSize = scanner.nextLine();
                                System.out.print("Độ phân giải màn hình: ");
                                String screenResolution = scanner.nextLine();
                                if (!screenSize.equals("") && !screenResolution.equals("")) {
                                    Screen screen = new Screen(Float.parseFloat(screenSize), Integer.parseInt(screenResolution));
                                    ((Tablet) products.get(fineIndex(id))).setScreen(screen);
                                }
                                updateDescribePriceQuantity(scanner, id);
                                System.out.println("Chỉnh sửa thành công!");
                            } else {
                                System.out.println("Mã sản phẩm " + id + " không thuộc danh sách máy tính bảng");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        }
        writeFile();
    }

    public void displayUserList() {
        users = readFileUser();
        if (users == null) {
            System.out.println("Danh sách khách hàng trống");
        } else {
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow(null, null, null, null, null, "DANH SÁCH KHÁCH HÀNG");
            at.addRule();
            at.addRow("Tên đăng nhập", "Họ và tên", "Tuổi", "Email", "Số điện thoại", "Mật khẩu");
            at.addRule();
            for (User user : users) {
                at.addRow(user.getAccount(), user.getName(), user.getAge(), user.getEmail(),
                        user.getPhoneNumber(), user.getPassWord());
                at.addRule();
            }
            System.out.println(at.render(150));
        }
    }

    public Product sameProperty(Product product, Scanner scanner) {
        System.out.print("Thêm mô tả sản phẩm: ");
        String describe = scanner.nextLine();
        product.setDescribe(describe);
        System.out.print("Giá sản phẩm: ");
        long price = Long.parseLong(scanner.nextLine());
        product.setPrice(price);
        System.out.print("Số lượng sản phẩm: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        product.setQuantity(quantity);
        return product;
    }

    public void removeProductById(Scanner scanner) {
        if (checkEmpty() == -1) {
            System.out.println("❌ Danh sách sản phẩm trống");
        } else {
            try {
                System.out.print("Mã sản phẩm: ");
                String id = scanner.nextLine();
                if (id.equals("")) {
                    System.out.println("❌ Mã sản phẩm không được để trống. Nhập lại đê!!");
                } else {
                    removeByIdConfirm(scanner, id);
                }
            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        }
        writeFile();
    }

    public void removeAll(Scanner scanner) {
        if (checkEmpty() == -1) {
            System.out.println("❌ Danh sách sản phẩm trống");
        } else {
            try {
                removeAllConfirm(scanner);
            } catch (Exception e) {
                System.out.println("❌ Nhập sai định dạng. Nhập lại!!");
            }
        }
        writeFile();
    }

    public int checkEmpty() {
        if (products.isEmpty()) {
            return -1;
        } else return 1;
    }

    public int fineIndex(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int checkId(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return -1;
            }
        }
        return 1;
    }

    public void updateBrandAndName(Scanner scanner, String id) {
        System.out.print("Hãng: ");
        String brand = scanner.nextLine();
        if (!brand.equals("")) {
            products.get(fineIndex(id)).setBrand(brand);
        }

        System.out.print("Tên sản phẩm: ");
        String name = scanner.nextLine();
        if (!name.equals("")) {
            products.get(fineIndex(id)).setName(name);
        }
    }

    public void updateDescribePriceQuantity(Scanner scanner, String id) {
        System.out.print("Chỉnh sửa mô tả sản phẩm: ");
        String describe = scanner.nextLine();
        if (!describe.equals("")) {
            products.get(fineIndex(id)).setDescribe(describe);
        }
        System.out.print("Giá sản phẩm: ");
        String price = scanner.nextLine();
        if (!price.equals("")) {
            products.get(fineIndex(id)).setPrice(Long.parseLong(price));
        }
        System.out.print("Số lượng sản phẩm: ");
        String quantity = scanner.nextLine();
        if (!quantity.equals("")) {
            products.get(fineIndex(id)).setQuantity(Integer.parseInt(quantity));
        }
    }

    public void removeAllConfirm(Scanner scanner) {
        System.out.print("Xác nhận xóa (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            products.clear();
            System.out.println("Đã xóa tất cả các sản phẩm");
        } else if (confirm.equalsIgnoreCase("n")) {
            System.out.println("Đã hủy bỏ lệnh xóa");
        }
    }

    public void removeByIdConfirm(Scanner scanner, String id) {
        System.out.print("Xác nhận xóa (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            products.remove(fineIndex(id));
            System.out.println("Xoá thành công sản phẩm có mã " + id);
        } else if (confirm.equalsIgnoreCase("n")) {
            System.out.println("Đã hủy bỏ lệnh xóa");
        } else {
            System.out.println("❌ Nhập sai. Hủy bỏ lệnh xóa");
        }
    }

    public void display() {
        products = readFile();
        readFile();
        if (products.isEmpty()) {
            System.out.println("❌ Danh sách sản phẩm trống");
        } else {
            String[] headers = {"Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Nhãn hàng", "Giá", "Số lượng"};
            Object[][] data = new Object[products.size()][6];
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i) instanceof Phone) {
                    data[i] = new Object[]{products.get(i).getId(), "Điện thoại", products.get(i).getName(),
                            products.get(i).getBrand(), products.get(i).getPrice(),
                            products.get(i).getQuantity()};
                }
                if (products.get(i) instanceof Laptop) {
                    data[i] = new Object[]{products.get(i).getId(), "Laptop", products.get(i).getName(),
                            products.get(i).getBrand(), products.get(i).getPrice(),
                            products.get(i).getQuantity()};
                } else if (products.get(i) instanceof Tablet) {
                    data[i] = new Object[]{products.get(i).getId(), "Máy tính bảng", products.get(i).getName(),
                            products.get(i).getBrand(), products.get(i).getPrice(),
                            products.get(i).getQuantity()};
                }
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void displayPhoneList() {
        products = readFile();
        ArrayList<Product> phoneList = new ArrayList<>();
        boolean flag = false;
        for (Product product : products) {
            if (product instanceof Phone) {
                phoneList.add(product);
                flag = true;
            }
        }

        if (flag) {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Android / Iphone", "Số lượng sim", "Hỗ trợ 4G/ 5G", "Mô tả", "Giá", "Số lượng"};
            Object[][] data = new Object[phoneList.size()][8];
            for (int i = 0; i < phoneList.size(); i++) {
                data[i] = new Object[]{phoneList.get(i).getId(), phoneList.get(i).getName(),
                        ((Phone) phoneList.get(i)).getCategory(), ((Phone) phoneList.get(i)).getSimQuantity(), ((Phone) phoneList.get(i)).getNetwork(),
                        phoneList.get(i).getDescribe(), phoneList.get(i).getPrice(), phoneList.get(i).getQuantity()
                };
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        } else {
            System.out.println("Danh sách điện thoại trống");
        }

    }

    public void displayLaptopList() {
        products = readFile();
        ArrayList<Product> laptopList = new ArrayList<>();
        boolean flag = false;
        for (Product product : products) {
            if (product instanceof Laptop) {
                laptopList.add(product);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Danh sách không có Laptop");
        } else {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Dung lượng bộ nhớ", "Dung lượng ram (G)",
                    "Kích thước màn hình", "Độ phân giải màn hình",
                    "Mô tả", "Giá", "Số lượng"};
            Object[][] data = new Object[laptopList.size()][8];
            for (int i = 0; i < laptopList.size(); i++) {
                data[i] = new Object[]{laptopList.get(i).getId(), laptopList.get(i).getName(),
                        ((Laptop) laptopList.get(i)).getMemory(), ((Laptop) laptopList.get(i)).getMemoryRam(),
                        ((Laptop) laptopList.get(i)).getScreen().getSize(), ((Laptop) laptopList.get(i)).getScreen().getResolution(),
                        laptopList.get(i).getDescribe(), laptopList.get(i).getPrice(), laptopList.get(i).getQuantity()
                };
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void displayTabletList() {
        products = readFile();
        ArrayList<Product> tabletList = new ArrayList<>();
        boolean flag = false;
        for (Product product : products) {
            if (product instanceof Tablet) {
                tabletList.add(product);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Danh sách không có máy tính bảng");
        } else {
            String[] headers = {"Mã sản phẩm", "Tên sản phẩm",
                    "Kích thước màn hình", "Độ phân giải màn hình",
                    "Mô tả", "Giá", "Số lượng"};
            Object[][] data = new Object[tabletList.size()][8];
            for (int i = 0; i < tabletList.size(); i++) {
                data[i] = new Object[]{tabletList.get(i).getId(), tabletList.get(i).getName(),
                        ((Tablet) tabletList.get(i)).getScreen().getSize(), ((Tablet) tabletList.get(i)).getScreen().getResolution(),
                        tabletList.get(i).getDescribe(), tabletList.get(i).getPrice(), tabletList.get(i).getQuantity()
                };
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void logout() {
        UserSystem userSystem = new UserSystem();
        userSystem.bigMenu();
    }

}
