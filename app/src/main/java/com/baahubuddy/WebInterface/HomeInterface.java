package com.baahubuddy.WebInterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class HomeInterface {
    public Context context;

    public HomeInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public String onLoad() {
        StringBuffer response = new StringBuffer();

        response.append("<table class=\"home_list\"><tr><th>Name</th><th><span class=\"credit\">CREDIT</span>&nbsp;/&nbsp;<span class=\"debit\">DEBIT</span></th></tr>");

        response.append("<tr class=\"data\" ref=\"");
        response.append("customerId");
        response.append("\"><td>");
        response.append("Ajith Lal");
        response.append("</td><td><span class=\"credit\">Rs. ");
        response.append("50000");
        response.append("</span></td></tr>");

        response.append("<tr class=\"data\" ref=\"");
        response.append("1255");
        response.append("\"><td>");
        response.append("Subrahmanyan");
        response.append("</td><td><span class=\"debit\">Rs. ");
        response.append("50");
        response.append("</span></td></tr>");

        response.append("</table>");

        return response.toString();
    }
}
