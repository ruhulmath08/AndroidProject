package serchapp.product.ruhul.com.searchapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class DataPackager {
    String name, email, password;

    public DataPackager() {

    }

    public DataPackager(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //pack data to be send
    public String packData(){
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();

        try {
            jo.put("name", name);
            jo.put("email", email);
            jo.put("password", password);

            Boolean firstValue = true;

            Iterator it = jo.keys();
            do {
                String key = it.next().toString();
                String value = jo.get(key).toString();

                if (firstValue){
                    firstValue = false;
                }
                else {
                    sb.append("&");
                }

                try {
                    sb.append(URLEncoder.encode(key, "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                }
                catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            while (it.hasNext());
            return sb.toString();
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }
}
