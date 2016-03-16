package com.example.matthew.project1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class AddCourseContact extends AppCompatActivity {

    private EditText nameEditText,emailEditText;
    private Uri contactPicture;
    private ImageButton contactPictureButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Handling content
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        contactPictureButton = (ImageButton)findViewById(R.id.contactPicture);
        Button submit = (Button)findViewById(R.id.submitButton);
        Button cancel = (Button)findViewById(R.id.cancelButton);
        final Spinner courseSpinner = (Spinner) findViewById(R.id.courseSpinner);

        //Incoming stuff
        Intent callingIntent = this.getIntent();
        Bundle incomingBundle = callingIntent.getExtras();
        String[] courseNames=incomingBundle.getStringArray("courseNames");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courseNames);
        courseSpinner.setAdapter(adapter);
        if(incomingBundle.getString("purpose").equals("edit")){
            nameEditText.setText(incomingBundle.getString("name"));
            emailEditText.setText(incomingBundle.getString("email"));
            contactPictureButton.setImageURI((Uri)incomingBundle.getParcelable("photo"));
            courseSpinner.setSelection(adapter.getPosition(incomingBundle.getString("course")));
        }

        //Event Listeners
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String course = courseSpinner.getSelectedItem().toString();

                Intent returnIntent = new Intent();
                Bundle returnBundle = new Bundle();

                returnBundle.putString("name",name);
                returnBundle.putString("email",email);
                returnBundle.putString("course",course);
                returnBundle.putParcelable("photo",contactPicture);

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
        contactPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoDialog(v);
            }
        });
    }

    //Handles photo activity return
    protected void onActivityResult(int requestCode,int resultCode, Intent returnedIntent){
        super.onActivityResult(requestCode, resultCode,returnedIntent);
        switch(requestCode){
            case 0:
                if(resultCode == RESULT_OK){
                    contactPicture = returnedIntent.getData();
                    contactPictureButton.setImageURI(contactPicture);
                }
                break;
            case 1:
                if(resultCode == RESULT_OK){
                    contactPicture = returnedIntent.getData();
                    contactPictureButton.setImageURI(contactPicture);
                }
                break;
        }

    }

    public void openPhotoDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");

        builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
        builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });
        AlertDialog photoDialog = builder.create();
        photoDialog.show();
    }

}
