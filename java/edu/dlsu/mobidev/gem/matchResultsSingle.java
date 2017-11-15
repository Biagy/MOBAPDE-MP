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

public class matchResultsSingle extends AppCompatActivity {

    Button home;
    TextView tvTitle, tvHP, tvSSF, tvHitRate, tvRed, tvBlue, tvDiff, tvTime, tvWin, tvScore;
    EditText etHPR, etHPB, etSSFR, etSSFB, etRateR, etRateB, etDiff, etTime, etWin, etScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        setContentView(R.layout.activity_match_results_single);

        home = (Button) findViewById(R.id.buttonHomeSingle);

        tvTitle = (TextView) findViewById(R.id.tvTitleSingle);
        tvHP = (TextView) findViewById(R.id.tvHPSingle);
        tvSSF = (TextView) findViewById(R.id.tvSSFSingle);
        tvHitRate = (TextView) findViewById(R.id.tvHitRateSingle);
        tvRed = (TextView) findViewById(R.id.tvRedSingle);
        tvBlue = (TextView) findViewById(R.id.tvBlueSingle);
        tvDiff = (TextView) findViewById(R.id.tvDiffSingle);
        tvTime = (TextView) findViewById(R.id.tvTimeSingle);
        tvWin = (TextView) findViewById(R.id.tvWinSingle);
        tvScore = (TextView) findViewById(R.id.tvScoreSingle);

        etHPR = (EditText) findViewById(R.id.etHPRSingle);
        etHPB = (EditText) findViewById(R.id.etHPBSingle);
        etSSFR = (EditText) findViewById(R.id.etSSFRSingle);
        etSSFB = (EditText) findViewById(R.id.etSSFBSingle);
        etRateR = (EditText) findViewById(R.id.etRateRSingle);
        etRateB = (EditText) findViewById(R.id.etRateBSingle);
        etDiff = (EditText) findViewById(R.id.etDiffSingle);
        etTime = (EditText) findViewById(R.id.etTimeSingle);
        etWin = (EditText) findViewById(R.id.etWinSingle);
        etScore = (EditText) findViewById(R.id.etScoreSingle);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
