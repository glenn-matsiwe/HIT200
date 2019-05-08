package com.example.hitcompanion.department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hitcompanion.R;
import com.example.hitcompanion.home_page;


public class department_home extends AppCompatActivity {
    TextView dept_backBtn , departmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_home);

        dept_backBtn = findViewById(R.id.deptMenu_back);
        departmentList = findViewById(R.id.deptsList);


        dept_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), home_page.class));
            }
        });

        departmentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , deptList.class));
            }
        });


    }


}
