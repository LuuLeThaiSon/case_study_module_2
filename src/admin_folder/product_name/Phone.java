package admin_folder.product_name;

import admin_folder.Product;

public class Phone extends Product {
    private int simQuantity;
    private String network;
    private String category;

    public Phone() {}

    public Phone(String id, String brand, String name, long price, int quantity, String describe, int simQuantity, String network, String category) {
        super(id, brand, name, price, quantity, describe);
        this.simQuantity = simQuantity;
        this.network = network;
        this.category = category;
    }

    public int getSimQuantity() {
        return simQuantity;
    }

    public void setSimQuantity(int simQuantity) {
        this.simQuantity = simQuantity;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "admin_folder.product_name.Phone{" +
                super.toString() +
                "Số lượng sim" + simQuantity +
                ", Hỗ trợ mạng (4G, 5G): '" + network + '\'' +
                ", Mô tả: '" + category + '\'' +
                ", Giá: '" + getPrice() + '\'' +
                ", Số lượng: '" + getQuantity() + '\'' +
                '}';
    }
}
