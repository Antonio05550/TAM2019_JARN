package mx.edu.ittepic.a3_4_sw;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText num;
    Button buscar;
    RecyclerView recyclerView;
    EquipoAdapter mAdapter;
    FloatingActionButton agregar;

    List<Equipo> equipos;

    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        num = findViewById( R.id.id );
        buscar = findViewById( R.id.buscar );
        recyclerView = findViewById( R.id.listView );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        agregar = findViewById( R.id.agregar );

        getJSON();

        agregar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEquipo();
            }
        } );

    }

    private void showEquipo(){
        List<Equipo> equipos = new ArrayList<>(  );
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String nombre = jo.getString(Config.TAG_NOMBRE);
                String desc = jo.getString( Config.TAG_DESC );
                equipos.add( new Equipo( id,nombre, desc ) );
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView)findViewById(R.id.listView);
        mAdapter = new EquipoAdapter(MainActivity.this, equipos);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Cargando Datos","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEquipo();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    public void agregarEquipo(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_equipo_layout, null);

        final EditText nombre = subView.findViewById(R.id.nombre );
        final EditText desc = subView.findViewById(R.id.desc );


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Equipo");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String nombreC = nombre.getText().toString();
                final String descC = desc.getText().toString();

                if(TextUtils.isEmpty(nombreC) || TextUtils.isEmpty(descC)){
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    class agregarEquipo extends AsyncTask<Void,Void,String> {

                        ProgressDialog loading;

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(MainActivity.this,"agregando...","Espere...",false,false);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();
                            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        protected String doInBackground(Void... v) {
                            HashMap<String,String> params = new HashMap<>();
                            params.put(Config.KEY_EQUIPO_NOMBRE,nombreC);
                            params.put(Config.KEY_EQUIPO_DESC,descC);

                            RequestHandler rh = new RequestHandler();
                            String res = rh.sendPostRequest(Config.URL_ADD, params);
                            return res;
                        }
                    }

                    agregarEquipo ae = new agregarEquipo();
                    ae.execute();
                    Toast.makeText( MainActivity.this, "Equipo agregada",Toast.LENGTH_SHORT ).show();
                }
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

}
