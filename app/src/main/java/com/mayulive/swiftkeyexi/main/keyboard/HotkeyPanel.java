package com.mayulive.swiftkeyexi.main.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mayulive.swiftkeyexi.util.MathUtils;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

/**
 * Created by Roughy on 5/22/2017.
 */

public class HotkeyPanel extends View
{

	private int mHighlightColor = 0x2d5bc6;

	public HotkeyPanel(Context context)
	{
		super(context);
		init(context);
	}

	public HotkeyPanel(Context context, int color)
	{
		super(context);
		mHighlightColor = color;
		init(context);
	}

	public HotkeyPanel(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public HotkeyPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context)
	{
		mPaint = new Paint();
		mPaint.setStrokeWidth(4);
		mPaint.setColor(Color.DKGRAY);
		mPaint.setTypeface(Typeface.create( Typeface.DEFAULT_BOLD, Typeface.BOLD));
		mPaint.setTextAlign(Paint.Align.CENTER);
		mPaint.setTextSize( mCircleHeight * 0.15f );
		mPaint.setColor(0xFF306db2);
		mPaint.setAlpha(200);
		mPaint.setAntiAlias(true);

		refresh();
	}

	/////////////////////////////////////
	/////////////////////////////////////

	Paint mPaint = null;

	Path mContentPath = new Path();

	Path mCircle = new Path();

	HotkeyMenuItem mSelectedItem = null;
	HotkeyMenuItem mLastSelectedItem = null;


	MathUtils.Vector2D mCenter;

	float mHorizontalCenterRatio = 0.5f;

	float mContentPosition = 0.8f;
	float mContentHeight;		//Distance of content from center

	float mTargetRadius = 700;	//Desired radius of circle

	float mMaxRatioOfWidth = 0.75f;	//Diameter may not be greater than this ratio of view width

	float mBottomMargin = 100;	//Distance of center from bottom of parent
	float mCoverTop = 600;	//Distance from top of screen to cover background
	float mCircleHeight = 500;	//Height of circle from center, calculated from targetRadius

	float mDividerLineRatioCenter = 0f;
	float mDividerLineRatioOuter = 1f;

	public void setCoverTop(float top)
	{
		mCoverTop = top;
	}

	public void setHorizontalCenterRadio(float centerRatio)
	{
		mHorizontalCenterRatio = centerRatio;
	}

	public void setTargetRadius(float radius)
	{
		mTargetRadius = radius;
		refresh();
	}

	public void setContentPosition(float position)
	{
		mContentPosition = position;
		refresh();
	}

	public void setBottomMargin(float margin)
	{
		mBottomMargin = margin;
		refresh();
	}

	private void updateCenters()
	{
		mCenter = new MathUtils.Vector2D( (float)this.getMeasuredWidth() * mHorizontalCenterRatio,  this.getMeasuredHeight() - mBottomMargin);

		mCircleHeight = mTargetRadius;
		if (mCircleHeight > this.getMeasuredWidth() * 0.5f)
		{
			mCircleHeight = this.getMeasuredWidth() * 0.5f;
		}

		//Leave some padding
		float maxRadius = this.getMeasuredWidth() * mMaxRatioOfWidth;
		maxRadius /= 2f;

		//Check side clearance
		float maxRadius_X = mCenter.x;
		if (mCenter.x > this.getMeasuredWidth() * 0.5f)
			maxRadius_X = this.getMeasuredWidth() - mCenter.x;
		maxRadius = maxRadius_X < maxRadius ? maxRadius_X : maxRadius;

		if (mCircleHeight > maxRadius)
			mCircleHeight = maxRadius;

		mContentHeight = mCircleHeight * mContentPosition;
	}


	private static class HotkeyMenuItem
	{
		String text = "";
		float width = 1;
		KeyboardInteraction.TextAction action = KeyboardInteraction.TextAction.COPY;

		float contentOnPath = 0;
		boolean selected = false;

		float startAngle = 0;
		float endAngle = 0;

		boolean isFirst = false;
		boolean isLast = false;

		MathUtils.Vector2D line_after_start;
		MathUtils.Vector2D line_after_end;

		MathUtils.Vector2D line_before_start;
		MathUtils.Vector2D line_before_end;

		HotkeyMenuItem(String text, KeyboardInteraction.TextAction action, float width)
		{
			this.text = text;
			this.width = width;
			this.action = action;
		}

		//Input should be a radian float value where 0 is straight up.
		//left is positive, right is negative.
		public boolean containsAngle(float angle)
		{
			if (isFirst)
			{
				if (angle < endAngle)
					return true;
			}
			if (isLast)
				if (angle > startAngle)
					return true;
			if (angle > startAngle && angle < endAngle)
				return true;

			return false;
		}
	}

	//String[] mItems = new String[]{"↑", "ALL", "COPY", "PASTE", "CUT", "↑"};
	//Hardcoded for now, strings will probably need to be presets anyway.
	HotkeyMenuItem[] mItems = new HotkeyMenuItem[]
			{
					//new HotkeyMenuItem ("↑", 	0.5f),
					new HotkeyMenuItem ("ALL", KeyboardInteraction.TextAction.SELECT_ALL, 	1),
					new HotkeyMenuItem ("COPY", KeyboardInteraction.TextAction.COPY, 	1),
					new HotkeyMenuItem ("PASTE", KeyboardInteraction.TextAction.PASTE, 	1),
					//new HotkeyMenuItem ("CUT", 		1),
					new HotkeyMenuItem ("END", 	KeyboardInteraction.TextAction.GO_TO_END, 	0.5f),
			};

	//float[] mItemWidth = new float[]{0.5f, 1, 1, 1, 1, 0.5f};


	private final static float NINTENTY_DEGREES =  (float) (Math.PI*2) / 4;
	private final static float ONE_EIGHTY_DEGREES =  (float) (Math.PI) ;
	private final static float THREE_SIXTY_DEGREES =  (float) (Math.PI*2) ;


	private void initPath()
	{
		mContentPath = new Path();

		mContentPath.addArc(new RectF( mCenter.x-(mContentHeight),
										(float)this.getMeasuredHeight()-mContentHeight - mBottomMargin,
										mCenter.x+(mContentHeight),
										(float)this.getMeasuredHeight() +mContentHeight - mBottomMargin ),
				180,180
		);

		mCircle = new Path();


		mCircle.addCircle(mCenter.x, mCenter.y, mCircleHeight, Path.Direction.CW);
		mCircle.addCircle(mCenter.x, mCenter.y, (mCircleHeight*0.5f) - ( (mCircleHeight*0.5f) * mDividerLineRatioCenter ), Path.Direction.CW);



		mCircle.setFillType(Path.FillType.EVEN_ODD);
	}

	public KeyboardInteraction.TextAction getLastSelectedAction()
	{
		if (mLastSelectedItem != null)
		{
			return mLastSelectedItem.action;
		}

		return KeyboardInteraction.TextAction.DEFAULT;
	}

	private void normalizeWidths()
	{

		float itemTotal = 0;
		for (HotkeyMenuItem item : mItems)
		{
			itemTotal += item.width;
		}
		for (HotkeyMenuItem item : mItems)
		{
			item.width /= itemTotal;
		}
	}

	private void checkSelection(float angle)
	{
		for (HotkeyMenuItem item : mItems)
		{
			if (item.containsAngle(angle))
			{
				select(item);
				break;
			}
		}
	}

	public void select(HotkeyMenuItem item)
	{
		mLastSelectedItem = item;

		if (item == mSelectedItem)
		{
			return;
		}

		if (mSelectedItem != null)
			mSelectedItem.selected = false;

		mSelectedItem = item;
		mSelectedItem.selected = true;

		this.invalidate();
	}

	public void deselect()
	{
		if (mSelectedItem != null)
			mSelectedItem.selected = false;
		mSelectedItem = null;

		this.invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getActionMasked();
		switch(action)
		{
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_CANCEL:
			{
				deselect();
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				handleTouch(event.getX(),event.getY());
				break;
			}

			default:
		}

		return true;
	}

	private void initItems()
	{
		//mItems[2].selected = true;
		//mItems[0].selected = true;



		normalizeWidths();


		//Full length
		float arcLength = MathUtils.getCircleCircumference(mContentHeight);
		arcLength /= 2f;	//half circle
		float lengthpro = - arcLength / 2;	//0 is straight up, rotate by 90 degrees to flat


		float lineAngle = NINTENTY_DEGREES*2;

		for (int i = 0; i < mItems.length; i++)
		{
			HotkeyMenuItem item = mItems[i];

			if (i == 0)
				item.isFirst = true;
			else if (i == mItems.length-1)
				item.isLast = true;

			float angleBetweenLines =  (float) (Math.PI) * item.width ;

			calculateItem(item, lineAngle, angleBetweenLines);

			float sectionLength = arcLength * item.width;

			lengthpro += sectionLength * 0.5f;	//Move to center
			item.contentOnPath = lengthpro;

			lengthpro += sectionLength * 0.5f;	//Move to end
			lineAngle += angleBetweenLines;
		}
	}


	public void handleTouch(float x, float y)
	{
		//Don't update selection based on input until we have been through layout
		if (this.getMeasuredWidth() == 0)
		{
			return;
		}

		MathUtils.Vector2D vector = MathUtils.Vector2D.subtract(new MathUtils.Vector2D(x,y), mCenter);

		float angle = (float) Math.atan2(vector.x, vector.y);

		//This angle value gives us 0 at center, negative going right, positive going left.
		//And I apparently got it backwards
		angle *= -1f;

		checkSelection(angle);

		//Log.e("###", "Angle is: "+angle);
	}

	private HotkeyMenuItem calculateItem(HotkeyMenuItem newItem, float angle, float angleBetween)
	{

		MathUtils.Vector2D firstVector = MathUtils.AngleToVector(angle);
		MathUtils.Vector2D secondVector = MathUtils.AngleToVector(angle+angleBetween);

		firstVector.multiplyInPlace(mCircleHeight);
		secondVector.multiplyInPlace(mCircleHeight);


		MathUtils.Vector2D after_halfVector = new MathUtils.Vector2D(secondVector).multiplyInPlace(0.5f);
		MathUtils.Vector2D before_halfVector = new MathUtils.Vector2D(firstVector).multiplyInPlace(0.5f);

		newItem.line_after_start = new MathUtils.Vector2D( mCenter ).addInPlace( after_halfVector.multiply(1f-mDividerLineRatioCenter) );
		newItem.line_after_end = new MathUtils.Vector2D( mCenter ).addInPlace( after_halfVector.multiply(1f+mDividerLineRatioOuter) );

		newItem.line_before_start = new MathUtils.Vector2D( mCenter ).addInPlace( before_halfVector.multiply(1f-mDividerLineRatioCenter) );
		newItem.line_before_end = new MathUtils.Vector2D( mCenter ).addInPlace( before_halfVector.multiply(1f+mDividerLineRatioOuter) );

		newItem.startAngle = angle;
		newItem.endAngle = angle+angleBetween;

		//These angles are from 3.14 to 6.28.
		//They're more convenient in negative - 0 - positive format.
		newItem.startAngle -= ONE_EIGHTY_DEGREES + NINTENTY_DEGREES;
		newItem.endAngle -= ONE_EIGHTY_DEGREES + NINTENTY_DEGREES;

		return newItem;
	}

	private void drawItems(Canvas canvas)
	{
		mPaint.setColor(mHighlightColor);

		for (int i = 0; i < mItems.length; i++)
		{
			HotkeyMenuItem item = mItems[i];

			if (item.selected)
			{
				Path circlePath = new Path();

				circlePath.moveTo(mCenter.x, mCenter.y);

				MathUtils.Vector2D farPointA = MathUtils.Vector2D.subtract( mCenter, item.line_before_end).multiplyInPlace(1.5f).addInPlace( mCenter );
				MathUtils.Vector2D farPointB = MathUtils.Vector2D.subtract( mCenter, item.line_after_end).multiplyInPlace(1.5f).addInPlace( mCenter);

				circlePath.lineTo( farPointA.x, farPointA.y);
				circlePath.lineTo( farPointB.x, farPointB.y);
				circlePath.close();

				canvas.save();
				canvas.clipPath(circlePath, Region.Op.INTERSECT);
				canvas.drawPath(mCircle, mPaint);
				canvas.restore();
			}
		}

		for (int i = 0; i < mItems.length; i++)
		{
			HotkeyMenuItem item = mItems[i];

			mPaint.setColor(Color.WHITE);

			mPaint.setAlpha(100);
			if (i != mItems.length-1)
				canvas.drawLine(
						item.line_after_start.x,
						item.line_after_start.y,
						item.line_after_end.x,
						item.line_after_end.y, mPaint
				);


			mPaint.setAlpha(255);


			canvas.drawTextOnPath(item.text, mContentPath, item.contentOnPath, 0, mPaint);


		}
	}

	private void updateFontSize()
	{
		mPaint.setTextSize( mCircleHeight * 0.15f );
	}


	private void drawBackground(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);

		paint.setAlpha(200);

		canvas.drawRect(0,mCoverTop, this.getMeasuredWidth(), this.getMeasuredHeight(), paint);
	}

	protected void refresh()
	{
		updateCenters();
		initPath();
		initItems();
		updateFontSize();
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w,h,oldw,oldh);
		refresh();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		drawBackground(canvas);
		drawItems(canvas);
	}
}
