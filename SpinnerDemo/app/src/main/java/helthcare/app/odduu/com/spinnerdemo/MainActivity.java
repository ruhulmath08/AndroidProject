package helthcare.app.odduu.com.spinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner division, district, thana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        division = findViewById(R.id.spinner_division);
        district = findViewById(R.id.spinner_district);
        thana = findViewById(R.id.spinner_thana);
    }

    public void saveValue(View view) {
        String s1 = division.getSelectedItem().toString();
        String s2 = district.getSelectedItem().toString();
        String s3 = thana.getSelectedItem().toString();
        Toast.makeText(this, "Value: "+s1+", "+s2+", "+s3, Toast.LENGTH_SHORT).show();
    }
}
