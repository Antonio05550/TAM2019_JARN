package mx.edu.ittepic.a3_5_servicioweb;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MultaList extends ArrayAdapter<Multa> {
    private Activity context;
    List<Multa> multas;

    public MultaList(Activity context, List<Multa> multas) {
        super(context, R.layout.activity_main, multas);
        this.context = context;
        this.multas = multas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View subView = inflater.inflate( R.layout.multa_layout, null );

        TextView numero = subView.findViewById( R.id.numero );
        TextView celular = subView.findViewById( R.id.celular );
        TextView correo = subView.findViewById( R.id.correo );


        Multa multa = multas.get( position );
        numero.setText( String.valueOf(multa.getNumero()));
        celular.setText( multa.getCelular());
        correo.setText( multa.getCorreo());

        return subView;
    }
}
