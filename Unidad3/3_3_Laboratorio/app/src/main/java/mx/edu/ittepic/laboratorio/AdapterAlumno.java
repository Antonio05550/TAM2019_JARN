package mx.edu.ittepic.laboratorio;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class AdapterAlumno extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Alumno> datos= Collections.emptyList();
    Alumno actual;
    int actualPos=0;

    public AdapterAlumno(Context context, List<Alumno> datos){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.datos=datos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.alumno_item, parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    // Bind datos
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        Holder holder= (Holder) viewHolder;
        Alumno actual=datos.get(position);
        holder.nctrl.setText("No. Ctrl: "+String.valueOf(actual.nctrl));
        holder.name.setText("Nombre: "+actual.name);
        holder.u1.setText("U1: "+String.valueOf(actual.u1));
        holder.u2.setText("U2: "+String.valueOf(actual.u2));
        holder.u3.setText("U3: "+String.valueOf(actual.u3));
        if(actual.getU1()>=70 && actual.getU2()>=70 && actual.getU3()>=70)
            holder.itemView.setBackgroundColor( Color.parseColor( "#DCEDC8" )  );
        else{
            if(actual.getU1()<70 && actual.getU2()>=70 && actual.getU3()>=70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFE0B2" )  );
            if(actual.getU1()>=70 && actual.getU2()>=70 && actual.getU3()<70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFE0B2" )  );
            if(actual.getU1()>=70 && actual.getU2()<70 && actual.getU3()>=70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFE0B2" )  );
            if(actual.getU1()>=70 && actual.getU2()<70 && actual.getU3()<70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFF9C4" )  );
            if(actual.getU1()<70 && actual.getU2()>=70 && actual.getU3()<70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFF9C4" )  );
            if(actual.getU1()<70 && actual.getU2()<70 && actual.getU3()>=70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFF9C4" )  );
            if(actual.getU1()<70 && actual.getU2()<70 && actual.getU3()<70)
                holder.itemView.setBackgroundColor( Color.parseColor( "#FFEBEE" )  );
        }

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        TextView nctrl;
        TextView name;
        TextView u1,u2,u3;

        public Holder(View itemView) {
            super(itemView);
            nctrl = (TextView) itemView.findViewById(R.id.nctrl);
            name = (TextView) itemView.findViewById(R.id.name);
            u1 = (TextView) itemView.findViewById(R.id.u1);
            u2 = (TextView) itemView.findViewById(R.id.u2);
            u3 = (TextView) itemView.findViewById(R.id.u3);
        }

    }

}