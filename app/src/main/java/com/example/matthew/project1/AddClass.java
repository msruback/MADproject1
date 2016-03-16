package com.example.matthew.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddClass extends AppCompatActivity {
    private EditText addCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addCourse = (EditText)findViewById(R.id.courseEditText);
        Button submit = (Button)findViewById(R.id.submitButton);
        Button cancel = (Button)findViewById(R.id.cancelButton);

        //Event Listeners
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String course = addCourse.getText().toString();

                Intent returnIntent = new Intent();
                Bundle returnBundle = new Bundle();

                returnBundle.putString("course",course);

                returnIntent.putExtras(returnBundle);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

}
