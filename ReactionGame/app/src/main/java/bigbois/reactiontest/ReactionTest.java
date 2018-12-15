package bigbois.reactiontest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ReactionTest extends AppCompatActivity {

    EditText playerName;
    Button startButton;
    ImageView santaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);

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
        santaImg.startAnimation(rotSanta);

        System.out.println(playerName.getText().toString());
    }
}
