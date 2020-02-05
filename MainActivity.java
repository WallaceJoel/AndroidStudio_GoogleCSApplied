package com.example.scarnesdice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    public static String StartMessage = "First to reach a 100 wins the game, you can choose to keep" +
            "rolling, but if you roll a one, you'll gain no points. Good Luck!!";

    int DiceNumber = 1;
    int total =0;
    int playerScores[] = {0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RollDice(View view){
        String RollResult = "\n You just rolled a ";
        String RollOne = "1. Got to Know when to hold it SMH!!! NO POINTS GIVEN. Cpu turn now";


            Random rand = new Random();
            while (true) {
                DiceNumber = rand.nextInt(6);
                if (DiceNumber != 0) {
                    break;
                }
            }
        ImageView diceImg = (ImageView) (findViewById(R.id.Dice));
        TextView GamePlay = (TextView) (findViewById(R.id.GamePlay));
            switch (DiceNumber) {
                case 2:
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice2));
                    total += 2;
                    GamePlay.setText(RollResult + "2" + " The Total is " + total);
                    break;
                case 3:
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice3));
                    total += 3;
                    GamePlay.setText(RollResult + "3" + " The Total is " + total);
                    break;
                case 4:
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice4));
                    total += 4;
                    GamePlay.setText(RollResult + "4" + " The Total is " + total);
                    break;
                case 5:
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice5));
                    total += 5;
                    GamePlay.setText(RollResult + "5" + " The Total is " + total);
                    break;
                case 6:
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice6));
                    total += 6;
                    GamePlay.setText(RollResult + "6" + " The Total is " + total);
                    break;
                default:
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice));
                    total = 0;
                    GamePlay.setText(RollResult + RollOne);
                    ComputerPlay();

            }


    }
    public void HoldScore(View view){
        ImageView diceImg = (ImageView) (findViewById(R.id.Dice));
        TextView GamePlay = (TextView) (findViewById(R.id.GamePlay));
        TextView Score = (TextView) (findViewById(R.id.PlayerScore));
            playerScores[1] += total;
            Score.setText(Integer.toString(playerScores[1]));
            if(playerScores[1] >= 100){
                GamePlay.setText("YOU WONNNNNN!!!!");
                diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.winner));
            }else {
                GamePlay.setText("You chose to hold now, and gain " + total + " points, cpu turn now");
                total = 0;
                ComputerPlay();
            }

    }
    public void ComputerPlay(){
        Random rand  =  new Random();

        ImageView diceImg = (ImageView) (findViewById(R.id.Dice));
        TextView GamePlay = (TextView) (findViewById(R.id.GamePlay));
        TextView ComputerScore = (TextView) (findViewById(R.id.AIScore));
        while(true) {
            DiceNumber = rand.nextInt(6);
            if(DiceNumber == 0){
                continue;
            }
            if(DiceNumber == 1){
                GamePlay.setText("Computer rolled  a one, no points given. Player its your turn");
                total = 0;
                break;
            }
            total += DiceNumber;

            int HoldOrRoll = rand.nextInt(100);
            if(HoldOrRoll %2 == 0) { //If HoldOrRoll is even the computer will decide to hold his total, and allow
                //player to go
                playerScores[0] += total;
                if(playerScores[0] >= 100) {
                    GamePlay.setText("YOUUUUUUU LOSE!!!!");
                    diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.winner));
                }else{
                    GamePlay.setText("Computer scored "+total+ " on this turn, your turn player");
                }
                ComputerScore.setText(Integer.toString(playerScores[0]));
                total = 0;
                break;
            }
        }

    }
    public void Reset(View view){
        ImageView diceImg = (ImageView) (findViewById(R.id.Dice));
        TextView GamePlay = (TextView) (findViewById(R.id.GamePlay));
        TextView Score = (TextView) (findViewById(R.id.PlayerScore));
        TextView ComputerScore = (TextView) (findViewById(R.id.AIScore));

        diceImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dice));
        GamePlay.setText("First to win 100 will win, Man vs Machine, are YOU MAN ENOUGH???");
        Score.setText("0");
        ComputerScore.setText("0");
        total = 0;
        playerScores[0] = 0;
        playerScores[1] = 0;
    }
    public boolean defaultView (View view){

        return true;
    }

}
