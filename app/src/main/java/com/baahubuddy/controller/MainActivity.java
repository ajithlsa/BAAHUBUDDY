package com.baahubuddy.controller;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baahubuddy.R;
import com.baahubuddy.db.dao.LoginDao;
import com.baahubuddy.model.Login;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = MainActivity.class.getName();
    Button emergencyLogoutButton, proceedButton;
    MainActivity mainActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        new AppLoadingTask().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.emergencyLogoutButton: {
                showConfirmationDialog();
                break;
            }
            case R.id.proceedButton: {
                proceed();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        showConfirmationDialog();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.emargency_layout_popup_header));
        builder.setMessage(getString(R.string.emargency_layout_popup_text));
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void proceed() {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    private class AppLoadingTask extends AsyncTask<Void, Void, Boolean> {


        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    getString(R.string.app_name),
                    "Please be patient.\nBaahubuddy loading...");
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected Boolean doInBackground(Void... param) {
            List<Login> logins = new LoginDao(getApplicationContext()).get(null);
            return logins.size() > 0;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            progressDialog.dismiss();
            if (success) {
                Intent intent = new Intent(MainActivity.this, BaahubuddyActivity.class);
                startActivity(intent);
                finish();
            } else {
                emergencyLogoutButton = findViewById(R.id.emergencyLogoutButton);
                emergencyLogoutButton.setOnClickListener(mainActivity);

                proceedButton = findViewById(R.id.proceedButton);
                proceedButton.setOnClickListener(mainActivity);
            }
        }
    }
}
