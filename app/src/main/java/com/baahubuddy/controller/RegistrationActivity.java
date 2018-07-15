package com.baahubuddy.controller;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baahubuddy.R;
import com.baahubuddy.db.dao.LoginDao;
import com.baahubuddy.model.Login;
import com.baahubuddy.support.Validator;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    String TAG = RegistrationActivity.class.getName();
    Button emergencyLogoutButton, registrationButton;
    EditText usernameEditText, passwordEditText, reEnterPasswordEditText, pinEditText, reEnterPinEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.usernameEditText);
        usernameEditText.setOnEditorActionListener(this);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText.setOnEditorActionListener(this);
        reEnterPasswordEditText = findViewById(R.id.reEnterPasswordEditText);
        reEnterPasswordEditText.setOnEditorActionListener(this);
        pinEditText = findViewById(R.id.pinEditText);
        pinEditText.setOnEditorActionListener(this);
        reEnterPinEditText = findViewById(R.id.reEnterPinEditText);
        reEnterPinEditText.setOnEditorActionListener(this);

        emergencyLogoutButton = findViewById(R.id.emergencyLogoutButton);
        emergencyLogoutButton.setOnClickListener(this);

        registrationButton = findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.emergencyLogoutButton: {
                showConfirmationDialog();
                break;
            }
            case R.id.registrationButton: {
                registration(view);
                break;
            }
        }
    }

    private void registration(View view) {
        Validator validator = new Validator();
        boolean cancel = false;
        View focusView = null;

        usernameEditText.setError(null);
        passwordEditText.setError(null);
        reEnterPasswordEditText.setError(null);
        pinEditText.setError(null);
        reEnterPinEditText.setError(null);

        if (usernameEditText.getText().toString().isEmpty()) {
            usernameEditText.setError(getString(R.string.error_field_required));
            if (focusView == null) focusView = usernameEditText;
            cancel = true;
        } else {
            if (!validator.isUsernameValid(usernameEditText.getText().toString())) {
                usernameEditText.setError(getString(R.string.error_invalid_username));
                if (focusView == null) focusView = usernameEditText;
                cancel = true;
            }
        }

        if (passwordEditText.getText().toString().isEmpty()) {
            passwordEditText.setError(getString(R.string.error_field_required));
            if (focusView == null) focusView = passwordEditText;
            cancel = true;
        } else {
            if (passwordEditText.getText().toString().length() < 6) {
                passwordEditText.setError(getString(R.string.error_short_password));
                if (focusView == null) focusView = passwordEditText;
                cancel = true;
            } else {
                if (!validator.isPasswordValid(passwordEditText.getText().toString())) {
                    passwordEditText.setError(getString(R.string.error_invalid_password));
                    if (focusView == null) focusView = passwordEditText;
                    cancel = true;
                }
            }
        }

        if (reEnterPasswordEditText.getText().toString().isEmpty()) {
            reEnterPasswordEditText.setError(getString(R.string.error_field_required));
            if (focusView == null) focusView = reEnterPasswordEditText;
            cancel = true;
        } else {
            if (!validator.isPasswordMatchValid(passwordEditText.getText().toString(), reEnterPasswordEditText.getText().toString())) {
                reEnterPasswordEditText.setError(getString(R.string.error_password_miss_match));
                if (focusView == null) focusView = reEnterPasswordEditText;
                cancel = true;
            }
        }

        if (pinEditText.getText().toString().isEmpty()) {
            pinEditText.setError(getString(R.string.error_field_required));
            if (focusView == null) focusView = pinEditText;
            cancel = true;
        } else {
            if (pinEditText.getText().toString().length() != 4) {
                pinEditText.setError(getString(R.string.error_pin_length));
                if (focusView == null) focusView = pinEditText;
                cancel = true;
            } else {
                if (!validator.isPasswordValid(pinEditText.getText().toString())) {
                    pinEditText.setError(getString(R.string.error_invalid_pin));
                    if (focusView == null) focusView = pinEditText;
                    cancel = true;
                }
            }
        }

        if (reEnterPinEditText.getText().toString().isEmpty()) {
            reEnterPinEditText.setError(getString(R.string.error_field_required));
            if (focusView == null) focusView = reEnterPinEditText;
            cancel = true;
        } else {
            if (!validator.isPinMatchValid(pinEditText.getText().toString(), reEnterPinEditText.getText().toString())) {
                reEnterPinEditText.setError(getString(R.string.error_pin_miss_match));
                if (focusView == null) focusView = reEnterPinEditText;
                cancel = true;
            }
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            Login login = new Login(usernameEditText.getText().toString(), passwordEditText.getText().toString(), pinEditText.getText().toString(),null,null);
            new RegistrationProcess(login, view).execute();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_NEXT) {
            registration(textView);
            return true;
        }
        return false;
    }

    class RegistrationProcess extends AsyncTask<Void, Void, Boolean> {
        Login login;
        View view;
        ProgressDialog progressDialog;

        public RegistrationProcess(Login login, View view) {
            this.login = login;
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(RegistrationActivity.this,
                    getString(R.string.app_name),
                    "Registration is in progress\nKeep wait...");
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = false;
            if (new LoginDao(getApplicationContext()).save(login) > 0) result = true;
            return result;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            progressDialog.dismiss();
            if (success) {
                Intent intent = new Intent(RegistrationActivity.this, BaahubuddyActivity.class);
                intent.putExtra("resp","Registration complete.\nWelcome to Baahubuddy.");
                startActivity(intent);
                finish();
            } else {
                Snackbar.make(view, "Somthing went wrong.\nPlease try again", Snackbar.LENGTH_LONG)
                        .setAction("Registration", null).show();
            }
        }
    }
}
