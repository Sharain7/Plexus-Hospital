package com.example.plexushospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    ArrayList<ServicesModel> servicesModels = new ArrayList<>();
    int[] serviceModelImages = {
            R.drawable.account_circle ,
            R.drawable.stethoscope ,
            R.drawable.medical_services,
            R.drawable.info ,
            R.drawable.contact_phone ,
            R.drawable.feedback
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RecyclerView recyclerView  = findViewById(R.id.servicesRecyclerView);
        setServicesModels();
        HS_RecyclerViewAdapter adapter = new HS_RecyclerViewAdapter(this , servicesModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setServicesModels(){
        String[] servicesNames = getResources().getStringArray(R.array.services_list);
        for(int i = 0; i< servicesNames.length ; i++){
            servicesModels.add(new ServicesModel(servicesNames[i],serviceModelImages[i]));
        }
    }


}