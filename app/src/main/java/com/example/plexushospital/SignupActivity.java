package com.example.plexushospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText txtInputPhoneNumber;
    private TextInputEditText txtInputFullName;
    private Button btnGetOtp;
    private TextView txtSignin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initUI();
    }

    private void initUI() {
        txtInputPhoneNumber = findViewById(R.id.txtInputPhoneEditTextNumber);
        txtInputFullName  = findViewById(R.id.txtInputEditFullName);
        txtSignin = findViewById(R.id.textSignIn);
        btnGetOtp = findViewById(R.id.buttonSignUp);

        btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleotpbuttonclicked();
            }
        });
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);

            }
        });
    }

    private void handleotpbuttonclicked(){
        String number = txtInputPhoneNumber.getText().toString().trim();
        String fullName = txtInputFullName.getText().toString().trim();
        if (number.isEmpty() || number.length() < 10) {
            txtInputPhoneNumber.setError("Valid number is required");
            txtInputPhoneNumber.requestFocus();
            return;
        }
        if(fullName.isEmpty()){
            txtInputFullName.setError("Full Name is required");
            txtInputFullName.requestFocus();
            return;
        }
        checkUserExists(number, fullName);

//        Intent intent = new Intent(SignupActivity.this, MobilePinVerificationActivity.class);
//        intent.putExtra("phoneNumber", number);
//        intent.putExtra("fullName",fullName);
//        startActivity(intent);
    }

    private void checkUserExists(String phoneNumber, String fullName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult() != null && !task.getResult().isEmpty()) {
                            // Existing user, prompt to sign in
                            promptSignIn();
                        } else {
                            // New user, start the mobile verification process
                            startMobileVerification(phoneNumber, fullName);
                        }
                    } else {
                        // Handle the error
                        Toast.makeText(SignupActivity.this, "Error checking user", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void promptSignIn() {
        Toast.makeText(SignupActivity.this, "User already exists. Please sign in.", Toast.LENGTH_SHORT).show();
    }

    private void startMobileVerification(String number, String fullName) {
        Intent intent = new Intent(SignupActivity.this, MobilePinVerificationActivity.class);
        intent.putExtra("phoneNumber", number);
        intent.putExtra("fullName",fullName);
        startActivity(intent);
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//
//        if (firebaseAuth.getCurrentUser() != null) {
//            // User is already signed in, move to the dashboard
//            Intent intent = new Intent(this, DashboardActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }
}
