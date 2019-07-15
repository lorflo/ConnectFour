package my.cool.apps.connectfour;

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
        gameView.setBoard(board);
        setContentView(R.layout.activity_main);
        setContentView(gameView);
        gameView.setDiscClickListener(i -> toast(i + " was clicked"));
        Player player = new Player("Me");
        board.dropDisc(0, player);
        board.dropDisc(2, player);
        gameView.invalidate();

    }
    private void toast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
