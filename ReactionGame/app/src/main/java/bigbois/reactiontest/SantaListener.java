package bigbois.reactiontest;

import android.view.animation.Animation;

public class SantaListener implements Animation.AnimationListener {

    ReactionTest m_activity;

    public SantaListener(ReactionTest activity)
    {
        m_activity = activity;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        try {
            m_activity.onAnimationCompleted();
        }
        catch  (Exception e){
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
