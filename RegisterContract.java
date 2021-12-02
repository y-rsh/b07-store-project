package com.example.b07storeapp;

public interface RegisterContract {
    public interface Model{
        public void userExists(RegisterContract.Presenter p, String username);
        public void register(String username, String password);



    }

    public interface View{
        public String getUsername();
        public String getPassword();
        public String getReEnterPassword();
        public void displayMsg(String message);
        public void startSuccessfulRegistrationActivity();


    }

    public interface Presenter{
        public void checkRegister();
        public void usernameExists();
        public void usernameNoExist();
        public void emptyField();







    }
}
