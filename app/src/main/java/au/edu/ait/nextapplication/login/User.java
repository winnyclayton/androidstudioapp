package au.edu.ait.nextapplication.login;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String registerFirstName;
    private String registerLastName;
    private String RegisterUsername;
    private String registerPassword;
    private String registerEmail;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterFirstName() {
        return registerFirstName;
    }

    public void setRegisterFirstName(String registerFirstName) {
        this.registerFirstName = registerFirstName;
    }

    public String getRegisterLastName() {
        return registerLastName;
    }

    public void setRegisterLastName(String registerLastName) {
        this.registerLastName = registerLastName;
    }

    public String getRegisterUsername() {
        return RegisterUsername;
    }

    public void setRegisterUsername(String registerUsername) {
        RegisterUsername = registerUsername;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }
}
