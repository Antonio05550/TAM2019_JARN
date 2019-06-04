package mx.edu.ittepic.a4_2_laboratorio;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText numero;
    Button comenzar;
    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        numero = findViewById( R.id.numero );
        comenzar = findViewById( R.id.comenzar );
        resultado = findViewById( R.id.resultado );

        comenzar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText( "" );
                Tarea tarea = new Tarea();
                int num = Integer.parseInt( numero.getText().toString() );
                tarea.execute(num);
            }
        } );
    }
    private class Tarea extends AsyncTask<Integer,Integer,String> {

        @Override
        protected void onPreExecute() {
            // Precondici�n de instalaci�n para ejecutar alguna tarea
        }

        @Override
        protected String doInBackground(Integer... params) {
            // hacer alguna tarea
            int n1=0;
            int n2=1;
            int aux;
            publishProgress( n1 );
            publishProgress( n2 );
            try {
                for(int i=1;i<params[0]-1;i++){
                    Thread.sleep( 1000 );
                    aux=n1;
                    n1=n2;
                    n2=aux+n1;
                    publishProgress( n2 );
                }

            }catch (InterruptedException e){

            }catch (Exception e){

            }


            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText( MainActivity.this,""+String.valueOf( values[0] ),Toast.LENGTH_SHORT ).show();
            resultado.setText( resultado.getText() + String.valueOf( values[0] ) + "\n");
            // Actualizar el progreso de la tarea actual
        }

        @Override
        protected void onPostExecute(String s) {
            // Mostrar el resultado obtenido de doInBackground
            Toast.makeText( MainActivity.this,"Listo",Toast.LENGTH_SHORT ).show();
        }
    }
}
