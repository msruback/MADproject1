package com.example.matthew.project1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private User user;
    private CourseContact editingContact;
    private ListView contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = new User();

        contactList = (ListView) findViewById(R.id.listView);

        String[] contactArray = user.getContactNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,contactArray);


        contactList.setAdapter(adapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) contactList.getItemAtPosition(position);
                CourseContact selectedContact = user.findContact(itemValue);
                editingContact = selectedContact;
                Intent intent = new Intent(getApplicationContext(), ViewContact.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",selectedContact.getName());
                bundle.putString("email",selectedContact.getEmail());
                bundle.putString("course",selectedContact.getCourse());
                bundle.putStringArray("courseNames", user.getCourseNames());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.addContact){
            Intent intent = new Intent(this, AddCourseContact.class);
            String[] courseNames = user.getCourseNames();
            Bundle bundle = new Bundle();
            bundle.putString("purpose","add");
            bundle.putStringArray("courseNames",courseNames);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
        }
        if (id == R.id.addCourse){
            Intent intent = new Intent(this,AddClass.class);
            startActivityForResult(intent,2);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
        super.onActivityResult(requestCode,resultCode,returnedIntent);
        switch(requestCode) {
            case 0:
                if (resultCode == RESULT_OK){
                    Bundle returnedBundle = returnedIntent.getExtras();

                    CourseContact newContact = new CourseContact();
                    newContact.setName(returnedBundle.getString("name"));
                    newContact.setEmail(returnedBundle.getString("email"));
                    newContact.setCourse(returnedBundle.getString("courseName"));
                    user.addCourseContact(newContact);
                    String[] contactArray = user.getContactNames();
                    ListView updateList = (ListView)findViewById(R.id.listView);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,contactArray);
                    updateList.setAdapter(adapter);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK) {
                    Bundle returnedBundle = returnedIntent.getExtras();
                    user.removeCourseContact(editingContact);
                    if(returnedBundle.getString("purpose").equals("edit")) {
                        CourseContact newContact = new CourseContact();
                        newContact.setName(returnedBundle.getString("name"));
                        newContact.setEmail(returnedBundle.getString("email"));
                        newContact.setCourse(returnedBundle.getString("courseName"));
                        user.addCourseContact(newContact);
                    }

                    String[] contactArray = user.getContactNames();
                    ListView updateList = (ListView)findViewById(R.id.listView);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,contactArray);
                    updateList.setAdapter(adapter);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Bundle returnedBundle = returnedIntent.getExtras();
                    user.addCourse(returnedBundle.getString("course"));
                }
        }
    }
}
