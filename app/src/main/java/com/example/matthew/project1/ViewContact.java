package com.example.matthew.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView nameTextView = (TextView)findViewById(R.id.nameTextView);
        final TextView emailTextView = (TextView)findViewById(R.id.emailTextView);
        final TextView courseTextView = (TextView)findViewById(R.id.courseTextView);
        Button edit = (Button)findViewById(R.id.editContact);
        Button delete = (Button)findViewById(R.id.deleteContact);
        Button cancel = (Button)findViewById(R.id.cancelButton);

        Intent callingIntent = this.getIntent();
        Bundle incomingBundle = callingIntent.getExtras();
        final String[] courseNames = incomingBundle.getStringArray("courseNames");
        nameTextView.setText(incomingBundle.getString("name"));
        emailTextView.setText(incomingBundle.getString("email"));
        courseTextView.setText(incomingBundle.getString("course"));

        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),AddCourseContact.class);
                Bundle bundle = new Bundle();
                bundle.putString("purpose","edit");
                bundle.putString("name",nameTextView.getText().toString());
                bundle.putString("email",emailTextView.getText().toString());
                bundle.putString("course",courseTextView.getText().toString());
                bundle.putStringArray("courseNames",courseNames);

                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent returnIntent = new Intent();
                Bundle returnBundle = new Bundle();
                returnBundle.putString("purpose", "delete");

                returnIntent.putExtras(returnBundle);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
        super.onActivityResult(requestCode,resultCode,returnedIntent);
        switch(requestCode){
            case 0:
                Bundle returnedBundle = returnedIntent.getExtras();

                Intent returnIntent = new Intent();
                returnedBundle.putString("purpose", "edit");
                returnIntent.putExtras(returnedBundle);

                setResult(RESULT_OK,returnIntent);
                finish();

                break;
        }
    }

}
