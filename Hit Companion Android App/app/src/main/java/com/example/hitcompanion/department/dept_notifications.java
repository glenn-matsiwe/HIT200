package com.example.hitcompanion.department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dept_notifications extends AppCompatActivity {
    TextView text;
    ListView dept_Notification;
    Button backBtn;
    ArrayList notis;
    ArrayAdapter adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_notifications);

        text = findViewById(R.id.text);
        dept_Notification = findViewById(R.id.dept_notification);
        backBtn = findViewById(R.id.backBtn9);

        final Intent data = getIntent();
        ParseUser currentUser = ParseUser.getCurrentUser();

        text.append(" "+data.getStringExtra("department"));

        ParseQuery<ParseObject> notification_query = ParseQuery.getQuery("DeptNotifications");
        notification_query.whereGreaterThanOrEqualTo("expireDate", new Date());
        notification_query.whereContains("department", data.getStringExtra("department") ).orderByDescending("createdAt");
        notification_query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                notis = new ArrayList<>();
                if (e == null){
                    Log.i("findInbaackground" , "retrieved " + objects.size() + " objects");
                    if(objects.size() > 0){
                        for ( ParseObject object : objects ){
                            notis.add(object.getString("notification"));
                        }
                        adpt = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , notis);
                        dept_Notification.setAdapter(adpt);
                    }else{
                        Toast.makeText(getApplicationContext() , objects.size()+ " notifications where found" , Toast.LENGTH_SHORT).show();
                    }
                }else{
                    e.printStackTrace();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext(),dept_details.class);
                inte.putExtra("dept", data.getStringExtra("department") );
                startActivity(inte);
            }
        });


    }
}
