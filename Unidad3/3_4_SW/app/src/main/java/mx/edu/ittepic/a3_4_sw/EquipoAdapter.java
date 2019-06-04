package mx.edu.ittepic.a3_4_sw;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoViewHolder>{
    private Context context;
    private List<Equipo> equipos;
    private String idA;


    public EquipoAdapter(Context context, List<Equipo> equipos) {
        this.context = context;
        this.equipos = equipos;
    }

    @Override
    public EquipoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipo_layout, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipoViewHolder holder, int position) {
        final Equipo equipo = equipos.get(position);
        holder.nombre.setText( equipo.getNombre());
        holder.desc.setText( equipo.getDesc());
        
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEquipo(String.valueOf( equipo.getId() ) );
            }
        });

    }

    private void getEquipo( final String idA){
        class GetEquipo extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context,"Cargando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText( context, "s:"+s, Toast.LENGTH_SHORT ).show();
                showEquipo(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_EQUIPO,idA);
                return s;
            }
        }
        GetEquipo ge = new GetEquipo();
        ge.execute();
    }

    private void showEquipo(String json){
        Toast.makeText( context, "ENTRO",Toast.LENGTH_SHORT ).show();

        String id = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            Toast.makeText( context, ""+ c.getString( Config.TAG_NOMBRE),Toast.LENGTH_SHORT ).show();
            LayoutInflater inflater = LayoutInflater.from(context);
            View subView = inflater.inflate(R.layout.add_equipo_layout, null);

            final EditText nombreC = subView.findViewById(R.id.nombre );
            final EditText descC = subView.findViewById(R.id.desc );

            if(c != null){
                nombreC.setText( c.getString( Config.TAG_NOMBRE));
                descC.setText( c.getString( Config.TAG_DESC) );
                id = c.getString( Config.TAG_ID);
                idA = c.getString( Config.TAG_ID);
                Toast.makeText( context, "Id:" +c.getString( Config.TAG_ID),Toast.LENGTH_SHORT ).show();

            }

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Editar Equipo");
            builder.setView(subView);
            builder.create();

            builder.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String nombre = nombreC.getText().toString();
                    final String desc = descC.getText().toString();

                    if(TextUtils.isEmpty(nombre) || TextUtils.isEmpty(desc)){
                        Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_LONG).show();
                    }
                    else{
                        updateEquipo( idA, nombre,desc  );
                        dialog.dismiss();
                        ((Activity)context).finish();
                        context.startActivity(((Activity)context).getIntent());
                    }
                }
            });

            builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_LONG).show();
                }
            });
            builder.show();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    private void updateEquipo(final String idA, final String nombreC, final String descC){

        class actualizarEquipo extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(context,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EQUIPO_ID,idA);
                hashMap.put(Config.KEY_EQUIPO_NOMBRE,nombreC);
                hashMap.put(Config.KEY_EQUIPO_DESC,descC);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EQUIPO,hashMap);

                return s;
            }
        }

        actualizarEquipo ue = new actualizarEquipo();
        ue.execute();
    }


}

