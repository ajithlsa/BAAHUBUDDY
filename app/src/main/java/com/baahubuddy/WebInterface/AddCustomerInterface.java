package com.baahubuddy.WebInterface;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

public class AddCustomerInterface {
    public Context context;

    public AddCustomerInterface(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public String getContacts(){
        StringBuffer responseBuffer = new StringBuffer();

        ArrayList<String> contactList;
        Cursor cursor;
        int counter;

//        if (!mayRequestContacts()) {
//            return;
//        }
//
//        contactList = new ArrayList<String>();
//
//        String phoneNumber = null;
//        String email = null;
//
//        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
//        String _ID = ContactsContract.Contacts._ID;
//        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
//        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
//
//        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
//        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
//
//        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
//        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
//        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
//
//        StringBuffer output;
//
//        ContentResolver contentResolver = getContentResolver();
//
//        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
//
//        // Iterate every contact in the phone
//        if (cursor.getCount() > 0) {
//
//            counter = 0;
//            while (cursor.moveToNext()) {
//                output = new StringBuffer();
//
//                // Update the progress message
//                updateBarHandler.post(new Runnable() {
//                    public void run() {
//                        pDialog.setMessage("Reading contacts : " + counter++ + "/" + cursor.getCount());
//                    }
//                });
//
//                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
//                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
//
//                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
//
//                if (hasPhoneNumber > 0) {
//
//                    output.append("\n First Name:" + name);
//
//                    //This is to read multiple phone numbers associated with the same contact
//                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
//
//                    while (phoneCursor.moveToNext()) {
//                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
//                        output.append("\n Phone number:" + phoneNumber);
//
//                    }
//
//                    phoneCursor.close();
//                }
//            }
//        }
//
//
//        for (int i = 0; i < 10; i++) {
//            if(i>0)responseBuffer.append(";");
//            responseBuffer.append("Ajith"+(i+1)+":");
//            for (int j = 0; j < 3; j++) {
//                if(j>0)responseBuffer.append(",");
//                responseBuffer.append("9745539573");
//            }
//        }

        return responseBuffer.toString();
    }

    private boolean mayRequestContacts() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true;
//        }
//        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        } else {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        }
        return false;
    }
}
