package com.example.hitcompanion.department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hitcompanion.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class dept_details extends AppCompatActivity {
    TextView dName , dChairperson , dDescription ;
    String id , description , chairperson ;
    Button notification , back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_details);

        dName = findViewById(R.id.department_name);
        dChairperson = findViewById(R.id.advisors);
        dDescription = findViewById(R.id.descript);

        back = findViewById(R.id.deptList_back);
        notification = findViewById(R.id.dept_notifications);

        final Intent inte = getIntent();
        final String dept = inte.getStringExtra("dept");


        ParseQuery<ParseObject> questionDetail = ParseQuery.getQuery("Department");
        questionDetail.whereEqualTo("name", inte.getStringExtra("dept") );
        questionDetail.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null && objects != null ){
                    for (ParseObject object : objects){
                        id = object.getObjectId();
                        description = object.getString("description");
                        chairperson = object.getString("chairman");
                    }
                    dName.setText(dept);
                    dDescription.setText(description);
                    dChairperson.setText(chairperson);

                }
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noti = new Intent(getApplicationContext(),dept_notifications.class);
                noti.putExtra("department", inte.getStringExtra("dept") );
                startActivity(noti);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),deptList.class));
            }
        });




    }
}
