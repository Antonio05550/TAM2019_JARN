package mx.edu.ittepic.a4_4_animacionxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView   imagen;
    Button      arriba, abajo, der, izq, cen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        imagen = findViewById( R.id.imagen );
        arriba = findViewById( R.id.arriba );
        abajo = findViewById( R.id.abajo );
        der = findViewById( R.id.der );
        izq = findViewById( R.id.izq );
        cen = findViewById( R.id.cen );

        arriba.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {                Animation animation =
                        AnimationUtils.loadAnimation(MainActivity.this, R.anim.top);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imagen.setScaleX(1);
                        imagen.setScaleY(1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                imagen.startAnimation(animation);
            }
        } );
        
        abajo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation =
                        AnimationUtils.loadAnimation(MainActivity.this, R.anim.down);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imagen.setScaleX(1);
                        imagen.setScaleY(1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                imagen.startAnimation(animation);
            }
        } );
        
        izq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation =
                        AnimationUtils.loadAnimation(MainActivity.this, R.anim.left );

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imagen.setScaleX(1);
                        imagen.setScaleY(1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                imagen.startAnimation(animation);
            }
        } );

        der.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation =
                        AnimationUtils.loadAnimation(MainActivity.this, R.anim.right );

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imagen.setScaleX(1);
                        imagen.setScaleY(1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                imagen.startAnimation(animation);
            }
        } );

        cen.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation =
                        AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin );

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imagen.setScaleX(1);
                        imagen.setScaleY(1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                imagen.startAnimation(animation);

            }
        } );
    }
}
