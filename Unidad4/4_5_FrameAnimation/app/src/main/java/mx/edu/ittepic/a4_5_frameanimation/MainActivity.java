package mx.edu.ittepic.a4_5_frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button play, pausa;
    ImageView imagen;
    AnimationDrawable animacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        play = findViewById( R.id.play );
        pausa = findViewById( R.id.pausa );
        imagen = findViewById( R.id.imagen );

        if (imagen==null) throw new AssertionError();

        imagen.setBackgroundResource(R.drawable.animation);
        imagen.setVisibility(View.INVISIBLE);

        animacion = (AnimationDrawable)imagen.getBackground();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagen.setVisibility(View.VISIBLE);

                if (animacion.isRunning())
                    animacion.stop();
                animacion.start();
            }
        });
        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacion.stop();
            }
        });

    }
}
