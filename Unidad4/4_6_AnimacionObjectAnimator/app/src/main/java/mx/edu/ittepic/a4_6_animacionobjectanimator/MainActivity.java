package mx.edu.ittepic.a4_6_animacionobjectanimator;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imagen;
    private Button a1, a2, a3;
    private RelativeLayout canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = findViewById(R.id.imagen);
        a1 = findViewById( R.id.ani1 );
        a2 = findViewById( R.id.ani2 );
        a3 = findViewById( R.id.ani3 );
        canvas = findViewById(R.id.espacio);

        a1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int screenHeight = canvas.getHeight();
                int targetY = screenHeight - imagen.getHeight();

                ObjectAnimator animator = ObjectAnimator.ofFloat(
                        imagen, "y", (canvas.getHeight()-150), 1)
                        .setDuration(4000);

                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
            }
        } );
        a2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(
                        imagen, "y", (canvas.getHeight()-150), 1)
                        .setDuration(4000);
                animator.setInterpolator(new BounceInterpolator());
                animator.start();
            }
        } );
        a3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(
                        imagen, "y", (canvas.getHeight()-150), 1)
                        .setDuration(4000);
                animator.setInterpolator(new AnticipateOvershootInterpolator(2));
                animator.start();
            }
        } );
    }
}
