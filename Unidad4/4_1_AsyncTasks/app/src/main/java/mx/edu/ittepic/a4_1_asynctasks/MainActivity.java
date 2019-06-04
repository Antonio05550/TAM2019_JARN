package mx.edu.ittepic.a4_1_asynctasks;

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

    EditText    segundos;
    Button      comenzar;
    ProgressBar progressBar;
    TextView    contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        segundos = findViewById( R.id.segundos );
        comenzar = findViewById( R.id.comenzar );
        progressBar = findViewById( R.id.progressBar );
        contador = findViewById( R.id.contador );

        comenzar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarea tarea = new Tarea();
                int seg = Integer.parseInt( segundos.getText().toString() );
                tarea.execute(seg);
            }
        } );

    }
    private class Tarea extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute() {
            // Precondición de instalación para ejecutar alguna tarea
            progressBar.setMax( 100 );
            progressBar.setProgress( 0 );
        }

        @Override
        protected String doInBackground(Integer... params) {
            // hacer alguna tarea
            //publishProgress( 1 );
            try{
                int tiempo = params[0]*1000;
                for(float i=100/tiempo;i<=100;i++){
                    Thread.sleep( tiempo/100 );

                    //if((int)i%1==0)
                        publishProgress ((int) i );
                }
            }catch (InterruptedException e){

            }catch (Exception e){

            }
            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // Actualizar el progreso de la tarea actual
            progressBar.setProgress( values[0] );
            contador.setText( (int) values[0]   + "/100" );
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText( MainActivity.this,"Listo",Toast.LENGTH_SHORT ).show();
        }
    }
}
