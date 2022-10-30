package admin_folder.product_name;

import admin_folder.Product;
import admin_folder.Screen;

public class Laptop extends Product {
    private int memory;
    private int memoryRam;
    private Screen screen;

    public Laptop() {}

    public Laptop(String id, String brand, String name, long price, int quantity, String describe, int memory, int memoryRam, Screen screen) {
        super(id, brand, name, price, quantity, describe);
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
        return "admin_folder.product_name.Laptop{" +
                super.toString() +
                "Bộ nhớ: " + memory +
                ", Ram: " + memoryRam +
                ", Kích thước màn hình: " + screen.getSize() +
                ", Độ phân giải màn hình: " + screen.getResolution() +
                ", Mô tả: " + getDescribe() +
                ", Giá: " + getPrice() +
                ", Số lượng: " + getQuantity() +
                '}';
    }
}
