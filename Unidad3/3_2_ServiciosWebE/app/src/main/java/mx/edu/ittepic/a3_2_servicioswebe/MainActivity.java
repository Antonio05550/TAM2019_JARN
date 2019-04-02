package mx.edu.ittepic.a3_2_servicioswebe;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView fruitsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        fruitsList = (ListView)findViewById(R.id.fruitsList);
        new WSIdalumno().execute();

    }
    private class WSIdalumno extends AsyncTask<Void, Void, String> {

        String json_url;
        String json_string;
        @Override
        protected String doInBackground(Void... Voids) {

            String cadena="";
            json_url = "https://www.thecrazyprogrammer.com/example_data/fruits_array.json";

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
                JSONArray fruitsArray = object.getJSONArray("fruits");
                ArrayList al = new ArrayList();

                for(int i = 0; i < fruitsArray.length(); ++i) {
                    al.add(fruitsArray.getString(i));
                }

                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                fruitsList.setAdapter(adapter);

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
