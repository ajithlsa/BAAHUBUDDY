package com.baahubuddy.db.dao;

import android.content.Context;
import android.database.Cursor;

import com.baahubuddy.db.Dbcon;
import com.baahubuddy.model.Login;

import java.util.ArrayList;
import java.util.List;

public class LoginDao extends Dbcon {

    public LoginDao(Context context) {
        super(context);
    }

    public int save(Login login) {
        int j = 0, k = 0;
        String valueString = "", columnString = "";
        init();
        String sql = "";
        if (login != null && login.getUsername() != null && !"".equals(login.getUsername())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + login.getUsername() + "'";
            columnString += "username";
            j++;
        }
        if (login != null && login.getPassword() != null && !"".equals(login.getPassword())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + login.getPassword() + "'";
            columnString += "password";
            j++;
        }
        if (login != null && login.getPin() != null && !"".equals(login.getPin())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + login.getPin() + "'";
            columnString += "pin";
            j++;
        }
        if (login != null && login.getName() != null && !"".equals(login.getName())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + login.getName() + "'";
            columnString += "name";
            j++;
        }
        if (login != null && login.getPic() != null && !"".equals(login.getPic())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + login.getPic() + "'";
            columnString += "pic";
            j++;
        }
        if (!"".equals(columnString) && !"".equals(valueString))
            sql = "insert into login(" + columnString + ") values(" + valueString + ");";
        if (!"".equals(sql))
            k = putData(sql);
        close();
        return k;
    }

    public int update(Login condetionLogin, Login login) {
        int i = 0, j = 0, k = 0;
        String condetionString = "", valueString = "", sql = "";
        init();
        if (condetionLogin != null && condetionLogin.getUsername() != null && !"".equals(condetionLogin.getUsername())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "username = '" + condetionLogin.getUsername() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getPassword() != null && !"".equals(condetionLogin.getPassword())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "password = '" + condetionLogin.getPassword() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getPin() != null && !"".equals(condetionLogin.getPin())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "pin = '" + condetionLogin.getPin() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getName() != null && !"".equals(condetionLogin.getName())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "name = '" + condetionLogin.getName() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getPic() != null && !"".equals(condetionLogin.getPic())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "pic = '" + condetionLogin.getPic() + "'";
            i++;
        }
        if (login != null && login.getUsername() != null && !"".equals(login.getUsername())) {
            if (j != 0) valueString += ",";
            valueString += "username = '" + login.getUsername() + "'";
            j++;
        }
        if (login != null && login.getPassword() != null && !"".equals(login.getPassword())) {
            if (j != 0) valueString += ",";
            valueString += "password = '" + login.getPassword() + "'";
            j++;
        }
        if (login != null && login.getPin() != null && !"".equals(login.getPin())) {
            if (j != 0) valueString += ",";
            valueString += "pin = '" + login.getPin() + "'";
            j++;
        }
        if (login != null && login.getName() != null && !"".equals(login.getName())) {
            if (j != 0) valueString += ",";
            valueString += "name = '" + login.getName() + "'";
            j++;
        }
        if (login != null && login.getPic() != null && !"".equals(login.getPic())) {
            if (j != 0) valueString += ",";
            valueString += "pic = '" + login.getPic() + "'";
            j++;
        }
        if (!"".equals(valueString)) {
            sql = "update login set " + valueString;
            if (!"".equals(condetionString)) {
                sql += " where " + condetionString;
            }
        }
        if (!"".equals(sql))
            k = putData(sql);
        close();
        return k;
    }

    public List<Login> get(Login condetionLogin) {
        List<Login> logins = new ArrayList<>();
        int i = 0;
        String condetionString = "", sql = "select username,password,pin,name,pic from login";
        init();
        if (condetionLogin != null && condetionLogin.getUsername() != null && !"".equals(condetionLogin.getUsername())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "username = '" + condetionLogin.getUsername() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getPassword() != null && !"".equals(condetionLogin.getPassword())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "password = '" + condetionLogin.getPassword() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getPin() != null && !"".equals(condetionLogin.getPin())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "pin = '" + condetionLogin.getPin() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getName() != null && !"".equals(condetionLogin.getName())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "name = '" + condetionLogin.getName() + "'";
            i++;
        }
        if (condetionLogin != null && condetionLogin.getPic() != null && !"".equals(condetionLogin.getPic())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "pic = '" + condetionLogin.getPic() + "'";
            i++;
        }
        if (!"".equals(condetionString))
            sql += " where " + condetionString;
        Cursor cursor = getData(sql);
        while (cursor != null && cursor.moveToNext()) {
            Login login = new Login(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            logins.add(login);
        }
        close();
        return logins;
    }

}
