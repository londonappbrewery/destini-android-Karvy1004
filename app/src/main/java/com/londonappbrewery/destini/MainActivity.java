package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
        TextView story;
        Button topButton, bottomButton, endButton;
        int state = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            state = savedInstanceState.getInt("state");
        }

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        story = findViewById(R.id.storyTextView);
        bottomButton = findViewById(R.id.buttonBottom);
        topButton = findViewById(R.id.buttonTop);
        endButton = findViewById(R.id.buttonEnd);

        if (state == 1) goToState1();
        else if(state == 2) goToState2();
        else if (state == 3) goToState3();
        else getAlert();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 1 || state == 2) {
                    state = goToState3();
                }
                else if(state == 3) {
                    story.setText(R.string.T6_End);
                    topButton.setVisibility(View.GONE);
                    bottomButton.setVisibility(View.GONE);
                    state = 0;
                }
            }
        });



        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 1) {
                    state = goToState2();
                }
                else if(state == 2){
                    story.setText(R.string.T4_End);
                    topButton.setVisibility(View.GONE);
                    bottomButton.setVisibility(View.GONE);
                    state = 0;
                }
                else if(state == 3){
                    story.setText(R.string.T5_End);
                    topButton.setVisibility(View.GONE);
                    bottomButton.setVisibility(View.GONE);
                    state = 0;
                }
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlert();
            }
        });

    }
    int goToState3(){
        story.setText(R.string.T3_Story);
        topButton.setText(R.string.T3_Ans1);
        bottomButton.setText(R.string.T3_Ans2);
        return 3;
    }

    int goToState2(){
        story.setText(R.string.T2_Story);
        topButton.setText(R.string.T2_Ans1);
        bottomButton.setText(R.string.T2_Ans2);
        return 2;
    }

    int goToState1(){
        story.setText(R.string.T1_Story);
        topButton.setText(R.string.T1_Ans1);
        bottomButton.setText(R.string.T1_Ans2);
        return 1;
    }
    void getAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("The End");
        alert.setMessage("You have ended the story." +
                "\nTap elsewhere to continue. ");
        alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.setNeutralButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in = new Intent(MainActivity.this, MainActivity.class);
                finish();
                startActivity(in);
            }
        });
        alert.show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("state", state);
    }
}
