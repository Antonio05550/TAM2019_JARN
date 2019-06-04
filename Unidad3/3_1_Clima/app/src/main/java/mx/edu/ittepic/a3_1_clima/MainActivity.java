package mx.edu.ittepic.a3_1_clima;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView clima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        clima = (TextView) findViewById(R.id.Clima1);
        new WSIdalumno().execute();

    }
    private class WSIdalumno extends AsyncTask<Void, Void, String> {

        String json_url;
        String json_string;
        @Override
        protected String doInBackground(Void... Voids) {

            String cadena="";
            json_url = "http://api.openweathermap.org/data/2.5/weather?q=tepic,mx&APPID=5a8ab31167d55aa3d4970b6ea03aa2bd";

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                int respuesta = httpURLConnection.getResponseCode();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK) {
                    String abc=json_string;

                    while ((json_string = bufferedReader.readLine()) != null) {

                        stringBuilder.append(json_string + "\n");

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    cadena = stringBuilder.toString().trim();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return cadena;
        }


        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject object = new JSONObject(result);
                JSONArray fruitsArray = object.getJSONArray("main");
                ArrayList al = new ArrayList();

                for(int i = 0; i < fruitsArray.length(); ++i) {
                    al.add(fruitsArray.getString(i));
                }


            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}