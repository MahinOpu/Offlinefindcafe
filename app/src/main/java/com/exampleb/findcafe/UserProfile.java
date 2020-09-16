package com.exampleb.findcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullname, yourusername,email,phoneno,password;
    TextView myprofilename,profileusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        fullname=(TextInputLayout) findViewById(R.id.fullname1);
        email=findViewById(R.id.useremil);
        phoneno=findViewById(R.id.userphoneno);
        password=findViewById(R.id.userpassword);
        yourusername=findViewById(R.id.username);

        myprofilename=findViewById(R.id.myprofilename);
        profileusername=findViewById(R.id.userprofilename);

        //Display User data

        //displayuserdata();
    }
  /*
    private void displayuserdata() {
        Intent intent=getIntent();

        String name=intent.getStringExtra("fullname1");
        String username=intent.getStringExtra("username1");
        String useremail=intent.getStringExtra("email1");
        String userphoneno=intent.getStringExtra("phoneno1");
        String userpassword=intent.getStringExtra("password1");

        myprofilename.setText(name);
        profileusername.setText(useremail);

        fullname.getEditText().setText(name);
        yourusername.getEditText().setText(username);
        email.getEditText().setText(useremail);
        phoneno.getEditText().setText(userphoneno);
        password.getEditText().setText(userpassword);




    }

   */
}
