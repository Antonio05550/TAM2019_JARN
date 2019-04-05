package mx.edu.ittepic.a3_5_servicioweb;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText num;
    Button buscar;
    RecyclerView listView;
    MultaAdapter multaListAdapter;
    FloatingActionButton agregar;

    List<Multa> multas;
    DatabaseReference dbMulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        dbMulta = FirebaseDatabase.getInstance().getReference("Multas");

        num = findViewById( R.id.id );
        buscar = findViewById( R.id.buscar );
        listView = findViewById( R.id.listView );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);
        agregar = findViewById( R.id.agregar );
        new ItemTouchHelper( new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                multaListAdapter.deleteItem( multas.get( viewHolder.getAdapterPosition()).getId() );
            }
        }).attachToRecyclerView(listView);

        agregar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarMulta();
            }
        } );

    }

    @Override
    protected void onStart() {
        super.onStart();
        multas = new ArrayList<>();

        dbMulta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Multa multa = postSnapshot.getValue(Multa.class);
                    multas.add(multa);
                }
                multaListAdapter = new MultaAdapter(MainActivity.this, multas, dbMulta);
                listView.setAdapter(multaListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void agregarMulta(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_multa_layout, null);

        final EditText numeroC = subView.findViewById(R.id.numero );
        final EditText celularC = subView.findViewById(R.id.celular );
        final EditText correoC = subView.findViewById(R.id.correo);
        final EditText montoC = subView.findViewById(R.id.monto );
        final EditText puntosC = subView.findViewById(R.id.puntos);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Multa");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int numero = Integer.parseInt(numeroC.getText().toString()) ;
                final String celular = celularC.getText().toString();
                final String correo = correoC.getText().toString();
                final int monto = Integer.parseInt(montoC.getText().toString());
                final int puntos = Integer.parseInt(puntosC.getText().toString());

                if(numero<=0 || TextUtils.isEmpty(celular) || TextUtils.isEmpty(correo)
                        || monto<=0 || puntos<=0){
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    String id = dbMulta.push().getKey();
                    Multa multa = new Multa(id, numero, celular, correo, monto,puntos );
                    dbMulta.child( id ).setValue( multa );
                    Toast.makeText( MainActivity.this, "Multa agregada",Toast.LENGTH_SHORT ).show();
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
