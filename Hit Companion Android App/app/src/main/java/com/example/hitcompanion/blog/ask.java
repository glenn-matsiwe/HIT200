package com.example.hitcompanion.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class ask extends AppCompatActivity {

    EditText topic , body ;
    Button submit , back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        topic = findViewById(R.id.topic);
        body = findViewById(R.id.body);
        back = findViewById(R.id.back1);
        submit = findViewById(R.id.submit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), blog_home.class ));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( topic.getText().toString().matches("") || body.getText().toString().matches("") ){
                    Toast.makeText(getApplicationContext() , "One/All of the fields is empty" , Toast.LENGTH_LONG).show();
                }else{

                    ParseObject blog = new ParseObject("Blog");
                    blog.put("topic", topic.getText().toString().trim() );
                    blog.put("body", body.getText().toString().trim() );
                    blog.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                Toast.makeText(getApplicationContext() , "Question has been submitted" , Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext() , blog_home.class ));
                            }else{
                                Toast.makeText(getApplicationContext() , "Couldn't save your question" , Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });




    }
}
