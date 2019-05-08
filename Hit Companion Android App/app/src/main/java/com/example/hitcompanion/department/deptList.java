package com.example.hitcompanion.department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.example.hitcompanion.clubs.club_details;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class deptList extends AppCompatActivity {

    ListView dept;
    ArrayList depts;
    ArrayAdapter adapter;
    Button dept_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_list);

        dept = findViewById(R.id.deptList);
        dept_menu = findViewById(R.id.deptMenu_back);

        ParseQuery<ParseObject> deptsList = ParseQuery.getQuery("Department");
        deptsList.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                depts = new ArrayList<>();
                if(e == null){
                    if(objects.size() > 0){
                        for( ParseObject obj : objects ){
                            depts.add(obj.getString("name"));
                        }
                        adapter = new ArrayAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , depts);
                        dept.setAdapter(adapter);
                        dept.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent detail = new Intent(getApplicationContext() , dept_details.class);
                                detail.putExtra("dept" , (String) depts.get(position));
                                startActivity(detail);
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext() , "No Departments found" , Toast.LENGTH_SHORT);
                    }

                }else{
                    e.printStackTrace();
                }
            }
        });

        dept_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), department_home.class ));
            }
        });

    }
}
