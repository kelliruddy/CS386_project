package com.example.chandler.hack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    Button b1, b2;
    EditText ed1, ed2;
    User user;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        b1 = (Button)findViewById(R.id.login);
        b2 = (Button)findViewById(R.id.signUp);

        ed1 = (EditText)findViewById(R.id.usernameText);
        ed2 = (EditText)findViewById(R.id.passText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(v);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),
                        "Attempting sign up...", Toast.LENGTH_SHORT).show();
                showSignUp();
            }
        });
    }

    public void Login(View v) {
        try {
            // Not using file is fine here
            // It is currently used as the detector for the creation of an account
            // If the file exists, the "account" has been registered and the user file
            // is present
            FileReader file = new FileReader(
                    getFilesDir().getPath() + ed1.getText().toString() + ed2.getText().toString() +
                            "DrinkList.json");
            Toast.makeText(getBaseContext(),
                    "Redirecting...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        catch (IOException e) {
            Toast.makeText(getBaseContext(),
                    "Incorrect Credentials", Toast.LENGTH_SHORT).show();
            Log.i("IOException", e.getMessage());
        }
        catch (NullPointerException e) {
            Toast.makeText(getBaseContext(),
                    "Incorrect Credentials", Toast.LENGTH_SHORT).show();
            Log.i("NullPointerException", e.getMessage());
        }
    }

    public void showSignUp() {
        signUpDialog newFragment = signUpDialog.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("fileDir", getFilesDir().getPath());
        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void setUser(User user) {
        this.user = user;
    }
}
