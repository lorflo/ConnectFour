package my.cool.apps.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;

import my.cool.apps.connectfour.Model.Board;
import my.cool.apps.connectfour.Model.Player;

public class GameView extends View {

    private int width, height, cellWidth,col = -1,row = -1; // width and height of this view in pixels
    private Board board;
    private Player player = null;
    private Player[][] discs = new Player[7][6];

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
        /** Background of the board. */
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0,cellWidth,width,width,paint);
        /** Draws the lines to form a grid
         * on the board. */
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(8);
        //Vertical Lines
        float sX = cellWidth;
        float sY = cellWidth;
        float eX = cellWidth;
        float eY = width;
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(sX, sY, eX,eY, paint);
            sX += cellWidth;
            eX += cellWidth ;
        }
        //Horizontal Lines
         sX = 0;
         sY = cellWidth;
         eX = width;
         eY = cellWidth;
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(sX, sY, eX,eY, paint);
            sY += cellWidth;
            eY += cellWidth ;
        }
        /** Draws the row of circles
         * at the top of board for player
         * to click on.*/
        if(player == null)
        {
            paint.setColor(Color.YELLOW);
        }
        else
        {
            paint.setColor(player.color());
        }
        float x = cellWidth / 2;
        float y = cellWidth / 2;
        for (int i = 0; i < 7; i++) {
            canvas.drawCircle(x, y, (float)((cellWidth / 2)*.8), paint);
            x += cellWidth ;
        }

        /** Screen coordinates are mapped
         * to a cell on the board. */
         for(int i = 0;i < 7;i++)
         {
             for(int j = 0;j < 6;j++)
             {
                 if(discs[i][j] != null)
                 {
                     paint.setColor(discs[i][j].color());
                     canvas.drawCircle((cellWidth / 2) + i * cellWidth,
                             (cellWidth * 3 / 2) + j * cellWidth,
                             (float) ((cellWidth / 2) * .8), paint);
                 }
             }
         }



    }//end of onDraw()
    public void setBoard(Board board)
    {
        this.board = board;
        board.setChangeListener((c,r,p) ->
        {
            if(c <= 6 && r <= 5) {
                col = c;
                row = r;
                player = p;
            }
            if(board.isWonBy(player))
            {
                player = new Player(player.name(),Color.BLUE);
                if(board.hasWinningRow())
                {
                    for( Board.Place winPlace: board.winningRow())
                    {
                        discs[winPlace.x][winPlace.y] = player;
                    }
                }
            }
            else
                discs[col][row] = player;
        });
    }


    //---------------------------------------------------------------------------------------------
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
                if(board.hasWinningRow())
                {
                    break;
                }
                int index = locateDisc(event.getX(), event.getY());
                if (index >= 0) { discClickListener.clicked(index); }
                break;
        }
        return true;
    }

    public int locateDisc(float x, float y)
    {
        for(int i = 0;i< 7;i ++) {
            if(y <= (cellWidth ))
                if( x<= (cellWidth *(i+1)))//x >= (cellWidth*i) &&)
                {
                    return i;
                }
        }
        return -1;
    }
}

