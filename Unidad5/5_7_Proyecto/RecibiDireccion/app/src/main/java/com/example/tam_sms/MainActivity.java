package com.example.tam_sms;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tam_sms.Llamada;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements OnClickListener {

    //  GUI Widget 
    Button  btnInbox;
    TextView lblMsg, lblNo;
    RecyclerView lvMsg;
    Adapter adapter;
    DatabaseReference myRef;
    private  List<Llamada> llamada;

    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
private  static  final  String MENSAJE="Hola";
    // Cursor Adapter
    //SimpleCursorAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //getLlamadas();
        myRef = FirebaseDatabase.getInstance().getReference("mensajes");

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET)) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_READ_CONTACTS); }
        }
        lvMsg = findViewById(R.id.lvMsg);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CALL_LOG)) {
            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, MY_PERMISSIONS_REQUEST_READ_CONTACTS); }
        }

        // Init GUI Widget
        btnInbox = (Button) findViewById(R.id.btnInbox);
        btnInbox.setOnClickListener(this);
        lblNo=findViewById(R.id.lblNumber);
        lblMsg=findViewById(R.id.lblMsg);
        lvMsg = (RecyclerView) findViewById(R.id.lvMsg);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lvMsg.setLayoutManager(linearLayoutManager);
        lvMsg.setHasFixedSize(true);



    }

    @Override
    public void onClick(View v) {

        if (v == btnInbox) {

            List<Llamada> sms = new ArrayList<>();
            //Llamada(lblNo.getText().toString().trim(),lblMsg.getText().toString().trim());
            Uri uriSms = Uri.parse("content://sms/inbox");
            Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

            cursor.moveToFirst();
            while  (cursor.moveToNext())
            {
                String address = cursor.getString(1);
                String body = cursor.getString(3);
                if(body.indexOf("Lat")==-1){

                }
                else {
                    sms.add( new Llamada(address,body));
                    String id= myRef.push().getKey();
                    //Subir a firebase
                    Map<String, Object>datosUsuario=new HashMap<>();
                    datosUsuario.put("numero", "mensaje");
                    myRef.child("mensajes").push().setValue(datosUsuario);
                    Toast.makeText(this, "mensajes cargados", Toast.LENGTH_SHORT).show();

                }
            }
            adapter = new Adapter(this,sms);
            lvMsg.setAdapter(adapter);

        }
    }
   /* protected void onStart() {
        super.onStart();
        msj = new ArrayList<>();

        lblMsg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Llamada llamada = postSnapshot.getValue(Llamada.class);
                    char letra = ' ';
                    String pos = "";

                    for (int i = 0; i < llamada.length(); i++)
                        if(llamada.charAt(i) == letra)
                            pos += i+",";
                        }
                        String lon = llamada.subList(pos[0]+1,pos[1]+1);}
                        String lag = llamada.subList(pos[2]+1,pos[3]+1);
                        String des = llamada.subList(pos[4]+1,pos[5]+1);
                        addMarker(lat,lon, des);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
    /*private void addMarker(double lap, double lon, String des) {
        MarkerOptions options = new MarkerOptions();

        IconGenerator iconFactory = new IconGenerator(this);
        iconFactory.setStyle(IconGenerator.STYLE_PURPLE);
        options.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(mLastUpdateTime)));
        options.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

        LatLng currentLatLng = new LatLng(lap, lon);
        options.position(currentLatLng);
        Marker mapMarker = googleMap.addMarker(options);
        mapMarker.setTitle(des);
        Log.d(TAG, "Agrega.............................");
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
                13));
        Log.d(TAG, "Listo.............................");
    }*/
}