package bigbois.reactiontest;

import android.view.animation.Animation;

public class SantaListener implements Animation.AnimationListener {

    ReactionTest s_activity;

    public SantaListener(ReactionTest activity)
    {
        s_activity = activity;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        System.out.println("onAnimationEnd");
        try {
            s_activity.onAnimationCompleted();
        }
        catch  (Exception e){
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
