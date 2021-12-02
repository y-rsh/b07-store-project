package com.example.b07storeapp;

public class ownerRegisterPresenter implements RegisterContract.Presenter{

    private RegisterContract.Model model;
    private RegisterContract.View view;

    public ownerRegisterPresenter(RegisterContract.Model model, RegisterContract.View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void checkRegister() {
        String user = view.getUsername();
        String password = view.getPassword();
        String reEnterPasswords = view.getReEnterPassword();
        if(user.equals("") || password.equals("") || reEnterPasswords.equals("")) {
            emptyField();
        }
        else {
            model.userExists(this, user);
        }
    }

    @Override
    public void usernameExists() {
        view.displayMsg("Username already exists");

    }

    @Override
    public void usernameNoExist() {
        String user = view.getUsername();
        String password = view.getPassword();
        String reEnterPasswords = view.getReEnterPassword();
       if (password.equals(reEnterPasswords)){
            model.register(user, password);
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
