package read_write_file;

import java.io.*;

public class ReadWriteFile <E> {
    public void writeFile(E e, String path) {
        File file = new File(path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(e);
            objectOutputStream.close();
        } catch (Exception exception) {
            System.out.print("");
        }
    }

    public E readFile(String path) {
        File file = new File(path);
        E arrayList = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                arrayList = (E) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return arrayList;
    }
}
