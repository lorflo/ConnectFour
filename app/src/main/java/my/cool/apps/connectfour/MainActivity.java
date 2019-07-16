package my.cool.apps.connectfour;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import my.cool.apps.connectfour.Model.Board;
import my.cool.apps.connectfour.Model.Player;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        board = new Board();
        gameView = new GameView(this);

        Player player1 = new Player("Player 1",Color.RED);
        Player player2 = new Player("Player 2",Color.YELLOW);

        gameView.setBoard(board);
        setContentView(gameView);

        gameView.setDiscClickListener(index ->  {
            if(gameView.turnChange() == true)
                board.dropDisc(index, player1);
            else
                board.dropDisc(index,player2);
            gameView.invalidate();
        });


    }
}
