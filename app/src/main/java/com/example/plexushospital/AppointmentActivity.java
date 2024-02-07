package com.example.plexushospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AppointmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> doctorList = new ArrayList<>();
    TextView txtChooseDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        setDoctorNames();
        Spinner spinner = (Spinner) findViewById(R.id.spinnerChooseDoctor);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.doctor_names,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        txtChooseDoctor = findViewById(R.id.txtDoctorName);
        txtChooseDoctor.setText("Choose Doctor");


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

    private void setDoctorNames() {
        doctorList.add(0, "Choose Doctor");
        String[] doctorNames = getResources().getStringArray(R.array.doctor_names);
        for (String doctorName : doctorNames) {
            doctorList.add(new String(doctorName));
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String selectedDoctor = adapterView.getSelectedItem().toString();
        txtChooseDoctor.setText(selectedDoctor);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
