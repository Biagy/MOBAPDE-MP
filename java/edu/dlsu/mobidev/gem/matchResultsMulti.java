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

public class matchResultsMulti extends AppCompatActivity {

    Button home;
    TextView tvTitle, tvHP, tvSSF, tvHitRate, tvRed, tvBlue, tvTime, tvWin;
    EditText etHPR, etHPB, etSSFR, etSSFB, etRateR, etRateB, etTime, etWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        setContentView(R.layout.activity_match_results_multi);

        home = (Button) findViewById(R.id.buttonHomeMulti);

        tvTitle = (TextView) findViewById(R.id.tvTitleMulti);
        tvHP = (TextView) findViewById(R.id.tvHPMulti);
        tvSSF = (TextView) findViewById(R.id.tvSSFMulti);
        tvHitRate = (TextView) findViewById(R.id.tvHitRateMulti);
        tvRed = (TextView) findViewById(R.id.tvRedMulti);
        tvBlue = (TextView) findViewById(R.id.tvBlueMulti);
        tvTime = (TextView) findViewById(R.id.tvTimeMulti);
        tvWin = (TextView) findViewById(R.id.tvWinMulti);


        etHPR = (EditText) findViewById(R.id.etHPRMulti);
        etHPB = (EditText) findViewById(R.id.etHPBMulti);
        etSSFR = (EditText) findViewById(R.id.etSSFRMulti);
        etSSFB = (EditText) findViewById(R.id.etSSFBMulti);
        etRateR = (EditText) findViewById(R.id.etRateRMulti);
        etRateB = (EditText) findViewById(R.id.etRateBMulti);
        etTime = (EditText) findViewById(R.id.etTimeMulti);
        etWin = (EditText) findViewById(R.id.etWinMulti);

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
