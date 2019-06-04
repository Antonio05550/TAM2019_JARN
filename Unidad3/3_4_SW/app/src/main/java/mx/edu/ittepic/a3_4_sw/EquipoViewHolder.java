package mx.edu.ittepic.a3_4_sw;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipoViewHolder extends RecyclerView.ViewHolder {

    public TextView nombre, desc;
    public ImageView editar;

    public EquipoViewHolder(View itemView){
        super(itemView);
        nombre = itemView.findViewById(R.id.nombre );
        desc = itemView.findViewById(R.id.desc );
        editar = itemView.findViewById(R.id.editar);
    }
}
