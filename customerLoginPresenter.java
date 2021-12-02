package com.example.b07storeapp;

public class customerLoginPresenter implements LoginContract.Presenter{
    private LoginContract.Model model;
    private LoginContract.View view;



    public customerLoginPresenter(LoginContract.Model model, LoginContract.View view){
        this.model = model;
        this.view = view;
    }

    public void usernameExists(){
        String user = view.getUsername();
        String password = view.getPassword();
        model.passwordMatches(this, user, password );
    }

    public void usernameNoExist() {

        view.displayMsg("Username does not exist");
    }
    public void correctPass(){
        view.startSuccessfulLoginActivity();
    }

    public void incorrectPass(){
        view.displayMsg("Incorrect Password");
    }

    @Override
    public void emptyField() {
        view.displayMsg("Cannot have empty fields");
    }

    public void checkLogin() {
        String user = view.getUsername();
        String password = view.getPassword();
        if(user.equals("") || password.equals("")) {
            emptyField();

        }
        else {
            model.userExists(this, user);
        }


    }


}
