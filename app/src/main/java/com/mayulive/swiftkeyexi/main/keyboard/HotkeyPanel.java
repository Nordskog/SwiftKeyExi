package com.mayulive.swiftkeyexi.main.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.util.MathUtils;
import com.mayulive.swiftkeyexi.util.ThemeUtils;
import com.mayulive.swiftkeyexi.xposed.KeyboardInteraction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Roughy on 5/22/2017.
 */

public class HotkeyPanel extends View
{
	String LOGTAG = ExiModule.getLogTag(HotkeyPanel.class);

	public static final int NO_COVER = -1;

	//TODO replace non-config interaction with this too
	OnInteractionEndedListener mInteractionListener = null;

	private enum WidgetMode
	{
		USER, CONFIG
	}

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

		mAccentColor = ThemeUtils.getThemeAccentColor(context);

		refresh();
	}

	/////////////////////////////////////
	/////////////////////////////////////

	WidgetMode mMode = WidgetMode.USER;

	private boolean mInteracting = false;

	Paint mPaint = null;

	Path mContentPath = new Path();
	Path mCircle = new Path();

	HotkeyMenuItem mSelectedItem = null;
	HotkeyMenuItem mLastSelectedItem = null;

	int mPrimaryColor = Color.WHITE;	//Draw white unless otherwise configured
	int mAccentColor = Color.RED;
	private int mHighlightColor = 0x2d5bc6;

	MathUtils.Vector2D mCenter;

	//Pixel sizes that are configured manually
	float mContentHeight;		//Distance of content from center
	float mTargetRadius = Float.MAX_VALUE;	//Desired radius of circle
	float mBottomMargin = 0;	//Distance of center from bottom of parent
	float mCoverTop = NO_COVER;	//Distance from top of screen to cover background
	float mCircleHeight = 500;	//Height of circle from center, calculated from targetRadius
	float mHandleLength = 600;

	float mHandleCircleRadius = 50;

	//Ratios
	float mDividerLineRatioCenter = 0f;
	float mDividerLineRatioOuter = 1f;
	float mHorizontalCenterRatio = 0.5f;
	float mContentPosition = 0.8f;
	float mMaxRatioOfWidth = 0.75f;	//Diameter may not be greater than this ratio of view width

	float mHandleLengthRatio = 1.5f;	//Ratio of circle height
	float mHandleRadiusRatio = 0.05f;		//Ratio of circle height
	float mHandleCirclePositionRatio = 0.75f;

	float mTextSizeRatio = 0.15f;

	float mMinWidth = 0.05f;

	//Cover top distance from bottom.
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

	public void setTextSizeRatio(float ratio)
	{
		mTextSizeRatio = ratio;
		updateFontSize();
		this.invalidate();
	}

	public void setHighlightColor(int color)
	{
		mHighlightColor = color;
	}

	public float getTextSizeRatio()
	{
		return mTextSizeRatio;
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
		if (mMode == WidgetMode.CONFIG)
			maxRadius = this.getMeasuredWidth();
		maxRadius /= 2f;

		//Check side clearance
		float maxRadius_X = mCenter.x;
		if (mCenter.x > this.getMeasuredWidth() * 0.5f)
			maxRadius_X = this.getMeasuredWidth() - mCenter.x;
		maxRadius = maxRadius_X < maxRadius ? maxRadius_X : maxRadius;

		//Check height clerance given measured height
		if(this.getMeasuredHeight() != 0)
		{
			float maxHeight = this.getMeasuredHeight() - mBottomMargin;
			if (maxHeight < maxRadius)
				maxRadius = maxHeight;
		}


		if (mCircleHeight > maxRadius)
			mCircleHeight = maxRadius;

		//And if we are in config mode, we need to accommodate the handle
		if (mMode == WidgetMode.CONFIG)
		{
			mHandleCircleRadius = mCircleHeight * mHandleRadiusRatio;


			mHandleLength = mCircleHeight;
			mCircleHeight = mCircleHeight / mHandleLengthRatio;


		}

		mContentHeight = mCircleHeight * mContentPosition;
	}

	public void enableConfigMode(boolean on)
	{
		mMode = on ? WidgetMode.CONFIG : WidgetMode.USER;
		refresh();
	}

	public void setOnInteractionEndedListener(OnInteractionEndedListener listener)
	{
		mInteractionListener = listener;
	}

	public void setPrimaryColor(int color)
	{
		mPrimaryColor = color;
	}


	public static class HotkeyMenuItem
	{
		///////////////
		//Set values
		///////////////
		public int position = 0;	//For sorting, done manually before creating views
		public String text = "";
		public float calculatedWidth = 1;
		public KeyboardInteraction.TextAction action = KeyboardInteraction.TextAction.COPY;

		///////////////////////
		//Calculated values
		///////////////////////
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

		//Handle is drawn from line_after_start
		MathUtils.Vector2D handle;
		MathUtils.Vector2D handle_circle;

		public HotkeyMenuItem(String text, KeyboardInteraction.TextAction action, float calculatedWidth, int position)
		{
			this.text = text;
			this.calculatedWidth = calculatedWidth;
			this.action = action;
			this.position = position;
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

		public static class HotkeyMenuItemComparator implements Comparator<HotkeyMenuItem>
		{
			@Override
			public int compare(HotkeyMenuItem a, HotkeyMenuItem b)
			{
				if (a.position == b.position)
					return 0;
				else if (a.position < b.position)
					return -1;
				else
					return 1;
			}
		}
	}

	public void setItems(List<? extends HotkeyMenuItem> items)
	{
		mItems = items;

		//Make sure none of the items are already marked as selected
		for (HotkeyPanel.HotkeyMenuItem item : items)
		{
			item.selected = false;
		}


	}

	//String[] mItems = new String[]{"↑", "ALL", "COPY", "PASTE", "CUT", "↑"};
	//Hardcoded for now, strings will probably need to be presets anyway.
	List<? extends HotkeyMenuItem> mItems = new ArrayList<HotkeyMenuItem>()
	{{
					//new HotkeyMenuItem ("↑", 	0.5f),
					add(new HotkeyPanel.HotkeyMenuItem ("ALL", KeyboardInteraction.TextAction.SELECT_ALL, 	1f, 1));
					add(new HotkeyPanel.HotkeyMenuItem ("COPY", KeyboardInteraction.TextAction.COPY, 	1f, 2));
					add(new HotkeyPanel.HotkeyMenuItem ("PASTE", KeyboardInteraction.TextAction.PASTE, 	1f, 3));
					add(new HotkeyPanel.HotkeyMenuItem ("END", 	KeyboardInteraction.TextAction.GO_TO_END, 	0.5f, 4));
	}};

	//float[] mItemWidth = new float[]{0.5f, 1, 1, 1, 1, 0.5f};


	private final static float NINTENTY_DEGREES =  (float) (Math.PI*2) / 4;
	private final static float ONE_EIGHTY_DEGREES =  (float) (Math.PI) ;
	private final static float THREE_SIXTY_DEGREES =  (float) (Math.PI*2) ;


	private void initPath()
	{
		mContentPath = new Path();

		/*
		mContentPath.addArc(new RectF( mCenter.x-(mContentHeight),
										(float)this.getMeasuredHeight()-mContentHeight - mBottomMargin,
										mCenter.x+(mContentHeight),
										(float)this.getMeasuredHeight() +mContentHeight - mBottomMargin ),
				180,180
		);
		*/
		mContentPath.addCircle(mCenter.x, mCenter.y, mContentHeight, Path.Direction.CW );

		//We want the start/end points to be ... off somewhere, at the bottom in our case.
		//If we attempt to draw text that extends before/after the endpoints, it just gets smoshed.
		Matrix rotMatrix = new Matrix();
		rotMatrix.postRotate(90, mCenter.x, mCenter.y);
		mContentPath.transform(rotMatrix);

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
			itemTotal += item.calculatedWidth;
		}
		for (HotkeyMenuItem item : mItems)
		{
			item.calculatedWidth /= itemTotal;
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

		//I don't know how, but an item now
		//somehow managed to stay marked as selected
		for (HotkeyMenuItem item : mItems)
		{
			item.selected = false;
		}

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
			{
				if (mInteracting)
				{
					mInteracting = false;
					if (mInteractionListener != null)
					{
						mInteractionListener.onInteractionEnded();
					}
				}
				//No I am not forgetting a break break
			}
			case MotionEvent.ACTION_CANCEL:
			{
				deselect();
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				if (mMode == WidgetMode.CONFIG)
					handleConfigTouch(event.getX(),event.getY());
				else
				{
					mInteracting = true;	//Always true
					handleTouch(event.getX(),event.getY());
				}

				break;
			}
			case MotionEvent.ACTION_DOWN:
			{
				deselect();
				if (mMode == WidgetMode.CONFIG)
					handleDown(event.getX(),event.getY());
				break;
			}

			default:
		}

		return true;
	}

	private void initItems()
	{
		normalizeWidths();

		//Full length
		float arcLength = MathUtils.getCircleCircumference(mContentHeight);
		arcLength /= 2f;	//half circle
		float lengthpro = - arcLength / 2;	//Path is rotated so start/end is at the bottom
											//Went want to start drawing text from left center,
											//So offset path progess by -90 degrees
											//Despite the start/end points being at the bottom,
											//0 actually refers to the 180 opposite point.... I think.
											//It's weird, with this it ends up in the right spot.

		float lineAngle = NINTENTY_DEGREES*2;

		for (int i = 0; i < mItems.size(); i++)
		{
			HotkeyMenuItem item = mItems.get(i);


			item.isFirst = i == 0;
			item.isLast = i == mItems.size()-1;

			float angleBetweenLines =  (float) (Math.PI) * item.calculatedWidth;

			calculateItem(item, lineAngle, angleBetweenLines);

			float sectionLength = arcLength * item.calculatedWidth;

			lengthpro += sectionLength * 0.5f;	//Move to center
			item.contentOnPath = lengthpro;

			lengthpro += sectionLength * 0.5f;	//Move to end
			lineAngle += angleBetweenLines;
		}
	}

	public void handleDown(float x, float y)
	{
		//Don't update selection based on input until we have been through layout
		if (this.getMeasuredWidth() == 0)
		{
			return;
		}

		MathUtils.Vector2D touchPos = new MathUtils.Vector2D(x,y);
		float closestPosition = Float.MAX_VALUE;
		for (int i = 0; i < mItems.size()-1; i++)
		{
			HotkeyMenuItem item = mItems.get(i);
			float distance = item.handle_circle.distance(touchPos);
			if (distance < closestPosition && distance <= (mHandleCircleRadius*2) )
			{
				mSelectedItem = item;
				closestPosition = distance;
				mInteracting = true;
			}
		}

		if (mSelectedItem != null)
			this.getParent().requestDisallowInterceptTouchEvent(true);
	}

	public void handleConfigTouch(float x, float y)
	{
		//Don't update selection based on input until we have been through layout
		if (this.getMeasuredWidth() == 0 || mSelectedItem == null)
		{
			return;
		}

		MathUtils.Vector2D touchPos = new MathUtils.Vector2D(x,y);

		//Limit y position to center position
		if (touchPos.y >= mCenter.y)
			touchPos.y = mCenter.y;

		//Get angle diff to new position
		MathUtils.Vector2D vectorToPos = touchPos.subtract(mCenter).normalizeInPlace();
		MathUtils.Vector2D vectorToEnd = mSelectedItem.handle.subtract(mCenter).normalizeInPlace();
		float angleBetween = (float) ( Math.atan2(vectorToEnd.y, vectorToEnd.x) - Math.atan2(vectorToPos.y, vectorToPos.x) );

		//We want position going right, negative going left
		angleBetween *= -1f;

		//Log.i("exi/TEST", "Angle between: "+ angleBetween);

		//Given that width units are currently normalized to add up to 1, calculate how much to add/remove from self
		float fullArch = NINTENTY_DEGREES*2;

		float widthDiff = angleBetween / fullArch;

		mSelectedItem.calculatedWidth += widthDiff;

		HotkeyMenuItem next =  mItems.get(mItems.indexOf(mSelectedItem)+1);
		next.calculatedWidth -= widthDiff;

		//But let's keep them within a certain size
		if (mSelectedItem.calculatedWidth < mMinWidth)
		{
			float widthCorrection = Math.abs( mMinWidth - mSelectedItem.calculatedWidth );
			mSelectedItem.calculatedWidth += widthCorrection;
			next.calculatedWidth -= widthCorrection;
		}
		if (next.calculatedWidth < mMinWidth)
		{
			float widthCorrection = Math.abs( mMinWidth - next.calculatedWidth );
			mSelectedItem.calculatedWidth -= widthCorrection;
			next.calculatedWidth += widthCorrection;
		}


		refresh();

		//Log.i("exi/TEST", "WidthDiff: "+    widthDiff);
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

		after_halfVector.multiplyInPlace(2f).multiplyInPlace(mHandleLengthRatio);
		newItem.handle = new MathUtils.Vector2D( mCenter ).addInPlace( after_halfVector );
		newItem.handle_circle = new MathUtils.Vector2D( mCenter ).addInPlace( after_halfVector.multiplyInPlace( mHandleCirclePositionRatio ) );

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
		mPaint.setAlpha(255);

		for (int i = 0; i < mItems.size(); i++)
		{
			HotkeyMenuItem item = mItems.get(i);

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

		for (int i = 0; i < mItems.size(); i++)
		{
			HotkeyMenuItem item = mItems.get(i);

			mPaint.setColor(mPrimaryColor);

			mPaint.setAlpha(100);
			if (i != mItems.size()-1)
				canvas.drawLine(
						item.line_after_start.x,
						item.line_after_start.y,
						item.line_after_end.x,
						item.line_after_end.y, mPaint
				);


			mPaint.setAlpha(255);

			if (mItems.size() > 1)
			{
				//Clip so text doesn't bleed into neighbors
				//Clipping to end of separator lines
				Path clipPath = new Path();
				clipPath.moveTo(mCenter.x, mCenter.y);

				//Needs to be a bit farther away
				MathUtils.Vector2D farPointA = MathUtils.Vector2D.subtract( mCenter, item.line_before_end).multiplyInPlace(20f).addInPlace( mCenter );
				MathUtils.Vector2D farPointB = MathUtils.Vector2D.subtract( mCenter, item.line_after_end).multiplyInPlace(20f).addInPlace( mCenter);

				clipPath.lineTo(farPointA.x, farPointA.y);
				clipPath.lineTo(farPointB.x, farPointB.y);
				clipPath.close();

				canvas.save();
				canvas.clipPath(clipPath, Region.Op.INTERSECT);
			}

			canvas.drawTextOnPath(item.text, mContentPath, item.contentOnPath, 0, mPaint);

			if (mItems.size() > 1)
			canvas.restore();


		}
	}

	private void updateFontSize()
	{
		mPaint.setTextSize( mCircleHeight * mTextSizeRatio );
	}


	private void drawBackground(Canvas canvas)
	{
		if (mCoverTop != NO_COVER)
		{
			mPaint.setColor(Color.BLACK);
			mPaint.setAlpha(200);

			canvas.drawRect(0,mCoverTop, this.getMeasuredWidth(), this.getMeasuredHeight(), mPaint);
		}

	}

	private void drawConfigInterface(Canvas canvas)
	{
		if (mMode != WidgetMode.CONFIG)
			return;


		mPaint.setColor(mPrimaryColor);

		//It's difficult to tell if everything is evenly distributed without something to compare to
		float lineAngle = NINTENTY_DEGREES*2;

		int count = 36;
		int sections = 4;
		float increment = ONE_EIGHTY_DEGREES / count;
		int sectionIncrements = count / sections;		//5
		int dividerCounter = sectionIncrements;
		int betweenLinesAlpha = (int)(255*0.25);
		mPaint.setAlpha(betweenLinesAlpha);
		for (int i = 0; i <= count; i++)
		{
			MathUtils.Vector2D vector = MathUtils.AngleToVector(lineAngle);
			MathUtils.Vector2D startPos = vector.multiply( (mHandleLength * mHandleCirclePositionRatio) - mHandleCircleRadius );	//Just below circle
			 startPos.multiplyInPlace(1.0f).addInPlace(mCenter);

			MathUtils.Vector2D endPos = vector.multiply( (mHandleLength * mHandleCirclePositionRatio) + mHandleCircleRadius );
			endPos.multiplyInPlace(1f).addInPlace(mCenter);

			canvas.drawLine(startPos.x, startPos.y, endPos.x, endPos.y, mPaint);
			if (dividerCounter == sectionIncrements)
			{
				mPaint.setAlpha(255);
				dividerCounter = 0;
				endPos = vector.multiply( (mHandleLength * mHandleCirclePositionRatio) + mHandleCircleRadius );
				endPos.multiplyInPlace(1.1f).addInPlace(mCenter);
				canvas.drawLine(startPos.x, startPos.y, endPos.x, endPos.y, mPaint);
				mPaint.setAlpha(betweenLinesAlpha);
			}

			dividerCounter++;
			lineAngle += increment;
		}

		mPaint.setAlpha(255);


		for (int i = 0; i < mItems.size()-1; i++)
		{
			HotkeyMenuItem item = mItems.get(i);

			mPaint.setColor(mAccentColor);
			canvas.drawCircle( item.handle_circle.x, item.handle_circle.y, (float)(mHandleCircleRadius) , mPaint );
		}
	}

	public void refresh()
	{
		if (mItems == null)
			return;

		updateCenters();
		initPath();
		initItems();
		updateFontSize();
		this.invalidate();
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
		drawConfigInterface(canvas);
		drawItems(canvas);
	}

	public interface OnInteractionEndedListener
	{
		void onInteractionEnded();
	}
}
