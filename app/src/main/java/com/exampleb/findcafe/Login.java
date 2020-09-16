package com.exampleb.findcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
 Button signup,login;
 ImageView logoimage;
 TextView welcometextview,logintextview;
 TextInputLayout username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        signup=(Button) findViewById(R.id.call_signup);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

    }

    private boolean validUsername(){
        String value=username.getEditText().getText().toString();

        if (value.isEmpty()){
            username.setError("Field is empty");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validPassword(){

        String value=password.getEditText().getText().toString();
        if (value.isEmpty()){
            password.setError("Field is empty");
            return false;

        }
        else
        {  username.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public  void loginUser(View view){
     if (!validUsername()| !validPassword()){
         return;
     }
     else{
         isUser();
     }

    }

    private void isUser(){
        final String enteredusername=username.getEditText().getText().toString().trim();
        final String entereduserpassword=password.getEditText().getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUser=reference.orderByChild("username").equalTo(enteredusername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordfromdatabase=dataSnapshot.child(enteredusername).child("password").getValue(String.class);

                    if (passwordfromdatabase.equals(entereduserpassword)){
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String namefromdatabase=dataSnapshot.child(enteredusername).child("fullname").getValue(String.class);
                        String usernamefromdatabase=dataSnapshot.child(enteredusername).child("username").getValue(String.class);
                        String emailfromdatabase=dataSnapshot.child(enteredusername).child("email").getValue(String.class);
                        String phonenofromdatabase=dataSnapshot.child(enteredusername).child("phoneno").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);
                        intent.putExtra("fullname1",namefromdatabase);
                        intent.putExtra("username1",usernamefromdatabase);
                        intent.putExtra("email1",emailfromdatabase);
                        intent.putExtra("phoneno1",phonenofromdatabase);
                        intent.putExtra("password1",passwordfromdatabase);
                        startActivity(intent);




                    }
                    else{
                        password.setError("wrong password!");
                        password.requestFocus();

                    }

                }
                else {
                    username.setError("You are not registered yet");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
