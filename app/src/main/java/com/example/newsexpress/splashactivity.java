package com.example.newsexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class splashactivity extends AppCompatActivity {


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    ImageView google_image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.pass);

        MaterialButton button =  (MaterialButton) findViewById(R.id.login);

        google_image = (ImageView) findViewById(R.id.google);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount(this);
        if(acct !=null){
            gotoSecondActivity();
        }


        google_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //admin and admin

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    startActivity(new Intent(splashactivity.this, MainActivity.class));
                } else
                    Toast.makeText(splashactivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }

                    });
    }

    //when click on google button it will open sign in page
    void signIn()
    {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);

    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                gotoSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        }

    }

    void gotoSecondActivity()
    {
        finish();
        startActivity(new Intent(splashactivity.this, MainActivity.class));
    }
}