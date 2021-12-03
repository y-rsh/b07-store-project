package com.example.b07storeapp;

public interface RegisterContract {
    public interface Model{
        public void userExists(RegisterContract.Presenter p, String username, String type);
        public void register(String username, String password, String type);



    }

    public interface View{
        public String getUsername();
        public String getPassword();
        public String getReEnterPassword();
        public void displayMsg(String message);
        public void startSuccessfulRegistrationActivity();


    }

    public interface Presenter{
        public void checkRegisterCustomer();
        public void checkRegisterOwner();
        public void usernameExists();
        public void usernameNoExistCustomer();
        public void usernameNoExistOwner();
        public void emptyField();







    }
}
