package com.mayulive.swiftkeyexi.main.emoji;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Roughy on 7/8/2017.
 */

public class EmojiPanelCommons
{
	//I give up, this shit does nothing.
	//You apparenlty cannot cancel animations in android :|
	private static void clearAnimation(View view)
	{
		Animation animation = view.getAnimation();

		view.clearAnimation();
		if (animation != null)
		{
			animation.cancel();

		}
	}

	public static void setTouchAnimation(View itemView)
	{
		//Sorta the same as the swiftkey stock animation

		final float startSize = 1f;
		final float endSize = 1.4f;
		final float overshoot = 5f;
		final long duration = 250;

		itemView.setOnTouchListener(new View.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
				{
					//clearAnimation(v);

					ObjectAnimator xAnimator = ObjectAnimator.ofFloat(v, "scaleX", startSize, endSize);
					ObjectAnimator yAnimator = ObjectAnimator.ofFloat(v, "scaleY", startSize, endSize);
					xAnimator.setDuration(duration);
					yAnimator.setDuration(duration);

					xAnimator.setInterpolator(new OvershootInterpolator(overshoot));
					yAnimator.setInterpolator(new OvershootInterpolator(overshoot));

					AnimatorSet animator = new AnimatorSet();

					animator.play(xAnimator).with(yAnimator);
					animator.start();



				}
				else if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL )
				{
					//clearAnimation(v); //Necessary if this animation is shorter than the down animation,
					//but all attempts to cancel it have failed. I give up.

					ObjectAnimator xAnimator = ObjectAnimator.ofFloat(v, "scaleX", endSize, startSize);
					ObjectAnimator yAnimator = ObjectAnimator.ofFloat(v, "scaleY", endSize, startSize);
					xAnimator.setDuration(duration);
					yAnimator.setDuration(duration);


					xAnimator.setInterpolator(new OvershootInterpolator(overshoot));
					yAnimator.setInterpolator(new OvershootInterpolator(overshoot));

					AnimatorSet animator = new AnimatorSet();
					animator.play(xAnimator).with(yAnimator);
					animator.start();
				}

				return false;
			}
		});


	}












}
