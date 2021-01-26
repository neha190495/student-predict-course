package com.ibso.student_predict_course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText mTextUserName, mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextUserName = (EditText) findViewById(R.id.login_username);
        mTextPassword = (EditText) findViewById(R.id.login_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textViewRegister);
        DB = new DBHelper(this);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUserName.getText().toString();
                String pass = mTextPassword.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkUserNamePassword(user, pass);
                    if(checkuserpass==false)
                        Toast.makeText(LoginActivity.this, "User Does not exist. Kindly check username or register.", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(LoginActivity.this, "Logging you in", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                    }
                }
            }
        });

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}