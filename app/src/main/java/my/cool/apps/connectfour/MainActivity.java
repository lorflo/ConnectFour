package my.cool.apps.connectfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import my.cool.apps.connectfour.Model.Board;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        board = new Board();
        gameView = new GameView(this);
        setContentView(R.layout.activity_main);
        setContentView(gameView);

    }
}
