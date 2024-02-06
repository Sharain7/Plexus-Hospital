package com.example.plexushospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.concurrent.TimeUnit;

public class SigninActivity extends AppCompatActivity {

    private TextInputEditText txtEditPhoneNumber;
    private TextView txtSignUp ;
    private Button btnSignin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initUI();

    }

    private void initUI() {
        txtEditPhoneNumber = findViewById(R.id.txtInputEditPhoneNumber);
        txtSignUp = findViewById(R.id.textSignUp);
        btnSignin = findViewById(R.id.buttonSignIn);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this , SignupActivity.class);
                startActivity(intent);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignIn();
            }
        });


    }

    private void handleSignIn() {
        String phoneNumber = txtEditPhoneNumber.getText().toString().trim();

        // Check if the input is valid
        if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
            txtEditPhoneNumber.setError("Valid number is required");
            txtEditPhoneNumber.requestFocus();
            return;
        }

        // Create a query to find a user with the given phone number
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersRef = db.collection("users");
        Query query = usersRef.whereEqualTo("phoneNumber", phoneNumber);

        // Execute the query
        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Handle the query result
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // User exists, navigate to the dashboard
                        Intent intent = new Intent(SigninActivity.this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        // User does not exist, navigate to the sign-up activity
                        promptSignUp();
                        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failures, such as permission issues or network errors
                    // You might want to show an error message or try again
                    Toast.makeText(SigninActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                });
    }
    private void promptSignUp() {
        Toast.makeText(SigninActivity.this, "User does not exist. Please sign up.", Toast.LENGTH_SHORT).show();
    }

}
