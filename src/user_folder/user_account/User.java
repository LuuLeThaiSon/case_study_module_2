package user_folder.user_account;

import admin_folder.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String account;
    private String passWord;

    public User(String name, int age, String email, String phoneNumber, String account, String passWord) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.passWord = passWord;
    }

    public User() {}
    public User(String account, ArrayList<Product> arrayList) {
        this.account = account;
        arrayList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
