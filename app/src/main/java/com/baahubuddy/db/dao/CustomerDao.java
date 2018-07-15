package com.baahubuddy.db.dao;

import android.content.Context;
import android.database.Cursor;

import com.baahubuddy.db.Dbcon;
import com.baahubuddy.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends Dbcon {

    public CustomerDao(Context context) {
        super(context);
    }

    public int save(Customer customer) {
        int j = 0, k = 0;
        String valueString = "", columnString = "";
        init();
        String sql = "";
        if (customer.getId() == 0) {
            customer.setId(getMaxId() + 1);
        }
        if (customer != null && customer.getId() != 0 && !"".equals(customer.getId())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + customer.getId() + "'";
            columnString += "id";
            j++;
        }
        if (customer != null && customer.getName() != null && !"".equals(customer.getName())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + customer.getName() + "'";
            columnString += "name";
            j++;
        }
        if (customer != null && customer.getPhone() != null && !"".equals(customer.getPhone())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + customer.getPhone() + "'";
            columnString += "phone";
            j++;
        }
        if (customer != null && customer.getPhoto() != null && !"".equals(customer.getPhoto())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + customer.getPhoto() + "'";
            columnString += "photo";
            j++;
        }
        if (customer != null && customer.getAddress() != null && !"".equals(customer.getAddress())) {
            if (j != 0) {
                valueString += ",";
                columnString += ",";
            }
            valueString += "'" + customer.getAddress() + "'";
            columnString += "address";
            j++;
        }
        if (!"".equals(columnString) && !"".equals(valueString))
            sql = "insert into customer(" + columnString + ") values(" + valueString + ");";
        if (!"".equals(sql))
            k = putData(sql);
        close();
        return k;
    }

    public int update(Customer condetionCustomer, Customer customer) {
        int i = 0, j = 0, k = 0;
        String condetionString = "", valueString = "", sql = "";
        init();
        if (condetionCustomer != null && condetionCustomer.getId() != 0 && !"".equals(condetionCustomer.getId())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "id = '" + condetionCustomer.getId() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getName() != null && !"".equals(condetionCustomer.getName())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "name = '" + condetionCustomer.getName() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getPhone() != null && !"".equals(condetionCustomer.getPhone())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "phone = '" + condetionCustomer.getPhone() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getPhoto() != null && !"".equals(condetionCustomer.getPhoto())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "photo = '" + condetionCustomer.getPhoto() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getAddress() != null && !"".equals(condetionCustomer.getAddress())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "address = '" + condetionCustomer.getAddress() + "'";
            i++;
        }
        if (customer != null && customer.getId() != 0 && !"".equals(customer.getId())) {
            if (j != 0) valueString += ",";
            valueString += "id = '" + customer.getId() + "'";
            j++;
        }
        if (customer != null && customer.getName() != null && !"".equals(customer.getName())) {
            if (j != 0) valueString += ",";
            valueString += "name = '" + customer.getName() + "'";
            j++;
        }
        if (customer != null && customer.getPhone() != null && !"".equals(customer.getPhone())) {
            if (j != 0) valueString += ",";
            valueString += "phone = '" + customer.getPhone() + "'";
            j++;
        }
        if (customer != null && customer.getPhoto() != null && !"".equals(customer.getPhoto())) {
            if (j != 0) valueString += ",";
            valueString += "photo = '" + customer.getPhoto() + "'";
            j++;
        }
        if (customer != null && customer.getAddress() != null && !"".equals(customer.getAddress())) {
            if (j != 0) valueString += ",";
            valueString += "address = '" + customer.getAddress() + "'";
            j++;
        }
        if (!"".equals(valueString)) {
            sql = "update customer set " + valueString;
            if (!"".equals(condetionString)) {
                sql += " where " + condetionString;
            }
        }
        if (!"".equals(sql))
            k = putData(sql);
        close();
        return k;
    }

    public List<Customer> get(Customer condetionCustomer) {
        List<Customer> customers = new ArrayList<>();
        int i = 0;
        String condetionString = "", sql = "select id,name,phone,photo,address from customer";
        init();
        if (condetionCustomer != null && condetionCustomer.getId() != 0 && !"".equals(condetionCustomer.getId())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "id = '" + condetionCustomer.getId() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getName() != null && !"".equals(condetionCustomer.getName())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "name = '" + condetionCustomer.getName() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getPhone() != null && !"".equals(condetionCustomer.getPhone())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "phone = '" + condetionCustomer.getPhone() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getPhoto() != null && !"".equals(condetionCustomer.getPhoto())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "photo = '" + condetionCustomer.getPhoto() + "'";
            i++;
        }
        if (condetionCustomer != null && condetionCustomer.getAddress() != null && !"".equals(condetionCustomer.getAddress())) {
            if (i != 0) condetionString += " AND ";
            condetionString += "address = '" + condetionCustomer.getAddress() + "'";
            i++;
        }
        if (!"".equals(condetionString))
            sql += " where " + condetionString;
        Cursor cursor = getData(sql);
        while (cursor != null && cursor.moveToNext()) {
            Customer customer = new Customer(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            customers.add(customer);
        }
        close();
        return customers;
    }

    public int getMaxId() {
        int id = 0;
        String sql = "select max(id) from customer";
        Cursor cursor = getData(sql);
        while (cursor != null && cursor.moveToNext()) {
            id = Integer.parseInt(cursor.getString(0));
        }
        close();
        return id;
    }

}
