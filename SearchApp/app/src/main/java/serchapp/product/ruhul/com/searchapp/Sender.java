package serchapp.product.ruhul.com.searchapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class Sender extends AsyncTask<Void, Void, String>{

    Context c;
    String urlAddress;
    EditText nameTxt, emailTxt, passTxt;
    String name, email, password;

    ProgressDialog pd;

    public Sender(Context c, String urlAddress, EditText... editTexts) {
        this.c = c;
        this.urlAddress = urlAddress;

        this.nameTxt = editTexts[0];
        this.emailTxt = editTexts[1];
        this.passTxt = editTexts[0];

        name = nameTxt.getText().toString();
        email = emailTxt.getText().toString();
        password = passTxt.getText().toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Send");
        pd.setMessage("Sending please wait...");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();
        
        if (s != null){

            //Success
            Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
            nameTxt.setText("");
            emailTxt.setText("");
            passTxt.setText("");
        }
        else {
            //No Success
            Toast.makeText(c, "Unsuccessful "+s, Toast.LENGTH_SHORT).show();
        }
    }

    private String send(){
        //Connect to server
        HttpURLConnection con = Connector.connect(urlAddress);
        if (con == null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            bw.write(new DataPackager(name, email, password).packData());

            //release resources
            bw.flush();
            bw.close();
            os.close();

            //Has it been successful
            int responseCode = con.getResponseCode();
            if (responseCode == con.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();

                String line = null;

                while ((line = br.readLine()) != null){
                    response.append(line);
                }
                
                //release resources
                br.close();
                return response.toString();

            }
            else {}
        }
        catch (IOException e) {
            System.out.print(e);
        }
        
        return null;
    }
}
