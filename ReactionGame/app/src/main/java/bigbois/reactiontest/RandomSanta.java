package bigbois.reactiontest;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class RandomSanta {

    int MAX_RANDOM_SANTA_DELAY = 5000;

    long m_startTime;
    ImageView m_image;
    Random m_random;
    int m_width;
    int m_height;

    public RandomSanta(ImageView image)
    {
        m_image = image;
        m_random = new Random();
    }

    public void start(long initalTime, int width, int height) {
        m_width = width;
        m_height = height;

        Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                m_image.setVisibility(View.VISIBLE);
                m_image.setX(m_random.nextInt(m_width));
                m_image.setY(m_random.nextInt(m_height));
                m_startTime = System.currentTimeMillis();
            }
        };

        int timeUntilSanta = m_random.nextInt(MAX_RANDOM_SANTA_DELAY);
        handler.postDelayed(runnableCode, initalTime + timeUntilSanta);
    }

    long stop()
    {
        return System.currentTimeMillis() - m_startTime;
    }
}
