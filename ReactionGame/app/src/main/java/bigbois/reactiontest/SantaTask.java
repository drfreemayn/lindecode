package bigbois.reactiontest;

import android.opengl.Visibility;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class SantaTask {

    TextView m_hitSanta;
    boolean m_visible;
    long m_delay;

    public SantaTask( TextView hitSanta, long delay, boolean visible)
    {
        m_hitSanta = hitSanta;
        m_delay = delay;
        m_visible = visible;
        run();
    }

    public void run() {
        Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                if (m_visible) {
                    m_hitSanta.setVisibility(View.VISIBLE);
                } else {
                    m_hitSanta.setVisibility(View.INVISIBLE);
                }
            }
        };

        handler.postDelayed(runnableCode, m_delay);
    }
}
