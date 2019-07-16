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

        Player[] players = new Player[2];
        players[0] = new Player("Player 1",Color.RED);
        players[1] = new Player("Player 2",Color.YELLOW);

        gameView.setBoard(board);
        setContentView(gameView);

        gameView.setDiscClickListener(index ->  {
            board.dropDisc(index, players[0]);
            gameView.invalidate();
        });


    }
}
