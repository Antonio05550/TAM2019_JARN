package mx.edu.ittepic.listadesensores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    private Context context;
    private List<Sensor> sensores;

    public Adapter(Context context, List<Sensor> sensores) {
        this.context = context;
        this.sensores = sensores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Sensor sensor = sensores.get(position);
        holder.nombre.setText( sensor.getNombre());
        holder.fabricante.setText( sensor.getFabricante());
        holder.version.setText( String.valueOf(sensor.getVersion()));
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context,"" + position,Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    public int getItemCount() {
        return sensores.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder {

    public TextView nombre, fabricante, version;

    public ViewHolder(View itemView){
        super(itemView);
        nombre = itemView.findViewById(R.id.nombre );
        fabricante = itemView.findViewById(R.id.fabricante );
        version = itemView.findViewById(R.id.version);
    }
}
