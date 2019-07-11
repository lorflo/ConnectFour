package my.cool.apps.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class GameView extends View {

    private int width, height, cellWidth; // width and height of this view in pixels

    public GameView(Context context) {
        super(context);
        calculateWidthAndHeight();
    }

    private void calculateWidthAndHeight() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    width = Math.min(getWidth(), getHeight());
                    height = Math.max(getWidth(), getHeight());
                    cellWidth = width / 7;
                }
            });
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        /** background board. */
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0,cellWidth,width,width,paint);

        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(8);
        /** vertical lines. */
        float sX = cellWidth;
        float sY = cellWidth;
        float eX = cellWidth;
        float eY = width;
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(sX, sY, eX,eY, paint);
            sX += cellWidth;
            eX += cellWidth ;
        }
        /** horizontal lines. */
         sX = 0;
         sY = cellWidth;
         eX = width;
         eY = cellWidth;
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(sX, sY, eX,eY, paint);
            sY += cellWidth;
            eY += cellWidth ;
        }
        /** line of Circles */
        paint.setColor(Color.BLUE);
        float x = cellWidth / 2;
        float y = cellWidth / 2;
        for (int i = 0; i < 7; i++) {
            canvas.drawCircle(x, y, (float)((cellWidth / 2)*.8), paint);
            x += cellWidth ;
        }


    }
    /**
    public interface DiscClickListener {
        void clicked(int index);
    }

    private DiscClickListener discClickListener;

    public void setDiscClickListener(DiscClickListener listener) {
        discClickListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                int index = locateDisc(event.getX(), event.getY());
                if (index >= 0) { discClickListener.clicked(index); }
                break;
        }
        return true;
    }

    public int locateDisc(float x, float y) {return 0; }
*/}

