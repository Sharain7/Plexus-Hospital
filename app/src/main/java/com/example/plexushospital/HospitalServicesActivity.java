package com.example.plexushospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HospitalServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_services);
        initBottomSheet();
    }

    private void initBottomSheet() {
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomSheetNavigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.services);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.services:
                        startActivity(new Intent(getApplicationContext() , HospitalServicesActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bookAppointment:
                        startActivity(new Intent(getApplicationContext() , AppointmentActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext() , ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                }
                return false;
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemLogOut:
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SigninActivity.class);
                startActivity(intent);


                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}