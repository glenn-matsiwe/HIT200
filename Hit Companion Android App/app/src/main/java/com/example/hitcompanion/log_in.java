package com.example.hitcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class log_in extends AppCompatActivity {

    EditText reg_num , pass_word ;
    Button log ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        reg_num = findViewById(R.id.reg_num);
        pass_word = findViewById(R.id.pass_word);
        log = findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reg = reg_num.getText().toString().trim();
                String passwo = pass_word.getText().toString().trim();

                ParseUser.logInInBackground(reg, passwo, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if ( e == null ){
                            Toast.makeText(getApplicationContext() , "Log in was successful" , Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext() , home_page.class));
                        }else{
                            e.printStackTrace();
                            if (e.getMessage() == "i/o failure"){
                                Toast.makeText(getApplicationContext() , "No Server Connection" , Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext() , e.getMessage()+" Or check if you are in the user database" , Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

            }
        });
    }
}
