package mx.edu.ittepic.a4_3_graficos2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;

public class Reloj extends View {

    private int alto, ancho = 0;
    private int relleno = 0;
    private int letra = 0;
    private int espaciadoN = 0;
    private int minutero, horario = 0;
    private int radio = 0;
    private Paint paint;
    private boolean estado;
    private int[] numeros = {1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();

    public Reloj(Context context) {
        super(context);
    }

    public Reloj(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Reloj(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void inicioClock() {
        alto = getHeight();
        ancho = getWidth();
        relleno = espaciadoN + 50;
        letra = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                getResources().getDisplayMetrics());
        int min = Math.min(alto, ancho);
        radio = min / 2 - relleno;
        minutero = min / 20;
        horario = min / 7;
        paint = new Paint();
        estado = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!estado) {
            inicioClock();
        }

        canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        drawCenter(canvas);
        drawNumeral(canvas);
        drawHands(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? radio - minutero - horario : radio - minutero;
        canvas.drawLine(ancho / 2, alto / 2,
                (float) (ancho / 2 + Math.cos(angle) * handRadius),
                (float) (alto / 2 + Math.sin(angle) * handRadius),
                paint);
    }

    private void drawHands(Canvas canvas) {
        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + c.get(Calendar.MINUTE) / 60) * 5f, true);
        drawHand(canvas, c.get(Calendar.MINUTE), false);
        drawHand(canvas, c.get(Calendar.SECOND), false);
    }

    private void drawNumeral(Canvas canvas) {
        paint.setTextSize(letra);

        for (int number : numeros) {
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, 0, tmp.length(), rect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (ancho / 2 + Math.cos(angle) * radio - rect.width() / 2);
            int y = (int) (alto / 2 + Math.sin(angle) * radio + rect.height() / 2);
            canvas.drawText(tmp, x, y, paint);
        }
    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ancho / 2, alto / 2, 12, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(ancho / 2, alto / 2, radio + relleno - 10, paint);
    }

}