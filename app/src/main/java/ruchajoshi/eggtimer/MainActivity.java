package ruchajoshi.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerseekbar;
    TextView timertextview;
    Button timercontrol;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerseekbar= (SeekBar) findViewById(R.id.timerseekbar);
        timertextview=(TextView)findViewById(R.id.timertextview);
        timercontrol= (Button) findViewById(R.id.controlbutton);

        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);

        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void updateTimer(int secondsLeft){

        int minutes =(int)secondsLeft/60;
        int seconds=secondsLeft - minutes*60;

        String secondString= Integer.toString(seconds);

        if(seconds<=9){

            secondString= "0" + secondString;
        }

        timertextview.setText(Integer.toString(minutes)+" : " + secondString);

    }

    public void resetTimer (){

        timertextview.setText("0:30");
        timerseekbar.setProgress(30);
        countDownTimer.cancel();
        timercontrol.setText("Go");
        timerseekbar.setEnabled(true);
        counterIsActive=false;
    }

    public void controlTimer(View view){

        if (counterIsActive== false){

            counterIsActive=true;
            timerseekbar.setEnabled(false);
            timercontrol.setText("stop");

            countDownTimer = new CountDownTimer(timerseekbar.getProgress()*1000 + 100,1000) {

            @Override
            public void onTick(long l) {

               updateTimer((int)l/1000);

            }

            @Override
            public void onFinish() {
                resetTimer();
                MediaPlayer mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
            }

            }.start();

            }

            else{
            resetTimer();

        }
    }


}
