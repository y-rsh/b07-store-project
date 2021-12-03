package com.example.b07storeapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)


public class LoginUnitTests {

    @Mock
    customer_login customer_view;

    @Mock
    owner_login owner_view;

    @Mock
    LoginModel model;


    @Test
    public void testEmptyUserCustomer(){
        /**  stubbing **/
        when(customer_view.getUsername()).thenReturn("");
        when(customer_view.getPassword()).thenReturn("hello");

        LoginPresenter presenter = new LoginPresenter(model, customer_view);
        presenter.checkCustomerLogin();

        verify(customer_view).displayMsg("Cannot have empty fields");
    }

    @Test
    public void testEmptyPassCustomer(){
        /**  stubbing **/
        when(customer_view.getUsername()).thenReturn("bob1234");
        when(customer_view.getPassword()).thenReturn("");

        LoginPresenter presenter = new LoginPresenter(model, customer_view);
        presenter.checkCustomerLogin();

        verify(customer_view).displayMsg("Cannot have empty fields");
    }

    @Test
    public void invokeUserExists(){
        /**  stubbing **/
        when(customer_view.getUsername()).thenReturn("bob1234");
        when(customer_view.getPassword()).thenReturn("hello");


        LoginPresenter presenter = new LoginPresenter(model, customer_view);
        presenter.checkCustomerLogin();

        verify(model).userExists(presenter, "bob1234", "Customers");
    }

    @Test
    public void checkUsernameNoExist(){
        LoginPresenter presenter = new LoginPresenter(model, customer_view);

        presenter.usernameNoExist();

        verify(customer_view).displayMsg("Username does not exist");
    }

    @Test
    public void checkIncorrectPass(){
        LoginPresenter presenter = new LoginPresenter(model, customer_view);

        presenter.incorrectPass();

        verify(customer_view).displayMsg("Incorrect Password");
    }

    @Test
    public void checkCorrectPass(){
        LoginPresenter presenter = new LoginPresenter(model, customer_view);

        presenter.correctPass();

        verify(customer_view).startSuccessfulLoginActivity();
    }

    @Test
    public void checkUserExistsCustomer(){
        /**  stubbing **/
        when(customer_view.getUsername()).thenReturn("bob1234");
        when(customer_view.getPassword()).thenReturn("hello");


        LoginPresenter presenter = new LoginPresenter(model, customer_view);
        presenter.usernameExistsCustomer();

        verify(model).passwordMatches(presenter,"bob1234","hello", "Customers");
    }

    @Test
    public void testEmptyUserOwner(){
        /**  stubbing **/
        when(owner_view.getUsername()).thenReturn("");
        when(owner_view.getPassword()).thenReturn("hello");

        LoginPresenter presenter = new LoginPresenter(model, owner_view);
        presenter.checkOwnerLogin();

        verify(owner_view).displayMsg("Cannot have empty fields");
    }

    @Test
    public void testEmptyPassOwner(){
        /**  stubbing **/
        when(owner_view.getUsername()).thenReturn("bob1234");
        when(owner_view.getPassword()).thenReturn("");

        LoginPresenter presenter = new LoginPresenter(model, owner_view);
        presenter.checkOwnerLogin();

        verify(owner_view).displayMsg("Cannot have empty fields");
    }

    @Test
    public void invokeUserExistsOwner(){
        /**  stubbing **/
        when(owner_view.getUsername()).thenReturn("bob1234");
        when(owner_view.getPassword()).thenReturn("hello");


        LoginPresenter presenter = new LoginPresenter(model, owner_view);
        presenter.checkOwnerLogin();

        verify(model).userExists(presenter, "bob1234", "Owners");
    }

    @Test
    public void checkUserExistsOwner(){
        /**  stubbing **/
        when(owner_view.getUsername()).thenReturn("bob1234");
        when(owner_view.getPassword()).thenReturn("hello");


        LoginPresenter presenter = new LoginPresenter(model, owner_view);
        presenter.usernameExistsOwner();

        verify(model).passwordMatches(presenter,"bob1234","hello", "Owners");
    }


}