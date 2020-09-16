package com.exampleb.findcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
 TextInputLayout personname,username,useremail,userphoneno,userpassword;
 Button signup,tologin;

 FirebaseDatabase rootNode;
 DatabaseReference reference;
 public String mystr="You are gegistered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       // rootNode=FirebaseDatabase.getInstance();
        //reference=rootNode.getReference("users");

        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");

        setContentView(R.layout.activity_signup);
        personname=findViewById(R.id.name);
        username=findViewById(R.id.username);
        useremail=findViewById(R.id.email);
        userphoneno=findViewById(R.id.phoneno);
        userpassword=findViewById(R.id.yourpassword);

        signup=findViewById(R.id.tosignup);
        tologin=findViewById(R.id.backtologin);


        String name=personname.getEditText().getText().toString();
        String userName=username.getEditText().getText().toString();
        String phoneno=userphoneno.getEditText().getText().toString();
        String email=useremail.getEditText().getText().toString();
        String password=userpassword.getEditText().getText().toString();

        Userdata userdata=new Userdata(name,userName,phoneno,password,email);

        reference.child(userName).setValue(userdata);

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });



    }

    private boolean validName(){
        String value=personname.getEditText().getText().toString();
        if (value.isEmpty()){
            personname.setError("Field can't be empty");
            return false;
        }
        else {
            personname.setError(null);
            personname.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validUserName(){
        String value=username.getEditText().getText().toString();
        if (value.isEmpty()){
            username.setError("Field can't be empty");
            return false;
        }
        else if (value.length()>15){

         username.setError("user name is too long");
         return false;
        }

        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validEmail(){
        String value=useremail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (value.isEmpty()){
            useremail.setError("Field can't be empty");
            return false;
        }
        else if (!value.matches(emailPattern)){
            useremail.setError("please enter a valid Email");
            return false;
        }
        else {
            useremail.setError(null);
            useremail.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validPhone(){
        String value=userphoneno.getEditText().getText().toString();
        if (value.isEmpty()){
            userphoneno.setError("Field can't be empty");
            return false;
        }
        else {
            userphoneno.setError(null);
            userphoneno.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validPassword(){
        String value=personname.getEditText().getText().toString();

        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (value.isEmpty()){
            userpassword.setError("Field can't be empty");
            return false;
        }
        else if (!value.matches(passwordVal)){
            userpassword.setError("Password is too weak");
            return false;

        }
        else {
            userpassword.setError(null);
            userpassword.setErrorEnabled(false);
            return true;
        }

    }



    //save data in firebase on button click
    public void userRegister(View view){
        if (!validName()| !validUserName()| !validEmail()| !validPhone()|!validPassword()){
            return;
        }
        Intent intent=new Intent(getApplicationContext(),UserProfile.class);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");

        String name=personname.getEditText().getText().toString();
        String userName=username.getEditText().getText().toString();
        String phoneno=userphoneno.getEditText().getText().toString();
        String email=useremail.getEditText().getText().toString();
        String password=userpassword.getEditText().getText().toString();


        Userdata userdata=new Userdata(name,userName,phoneno,password,email);

        reference.child(userName).setValue(userdata);

        Toast.makeText(getApplicationContext(),mystr,Toast.LENGTH_SHORT).show();





    }




}
