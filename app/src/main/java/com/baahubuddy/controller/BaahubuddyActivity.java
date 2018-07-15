package com.baahubuddy.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baahubuddy.R;
import com.baahubuddy.db.dao.LoginDao;
import com.baahubuddy.model.Login;

import java.io.File;
import java.util.List;

public class BaahubuddyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static Context context = null;
    String TAG = BaahubuddyActivity.class.getName();
    TextView profileName;
    ImageView profilePicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baahubuddy);
        context = this;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Object object = null;
        if (bundle != null && (object = bundle.get("resp")) != null) {
            Toast.makeText(getApplicationContext(), object.toString(), Toast.LENGTH_LONG).show();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        profileName = navigationView.getHeaderView(0).findViewById(R.id.profileName);
        Log.e(TAG, "profileName = " + profileName);
        profileName.setOnClickListener(this);
        profilePicView = navigationView.getHeaderView(0).findViewById(R.id.profilePicView);
        Log.e(TAG, "profilePicView = " + profilePicView);
        profilePicView.setOnClickListener(this);

        List<Login> logins = new LoginDao(getApplicationContext()).get(null);
        if (logins.size() > 0) {
            Login login = logins.get(0);
            if (login != null && login.getPic() != null && !"".equals(login.getPic())) {
                File imgFile = new File(login.getPic());
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    profilePicView.setImageBitmap(myBitmap);
                }
            }
            if (login != null && login.getName() != null && !"".equals(login.getName())) {
                profileName.setText(login.getName());
            } else {
                if (login != null && login.getUsername() != null && !"".equals(login.getUsername())) {
                    profileName.setText(login.getUsername());
                }
            }

        }

        displaySelectedScreen(R.id.nav_home);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showConfirmationDialog();
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        displaySelectedScreen(menuItem.getItemId());
        return true;
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home: {
                fragment = new HomeActivityFragment();
                break;
            }
            case R.id.nav_exit: {
                showConfirmationDialog();
                break;
            }
            case R.id.nav_share: {
                break;
            }
            case R.id.nav_donate: {
                break;
            }
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profileName: {

                break;
            }
            case R.id.profilePicView: {
                String filePath = imageFileExplorer();
                Log.e(TAG, "filePath = " + filePath);
                break;
            }
        }
    }

    private String imageFileExplorer() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(R.layout.file_explorer);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        return null;
    }
}
