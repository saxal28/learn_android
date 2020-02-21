package saxal.me.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String currentPlayer = "Player 1";
    String PLAYER_1 = "Player 1";
    String PLAYER_2 = "Player 2";

    Boolean isGameOver = false;

    ArrayList<String> player1Moves = new ArrayList<>();
    ArrayList<String> player2Moves = new ArrayList<>();
    HashMap<String,String> moves =  new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickPlayAgain(View view) {

        // reset moves array
        moves = new HashMap<>();
        player1Moves = new ArrayList<>();
        player2Moves = new ArrayList<>();

        // reset current player
        currentPlayer = PLAYER_1;

        // re-hide button
        view.animate().alpha(0);

        isGameOver = false;

        for(int i = 1; i <= 9; i++) {
            String move = String.valueOf(i);

            // reset pieces
            ImageView currentPiece = getCorrectPiece(move);
            currentPiece.animate().translationY(10).alpha(0).setDuration(0);

        }

    }

    public void handleMove(View view){

        if(isGameOver) return;

        String move = view.getTag().toString();


        // if tag is not in map, add && switch player;
        if(!moves.containsKey(move)) {
            Button currentButton = getCurrentButton(move);

           // add move to moves
            moves.put(move, currentPlayer);
            if(currentPlayer.equals(PLAYER_1)) {
                player1Moves.add(move);
            } else {
                player2Moves.add(move);
            }

            // handle checker dropdown
            handlePieceMove(move, currentButton);

            // check if victory
            checkVictory();

            // set current player
            currentPlayer = currentPlayer.equals(PLAYER_1) ? PLAYER_2 : PLAYER_1;

        } else {
            Log.i("ERROR MOVE", "MOVE ALREADY TAKEN");
        }

        if(moves.size() == 9) {
            Log.i("FULL!", "show reset button");
            showPlayAgainButton();
        }

    }

    private void handlePieceMove(String move, Button button) {

        ImageView targetPiece = getCorrectPiece(move);

        int[] intArray = new int[2];

        button.getLocationOnScreen(intArray);

        int x = intArray[0];
        int y = intArray[1];

        if(currentPlayer.equals(PLAYER_1)){
            targetPiece.setImageResource(R.drawable.playerone);
        } else {
            targetPiece.setImageResource(R.drawable.playertwo);
        }

        targetPiece
                .animate()
                .translationY(y - 160)
                .alpha(1)
                .setDuration(500);
    }

    private void checkVictory() {
        String[][] victoryConditions = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
                {"1", "8", "9"},
                {"2", "5", "8"},
                {"7", "8", "9"},
                {"1", "5", "9"},
                {"3", "5", "7"}
        };

        for(String[] x : victoryConditions) {

            ArrayList<String> victoryCondition = new ArrayList<>(Arrays.asList(x));
            ArrayList<String> relevantMoves;

            boolean didWin = false;

            //check current player moves
            if(currentPlayer.equals(PLAYER_1)) {
                relevantMoves = (ArrayList<String>) player1Moves.clone();
            } else {
                relevantMoves = (ArrayList<String>) player2Moves.clone();
            }

            relevantMoves.retainAll(victoryCondition);
            Collections.sort(relevantMoves);
            didWin = relevantMoves.equals(victoryCondition);

            if(didWin) {
                Toast.makeText(this, "Victory " + currentPlayer, Toast.LENGTH_SHORT).show();
                showPlayAgainButton();
                isGameOver = true;
            }
        }
    }

    private void showPlayAgainButton() {
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.animate().alpha(1);
    }

    private Button getCurrentButton(String move) {
        switch (move) {
            case "1":
                return findViewById(R.id.button1);
            case "2":
                return findViewById(R.id.button2);
            case "3":
                return findViewById(R.id.button3);
            case "4":
                return findViewById(R.id.button4);
            case "5":
                return findViewById(R.id.button5);
            case "6":
                return findViewById(R.id.button6);
            case "7":
                return findViewById(R.id.button7);
            case "8":
                return findViewById(R.id.button8);
            case "9":
                return findViewById(R.id.button9);
            default:
                return findViewById(R.id.button9);
        }
    }

    private ImageView getCorrectPiece(String move) {
        switch (move) {
            case "1":
                return findViewById(R.id.piece1);
            case "2":
                return findViewById(R.id.piece4);
            case "3":
                return findViewById(R.id.piece7);
            case "4":
                return findViewById(R.id.piece2);
            case "5":
                return findViewById(R.id.piece5);
            case "6":
                return findViewById(R.id.piece8);
            case "7":
                return findViewById(R.id.piece3);
            case "8":
                return findViewById(R.id.piece6);
            case "9":
                return findViewById(R.id.piece9);
            default:
                return findViewById(R.id.button9);
        }
    }
}
