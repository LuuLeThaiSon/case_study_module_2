package admin_folder;

import java.io.Serializable;

public class Screen implements Serializable {
    private float size;
    private int resolution;

    public Screen() {}

    public Screen(float size, int resolution) {
        this.size = size;
        this.resolution = resolution;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

}
