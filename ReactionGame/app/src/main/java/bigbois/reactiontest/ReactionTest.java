package bigbois.reactiontest;

import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactionTest extends AppCompatActivity {

    HashMap<String, String> highscore = new HashMap<>();
    Player player;
    EditText playerName;
    Button startButton, restartButton, stopButton, scoreboardButton;
    ImageView santaImg;
    TextView hitSanta;
    LinearLayout entriesLayout;
    RandomSanta m_randSanta;
    String namePlayer;
    int m_playCounter, m_width, m_height;
    long m_totalReactionTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = new Player();
        setContentView(R.layout.start_reaction_game);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        playerName = findViewById(R.id.playerText);
        startButton = findViewById(R.id.startButton);
        santaImg = findViewById(R.id.santaImg);

        Display display = getWindowManager().getDefaultDisplay();
        m_width = display.getWidth() / 2;
        m_height = display.getHeight() / 2;
        m_totalReactionTime = 0;
        m_playCounter = 0;
    }

    public void goToStartPage(View v)
    {
        setContentView(R.layout.start_reaction_game);
        santaImg = findViewById(R.id.santaImg);
    }

    public void goToScoreboard(View v)
    {
        setContentView(R.layout.scoreboard);

        entriesLayout = findViewById(R.id.entriesLayout);

        int size = 5; // total number of TextViews to add
        TextView[] tv = new TextView[size];

        int i = 0;
        for (Map.Entry player : highscore.entrySet()) {
            TextView temp = new TextView(this);
            temp.setTextSize(20);
            temp.setTextColor(0xFF288F11);
            String entry = String.valueOf(i +1) + ". " + player.getKey() + " : " + player.getValue() + " ms";
            temp.setText(entry);
            entriesLayout.addView(temp);
            tv[i] = temp;
            System.out.println("Key: "+player.getKey() + " & Value: " + player.getValue());
        }
    }

    public void onStartGameClick(View v)
    {
        playerName = findViewById(R.id.playerText);
        namePlayer = playerName.getText().toString();
        player.setName(namePlayer);

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
        setContentView(R.layout.run_reaction_game);
        hitSanta = findViewById(R.id.instructionTxt);
        santaImg = findViewById(R.id.santaImg);

        new SantaTask(hitSanta, 1500, true);
        new SantaTask(hitSanta, 3500, false);

        m_randSanta = new RandomSanta(santaImg);
        m_randSanta.start(5000, m_width, m_height);
    }

    public void playSantaAgain(View v)
    {
        restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.INVISIBLE);

        stopButton = findViewById(R.id.stopButton);
        stopButton.setVisibility(View.INVISIBLE);

        scoreboardButton = findViewById(R.id.scoreboardButton);
        scoreboardButton.setVisibility(View.INVISIBLE);

        hitSanta.setVisibility(View.INVISIBLE);
        m_randSanta = new RandomSanta(santaImg);
        m_randSanta.start(500, m_width, m_height);
    }

    public String showStatistics()
    {
        float avgReactionTime = ((float) m_totalReactionTime) / m_playCounter;
        String time = namePlayer + ", your average reaction time is " + String.valueOf(avgReactionTime) + " milliseconds!";
        hitSanta.setText(time);
        hitSanta.setTextSize(16);
        hitSanta.setVisibility(View.VISIBLE);

        restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.VISIBLE);

        stopButton = findViewById(R.id.stopButton);
        stopButton.setVisibility(View.VISIBLE);

        scoreboardButton = findViewById(R.id.scoreboardButton);
        scoreboardButton.setVisibility(View.VISIBLE);

        m_playCounter = 0;
        m_totalReactionTime = 0;
        return   String.valueOf(avgReactionTime);
    }

    public void hitTheSanta(View v){
        hitSanta = findViewById(R.id.instructionTxt);
        santaImg = findViewById(R.id.santaImg);

        santaImg.setVisibility(View.INVISIBLE);
        m_totalReactionTime += m_randSanta.stop();
        m_playCounter++;

        final int MAX_SANTA_COUNT = 3;
        if (m_playCounter < MAX_SANTA_COUNT) {
            playSantaAgain(v);
        }
        else
        {
            highscore.put(player.getName(), showStatistics());
            new ResultPoster(namePlayer, String.valueOf(m_playCounter));
        }
    }
}


