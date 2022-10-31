package admin_folder.product_name;

import admin_folder.Product;
import admin_folder.Screen;

import java.io.Serializable;

public class Tablet extends Product implements Serializable {
    private Screen screen;

    public Tablet() {}

    public Tablet(String id, String brand, String name, Screen screen, long price, int quantity, String describe) {
        super(id, brand, name, price, quantity, describe);
        this.screen = screen;
    }

    public Tablet(String id, String brand, String name, Screen screen) {
        super(id, brand, name);
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Máy tính bảng {" +
                super.toString() +
                ", Kích thước màn hình: " + screen.getSize() +
                ", Độ phân giải màn hình: " + screen.getResolution() +
                ", Mô tả: " + getDescribe() +
                ", Giá: " + getPrice() +
                ", Số lượng: " + getQuantity() +
                '}';
    }
}
