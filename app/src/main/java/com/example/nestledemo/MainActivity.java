package com.example.nestledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final EditText login_username = (EditText) findViewById(R.id.login_username);
        final EditText login_password = (EditText) findViewById(R.id.login_password);
        final String username = login_username.getText().toString();
        // Need to change this in the future.
        final String password = login_password.getText().toString();
        final Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigateToHomePage = new Intent(MainActivity.this, HomePage.class);
                navigateToHomePage.putExtra("USERNAME", username);
                MainActivity.this.startActivity(navigateToHomePage);

            }
        });
    }
}