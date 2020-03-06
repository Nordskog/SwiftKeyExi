package com.mayulive.swiftkeyexi.main.emoji;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mayulive.swiftkeyexi.R;

public class EmojiDropIndicator extends androidx.appcompat.widget.AppCompatImageView
{


	CoverType mCoverType = CoverType.CAN_DROP;

	enum CoverType
	{
		CAN_DROP(0.1f, 0.3f),
		CANNOT_DROP(0.5f, 0.2f);

		float activeAlpha;
		float inactiveAlpha;

		CoverType(float active, float inactive )
		{
			this.activeAlpha = active;
			this.inactiveAlpha = inactive;
		}
	}

	public EmojiDropIndicator(Context context)
	{
		super(context);
	}

	public EmojiDropIndicator(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
	}

	public EmojiDropIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}



	@Override
	public void setActivated(boolean activated)
	{
		super.setActivated(activated);

		if (activated)
		{
			this.setAlpha(mCoverType.activeAlpha);
		}
		else
		{
			this.setAlpha(mCoverType.inactiveAlpha);
		}
	}

	public void setVisible( boolean visible)
	{
		if (visible)
			setVisibility(View.VISIBLE);
		else
			setVisibility(View.GONE);
	}

	public void setMode( CoverType type )
	{
		mCoverType = type;

		switch(type)
		{

			case CAN_DROP:
			{
				this.setImageResource( R.drawable.emoji_add_background  );
				break;
			}

			case CANNOT_DROP:
			{
				this.setImageResource(R.drawable.emoji_blocked_background );
				break;
			}

		}

	}

	public void set(  boolean visible, boolean activated )
	{
		setVisible(visible);
		setActivated(activated);
	}

	public void set( CoverType type, boolean visible, boolean activated )
	{
		setMode(type);
		setVisible(visible);
		setActivated(activated);
	}
}
