package login;

import user_folder.user_account.UserSystem;

import java.io.Serializable;

public class LoginMain implements Serializable {
    public static void main(String[] args) {
        UserSystem userSystem = new UserSystem();
        userSystem.bigMenu();
    }
}
