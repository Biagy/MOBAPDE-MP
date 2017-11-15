package edu.dlsu.mobidev.gem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class highScoreActivity extends AppCompatActivity {

    Button buttonHome;
    TextView tvTitle, tvTitle2, tvWins, tvLosses, tvGames, tvRate;
    EditText etWins, etLosses, etGames, etRate;


    //ToDo: Initialize Recycler View and adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        setContentView(R.layout.activity_high_score);

        buttonHome = (Button) findViewById(R.id.buttonHS);

        tvTitle = (TextView) findViewById(R.id.titleHS);
        tvTitle2 = (TextView) findViewById(R.id.title2HS);
        tvWins = (TextView) findViewById(R.id.tvWinHS);
        tvLosses = (TextView) findViewById(R.id.tvLossHS);
        tvGames = (TextView) findViewById(R.id.tvGamesHS);
        tvRate = (TextView) findViewById(R.id.tvRateHS);

        etWins = (EditText) findViewById(R.id.etWinsHS);
        etLosses = (EditText) findViewById(R.id.etLossHS);
        etGames = (EditText) findViewById(R.id.etGamesHS);
        etRate = (EditText) findViewById(R.id.etRateHS);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        });



    }
}
