package com.example.academy_intern.async;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class ActiviyLogin extends AppCompatActivity {

    TextView textView;
    final String LOG = "ActiviyLogin";
    EditText username , password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       // textView = (TextView) findViewById(R.id.text);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnLogIN);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Password = password.getText().toString();

                HashMap postData = new HashMap();
                postData.put("txtUsername",Username);
                postData.put("txtPassword",Password);


                PostResponseAsyncTask task1 = new PostResponseAsyncTask(ActiviyLogin.this, postData , new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        Log.d(LOG , s);


                        if(s.contains("success"))
                        {
                            Toast.makeText(ActiviyLogin.this , "Successfully Logged in" , Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ActiviyLogin.this , ListActivity.class);
                            startActivity(intent);
                        }


                    }
                });
                task1.execute("http://10.0.2.2:80/customer/");

            }
        });




    }
}
