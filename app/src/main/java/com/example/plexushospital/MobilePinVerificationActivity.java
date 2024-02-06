package com.example.plexushospital;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MobilePinVerificationActivity extends AppCompatActivity {

    private String verificationId;
    private FirebaseAuth mAuth;
    private Button buttonSignUp;

    ProgressBar progressBar;
    PinView txtOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_pin_verification);
        initUI();
    }
    private void initUI() {
        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        txtOtp = findViewById(R.id.pinview);
        buttonSignUp = findViewById(R.id.btn_signUp);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String fullName = getIntent().getStringExtra("fullName");

        if (phoneNumber != null && !phoneNumber.isEmpty() && fullName != null && !fullName.isEmpty()) {
            // Always initiate the registration process
            sendVerificationCode(phoneNumber);
            // You may want to store user details after verification is successful
        } else {
            // Handle the case where phoneNumber or fullName is empty or null
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
            finish();
        }

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = txtOtp.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {
                    txtOtp.setError("Enter code...");
                    txtOtp.requestFocus();
                    return;
                }

                verifyCode(code);
            }
        });
    }


    private void storeUserDetail(String phoneNumber, String fullName, String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("name", fullName);
        user.put("phoneNumber", phoneNumber);
        db.collection("users")
                .document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User data successfully written to Firestore");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing user data to Firestore", e);
                    }
                });
    }



    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeUserDetail(
                                    getIntent().getStringExtra("phoneNumber"),
                                    getIntent().getStringExtra("fullName"),
                                    mAuth.getCurrentUser().getUid()
                            );
                            Intent intent = new Intent(MobilePinVerificationActivity.this, DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);

                        } else {
                            Toast.makeText(MobilePinVerificationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                this ,
                mCallBack
        );

        progressBar.setVisibility(View.GONE);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                txtOtp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(MobilePinVerificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    };
}
