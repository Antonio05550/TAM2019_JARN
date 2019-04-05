package mx.edu.ittepic.a3_5_servicioweb;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MultaAdapter extends RecyclerView.Adapter<MultaViewHolder>{
    private Context context;
    private List<Multa> multas;
    private DatabaseReference dbMulta;


    public MultaAdapter(Context context, List<Multa> multas, DatabaseReference dbMulta) {
        this.context = context;
        this.multas = multas;
        this.dbMulta = dbMulta;
    }

    @Override
    public MultaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multa_layout, parent, false);
        return new MultaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultaViewHolder holder, int position) {
        final Multa multa = multas.get(position);
        holder.numero.setText( String.valueOf(multa.getNumero()));
        holder.celular.setText( multa.getCelular());
        holder.correo.setText( multa.getCorreo());
        if(multa.getPuntos()>10)
            holder.itemView.setBackgroundColor( Color.parseColor( "#FFEBEE" )  );
        //"#EF9597" "#E6CFCF"

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarMulta( multa );
            }
        });

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View subView = inflater.inflate(R.layout.det_multa_layout, null);

                final EditText numeroC = subView.findViewById(R.id.numero);
                final EditText montoC = subView.findViewById(R.id.monto );
                final EditText puntosC = subView.findViewById(R.id.puntos);
                numeroC.setEnabled( false );
                montoC.setEnabled( false );
                puntosC.setEnabled( false );

                if(multa != null){
                    numeroC.setText("Matrícula: "+String.valueOf(multa.getNumero()));
                    montoC.setText("Monto total: $"+String.valueOf(multa.getMonto()));
                    puntosC.setText("Total de Puntos: "+String.valueOf(multa.getPuntos()));
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Detalles Multa");
                builder.setView(subView);
                builder.create();

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    public void deleteItem(String id) {
        eliminarMultar(id);
        ((Activity)context).finish();
        context.startActivity(((Activity)context).getIntent());
    }

    @Override
    public int getItemCount() {
        return multas.size();
    }

    private void editarMulta(final Multa multa){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_multa_layout, null);

        final EditText numeroC = subView.findViewById(R.id.numero );
        final EditText celularC = subView.findViewById(R.id.celular );
        final EditText correoC = subView.findViewById(R.id.correo);
        final EditText montoC = subView.findViewById(R.id.monto );
        final EditText puntosC = subView.findViewById(R.id.puntos);

        if(multa != null){
            numeroC.setText( String.valueOf(multa.getNumero()));
            celularC.setText( multa.getCelular());
            correoC.setText( multa.getCorreo());
            montoC.setText( String.valueOf(multa.getMonto()));
            puntosC.setText( String.valueOf(multa.getPuntos()));

            Toast.makeText( context, "Id:" + multa.getId(),Toast.LENGTH_SHORT ).show();

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar Multa");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int numero = Integer.parseInt(numeroC.getText().toString());
                final String celular = celularC.getText().toString();
                final String correo = correoC.getText().toString();
                final int monto = Integer.parseInt(montoC.getText().toString());
                final int puntos = Integer.parseInt(puntosC.getText().toString());

                if(numero==0 || TextUtils.isEmpty(celular) || TextUtils.isEmpty(correo)
                        || monto==0 || puntos==0){
                    Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    actMulta( multa.getId(),numero, celular, correo, monto,puntos);
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
    }
    public void eliminarMultar(String id)
    {
        DatabaseReference databaseReferenceMulta=FirebaseDatabase.getInstance().getReference("Multas").child(id);
        databaseReferenceMulta.removeValue();
        Toast.makeText(context,"Multa Eliminada",Toast.LENGTH_SHORT).show();
    }

    public boolean actMulta(String id, int numero, String celular, String correo, int monto, int puntos)
    {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Multas").child(id);
        Multa multa=new Multa(id,numero, celular, correo, monto,puntos);
        databaseReference.setValue(multa);
        Toast.makeText(context,"Multa actualizada",Toast.LENGTH_SHORT).show();
        return true;
    }
}

