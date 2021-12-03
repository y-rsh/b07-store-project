package com.example.b07storeapp;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.Model model;
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.Model model, RegisterContract.View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void checkRegisterCustomer() {
        String user = view.getUsername();
        String password = view.getPassword();
        String reEnterPasswords = view.getReEnterPassword();
        if(user.equals("") || password.equals("") || reEnterPasswords.equals("")) {
            emptyField();
        }
        else {
            model.userExists(this, user, "Customers");
        }
    }

    @Override
    public void checkRegisterOwner() {
        String user = view.getUsername();
        String password = view.getPassword();
        String reEnterPasswords = view.getReEnterPassword();
        if(user.equals("") || password.equals("") || reEnterPasswords.equals("")) {
            emptyField();
        }
        else {
            model.userExists(this, user, "Owners");
        }
    }

    @Override
    public void usernameExists() {
        view.displayMsg("Username already exists");
    }

    @Override
    public void usernameNoExistCustomer() {
        String user = view.getUsername();
        String password = view.getPassword();
        String reEnterPasswords = view.getReEnterPassword();
        if (password.equals(reEnterPasswords)){
            model.register(user, password, "Customers");
            view.startSuccessfulRegistrationActivity();
        }
        else{
            view.displayMsg("The passwords do not match");
        }

    }

    @Override
    public void usernameNoExistOwner() {
        String user = view.getUsername();
        String password = view.getPassword();
        String reEnterPasswords = view.getReEnterPassword();
        if (password.equals(reEnterPasswords)){
            model.register(user, password, "Owners");
            view.startSuccessfulRegistrationActivity();
        }
        else{
            view.displayMsg("The passwords do not match");
        }

    }

    @Override
    public void emptyField() {
        view.displayMsg("Cannot have empty fields");
    }


}
