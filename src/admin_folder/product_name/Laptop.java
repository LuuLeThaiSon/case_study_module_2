package admin_folder.product_name;

import admin_folder.Product;
import admin_folder.Screen;

import java.io.Serializable;

public class Laptop extends Product implements Serializable {
    private int memory;
    private int memoryRam;
    private Screen screen;

    public Laptop() {}

    public Laptop(String id, String brand, String name, int memory, int memoryRam, Screen screen, long price, int quantity, String describe) {
        super(id, brand, name, price, quantity, describe);
        this.memory = memory;
        this.memoryRam = memoryRam;
        this.screen = screen;
    }

    public Laptop(String id, String brand, String name, int memory, int memoryRam, Screen screen) {
        super(id, brand, name);
        this.memory = memory;
        this.memoryRam = memoryRam;
        this.screen = screen;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getMemoryRam() {
        return memoryRam;
    }

    public void setMemoryRam(int memoryRam) {
        this.memoryRam = memoryRam;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Laptop {" +
                super.toString() +
                ", Bộ nhớ: " + memory +
                ", Ram: " + memoryRam +
                ", Kích thước màn hình: " + screen.getSize() +
                ", Độ phân giải màn hình: " + screen.getResolution() +
                ", Mô tả: " + getDescribe() +
                ", Giá: " + getPrice() +
                ", Số lượng: " + getQuantity() +
                '}';
    }
}
