package com.mayulive.swiftkeyexi.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Roughy on 9/25/2017.
 */

public class ResourceUtils
{

	//From https://stackoverflow.com/a/38244327/2312367
	public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId)
	{
		Drawable drawable = ContextCompat.getDrawable(context, drawableId);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
		{
			drawable = (DrawableCompat.wrap(drawable)).mutate();
		}

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

}
