package com.ibso.student_predict_course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mTextFullName, mTextUserName, mTextPassword, mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextFullName = (EditText) findViewById(R.id.register_fullname);
        mTextUserName = (EditText) findViewById(R.id.register_username);
        mTextPassword = (EditText) findViewById(R.id.register_password);
        mTextCnfPassword = (EditText) findViewById(R.id.register_cnf_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        DB = new DBHelper(this);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mTextFullName.getText().toString();
                String user = mTextUserName.getText().toString();
                String pass = mTextPassword.getText().toString();
                String cnfPass = mTextCnfPassword.getText().toString();

                if(user.equals("") || pass.equals("")|| cnfPass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(cnfPass)) {
                        Boolean checkuser = DB.checkUserName(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertUserRegistrationData(name,user,pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(homeIntent);
                            }else
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "User Already Exists. Kindly LOGIN.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}