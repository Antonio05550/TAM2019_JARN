package mx.edu.ittepic.listadesensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    RecyclerView recycler = null;
    Adapter adapter;
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setHasFixedSize(true);

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<mx.edu.ittepic.listadesensores.Sensor> data = new ArrayList<>();
        for(int i=1; i<lista.size(); i++){
            data.add(new mx.edu.ittepic.listadesensores.Sensor(lista.get(i).getName(), lista.get(i).getVendor(), lista.get(i).getVersion()));
        }
        adapter = new Adapter(this, data );
        recycler.setAdapter(adapter);
    }
}