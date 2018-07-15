package com.baahubuddy.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    String TAG = Validator.class.getName();

    public boolean isNameValid(String name) {
        String regx = "[A-Z][a-z]+(( )[\\.A-Z][A-za-z]*)+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public boolean isPhoneValid(String phone) {
        return true;
    }

    public boolean isEmailValid(String email) {
        return true;
    }

    public boolean isPasswordValid(String password) {
        String regx = "[A-Za-z0-9@.]+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean isPasswordMatchValid(String password, String re_enter_password) {
        return password.equals(re_enter_password);
    }

    public boolean isUsernameValid(String username) {
        String regx = "[A-Za-z0-9@.]+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public boolean isPinMatchValid(String pin, String re_enter_pin) {
        return pin.equals(re_enter_pin);
    }
}
