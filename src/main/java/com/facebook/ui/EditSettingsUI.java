package com.facebook.ui;
import com.facebook.exception.*;
import com.facebook.service.UserService;
import com.facebook.ui.validator.UserValidator;
import java.io.IOException;
import java.util.Scanner;

public class EditSettingsUI {

    private UserService userService = new UserService();
    private UserValidator userValidator=new UserValidator();

    public void displayEditSettings() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        try {
            userValidator.validateUserCredentials(password,name);
            userValidator.validateUserName(name);
            userService.editSettings(password, name);
            System.out.println("User's settings successfully modified");
        } catch (FbShortPasswordException e) {
            System.out.println("Password is too short");            //parola
        } catch (FbStrongPasswordException e){
            System.out.println("Password is not strong");           //parola
        } catch (FbEmailInvalid e) {
            System.out.println("Email invalid");
        } catch (FbNameNullException e) {
            System.out.println("Empty name");                   //nume
        } catch (FbTechnicalException e) {
            e.printStackTrace();
            System.out.println("A system error appeared. Please contact your administrator");
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }
}


