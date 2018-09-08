package helthcare.app.odduu.com.odduuhealthcare;

import android.content.Intent;
import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UploadUserInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    Button saveInfo;
    EditText editName, editAge, editWeight;

    Spinner blodGroupSpinner;
    String[] bloodGroup = {"Select blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user_info);

        //button
        saveInfo = findViewById(R.id.btn_save_info);
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadUserInfo.this, "Spenner value: "+selectdBloodGroup+" Name: "+editName.getText().toString()+
                        " Age: "+editAge.getText().toString()+" Weight: "+editAge.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //editText
        editName = findViewById(R.id.patient_name);
        editAge = findViewById(R.id.patient_age);
        editWeight = findViewById(R.id.patient_weight);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        blodGroupSpinner = findViewById(R.id.blood_group);
        blodGroupSpinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,bloodGroup);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        blodGroupSpinner.setAdapter(dataAdapter);

        //get value from intent
        Intent intent = getIntent();
        String s1 = intent.getStringExtra(Home.HOME_USER_NAME).toString();
        String s2 = intent.getStringExtra(Home.HOME_PHONR_NUMBER).toString();
        editName.setText(s1);
        editAge.setText(s2);
    }

    String selectdBloodGroup;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        selectdBloodGroup = parent.getItemAtPosition(position).toString();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
