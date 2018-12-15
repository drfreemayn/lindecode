package bigbois.reactiontest;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ReactionTest extends AppCompatActivity {

    EditText playerName;
    Button startButton;
    ImageView santaImg;
    TextView hitSanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_reaction_game);

        playerName = findViewById(R.id.playerText);

        startButton = findViewById(R.id.startButton);

        santaImg = findViewById(R.id.santaImg);
    }

    public void onStartGameClick(View v)
    {
        MediaPlayer santaSound = MediaPlayer.create(ReactionTest.this, R.raw.santa_sound);
        santaSound.start();
        RotateAnimation rotSanta = new RotateAnimation(santaImg.getRotation(), santaImg.getRotation() + 360, santaImg.getWidth()/2, santaImg.getHeight()/2);
        rotSanta.setDuration(1000);
        rotSanta.setFillAfter(true);
        SantaListener santaListener = new SantaListener(this);
        rotSanta.setAnimationListener(santaListener);
        santaImg.startAnimation(rotSanta);

    }

    public void onAnimationCompleted()
    {
        System.out.println("onAnimationCompletedStart");
        setContentView(R.layout.run_reaction_game);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hitSanta = findViewById(R.id.instructionTxt);
                santaImg = findViewById(R.id.santaImg);
                hitSanta.setVisibility(View.VISIBLE);
                santaImg.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

}


