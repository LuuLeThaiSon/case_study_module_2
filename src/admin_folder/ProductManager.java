package admin_folder;

import admin_folder.product_name.Laptop;
import admin_folder.product_name.Phone;
import admin_folder.product_name.Tablet;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    ArrayList<Product> products;

    public ProductManager() {
        products = readFile();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void writeFile() {
        File file = new File("src/admin_folder/product_data/product_list.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(products);
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> readFile() {
        File file = new File("src/admin_folder/product_data/product_list.txt");
        ArrayList<Product> productArrayList = new ArrayList<>();

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                productArrayList = (ArrayList<Product>) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return productArrayList;
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
                    System.out.print("Kích thước màn hình: ");
                    float screenSize = Float.parseFloat(scanner.nextLine());
                    System.out.print("Độ phân giải màn hình: ");
                    int screenResolution = Integer.parseInt(scanner.nextLine());

                    Screen laptopScreen = new Screen(screenSize, screenResolution);
                    Laptop laptop = new Laptop(id, brand, name, memory, memoryRam, laptopScreen);

                    products.add(sameProperty(laptop, scanner));
                } else {
                    System.out.print("Kích thước màn hình: ");
                    float screenSize = Float.parseFloat(scanner.nextLine());
                    System.out.print("Độ phân giải màn hình: ");
                    int screenResolution = Integer.parseInt(scanner.nextLine());
                    Screen tabletScreen = new Screen(screenSize, screenResolution);

                    Tablet tablet = new Tablet(id, brand, name, tabletScreen);
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
        readFile();
        if (products.isEmpty()) {
            System.out.println("❌ Danh sách sản phẩm trống");
        } else {
            System.out.println("===================================================================================================================================");

            System.out.printf("%-30s%-30s%-30s%-30s%s",
                        "| Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Giá sản phẩm","Số lượng  |\n");
                for (Product product : products) {
                    if (product instanceof Phone) {
                        System.out.printf("%-30s%-29s%-28s%-20s%s",
                                product.getId(), "Điện thoại", product.getName(),
                                product.getPrice(), product.getQuantity() + "\n");
                    } else if (product instanceof Laptop){
                        System.out.printf("%-34s%-29s%-28s%-20s%s",
                                product.getId(), "Laptop", product.getName(),
                                product.getPrice(), product.getQuantity() + "\n");
                    } else {
                        System.out.printf("%-34s%-29s%-28s%-20s%s",
                                product.getId(), "Máy tính bảng", product.getName(),
                                product.getPrice(), product.getQuantity() + "\n");
                    }
                }
        }
    }

    public void displayPhoneList() {
        for (Product product : products) {
            if (product instanceof Phone) {
                System.out.println(product);
            }
        }
    }

    public void displayLaptopList() {
        for (Product product : products) {
            if (product instanceof Laptop) {
                System.out.println(product);
            }
        }
    }

    public void displayTabletList() {
        for (Product product : products) {
            if (product instanceof Tablet) {
                System.out.println(product);
            }
        }
    }
}
