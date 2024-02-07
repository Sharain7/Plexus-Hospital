package com.example.plexushospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    ArrayList<ServicesModel> servicesModels = new ArrayList<>();
    int[] serviceModelImages = {
            R.drawable.account_circle,
            R.drawable.stethoscope,
            R.drawable.medical_services,
            R.drawable.info,
            R.drawable.contact_phone,
            R.drawable.feedback
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initBottomSheet();

        RecyclerView recyclerView = findViewById(R.id.servicesRecyclerView);
        setServicesModels();
        HS_RecyclerViewAdapter adapter = new HS_RecyclerViewAdapter(this, servicesModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    private void initBottomSheet() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomSheetNavigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.services:
                        startActivity(new Intent(getApplicationContext(), HospitalServicesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.bookAppointment:
                        startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void setServicesModels() {
        String[] servicesNames = getResources().getStringArray(R.array.services_list);
        for (int i = 0; i < servicesNames.length; i++) {
            servicesModels.add(new ServicesModel(servicesNames[i], serviceModelImages[i]));
        }
    }


}