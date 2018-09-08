package serchapp.product.ruhul.com.searchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String urlAddress = "http://10.0.2.2/android/poster.php";
    EditText nameTxt, emailTxt, passTxt;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTxt = findViewById(R.id.edit_name);
        emailTxt = findViewById(R.id.edit_email);
        passTxt = findViewById(R.id.edit_password);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sender s = new Sender(MainActivity.this, urlAddress, nameTxt, emailTxt, passTxt);
                s.execute();
            }
        });
    }
}
