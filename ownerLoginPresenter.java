package com.example.b07storeapp;



public class ownerLoginPresenter implements LoginContract.Presenter {
    private LoginContract.Model model;
    private LoginContract.View view;


    public ownerLoginPresenter(LoginContract.Model model, LoginContract.View view){
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
        if(user.equals("") || password.equals("")){
            emptyField();
        }
        else {
            model.userExists(this, user);
        }
       // boolean user_found = model.userExists(user);
        /*boolean pass_match = model.passwordMatches(user, password);
        if(!exists){
            view.displayMsg("Username does not exist");
        }
        else{
            if(!pass_match){
                view.displayMsg("Incorrect Password");
            }
            else{
                view.startSuccessfulLoginActivity();
            }

        }*/

    }


}
