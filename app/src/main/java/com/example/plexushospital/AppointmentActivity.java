package com.example.plexushospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AppointmentActivity extends AppCompatActivity {

//    ArrayList<AppointmentModel> appointmentModels = new ArrayList<>();
//    ArrayList<String> doctorList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
//        RecyclerView recyclerView = findViewById(R.id.appointmentRecyclerView);
//        setAppointmentModels();
//        setDoctorList();
//        AS_RecyclerViewAdapter adapter = new AS_RecyclerViewAdapter(this , appointmentModels , doctorList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
//    private void setDoctorList(){
//        String[] doctorNames = getResources().getStringArray(R.array.doctor_names);
//        for(String doctorName :  doctorNames){
//            doctorList.add(doctorName);
//        }
//    }
//
//    private void setAppointmentModels() {
//        String[] appointFormRequirements = getResources().getStringArray(R.array.appointment_form_requirements);
//
//        for (int i = 0; i < appointFormRequirements.length; i++) {
//            boolean hasDropdown = (i == 0);
//            boolean hasTextArea = (i == 5);
//
//            appointmentModels.add(new AppointmentModel(appointFormRequirements[i], hasDropdown, hasTextArea));
//        }
//    }


}
