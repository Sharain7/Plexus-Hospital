package com.example.plexushospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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

