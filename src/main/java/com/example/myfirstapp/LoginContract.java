package com.example.b07storeapp;

public interface LoginContract {
    public interface Model{
        public void userExists(Presenter p, String username, String type);
        public void passwordMatches(Presenter p, String username, String password, String type);


    }

    public interface View{
        public String getUsername();
        public String getPassword();
        public void displayMsg(String message);
        public void startSuccessfulLoginActivity();


    }

    public interface Presenter{
        public void checkCustomerLogin();
        public void checkOwnerLogin();
        public void  usernameExistsCustomer();
        public void  usernameExistsOwner();
        public void  usernameNoExist();
        public void  correctPass();
        public void  incorrectPass();
        public void emptyField();





    }
}
