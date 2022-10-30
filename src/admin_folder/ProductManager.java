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
        if (choice > 3 || choice < 0) {
            System.err.println("Lựa chọn không đứng. Nhập lại!");
        } else {
            System.out.print("Mã sản phẩm: ");
            String id = scanner.nextLine();
            try {
                for (Product product : products) {
                    if (product.getId().equals(id)) {
                        System.out.println("Mã sản phẩm đã tồn tại. Mời nhập lại!");
                    } else  {
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
                            System.out.print("Thêm mô tả sản phẩm: ");
                            String describe = scanner.nextLine();
                            System.out.print("Giá sản phẩm: ");
                            long price = Long.parseLong(scanner.nextLine());
                            System.out.print("Số lượng sản phẩm: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            products.add(new Phone(id, brand, name, price, quantity, describe, simQuantity, network, category));
                        } else if (choice == 2) {
                            System.out.print("Dung lượng bộ nhớ: ");
                            int memory = Integer.parseInt(scanner.nextLine());
                            System.out.print("Dung lượng ram: ");
                            int memoryRam = Integer.parseInt(scanner.nextLine());
                            System.out.print("Kích thước màn hình: ");
                            float screenSize = Float.parseFloat(scanner.nextLine());
                            System.out.print("Độ phân giải màn hình: ");
                            int screenResolution = Integer.parseInt(scanner.nextLine());
                            System.out.print("Thêm mô tả sản phẩm: ");
                            String describe = scanner.nextLine();
                            System.out.print("Giá sản phẩm: ");
                            long price = Long.parseLong(scanner.nextLine());
                            System.out.print("Số lượng sản phẩm: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            Screen laptopScreen = new Screen(screenSize, screenResolution);

                            products.add(new Laptop(id, brand, name, price, quantity, describe, memory, memoryRam, laptopScreen));
                        } else {
                            System.out.print("Kích thước màn hình: ");
                            float screenSize = Float.parseFloat(scanner.nextLine());
                            System.out.print("Độ phân giải màn hình: ");
                            int screenResolution = Integer.parseInt(scanner.nextLine());
                            Screen tabletScreen = new Screen(screenSize, screenResolution);
                            System.out.print("Thêm mô tả sản phẩm: ");
                            String describe = scanner.nextLine();
                            System.out.print("Giá sản phẩm: ");
                            long price = Long.parseLong(scanner.nextLine());
                            System.out.print("Số lượng sản phẩm: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            products.add(new Tablet(id, brand, name, price, quantity, describe, tabletScreen));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeFile();
    }

    public void display() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
