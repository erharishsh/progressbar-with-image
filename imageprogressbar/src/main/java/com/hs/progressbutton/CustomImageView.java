package com.hs.progressbutton;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Harish Sharma
 */
@SuppressLint("AppCompatCustomView")
public class CustomImageView extends ImageView {

    //parameter to decide the radius of circular image
    private int desiredWidth;

    // images to be shown at start and end of progress.
    private int mStartImage, mEndImage,mProgressImage,mPauseImage,mErrorImage;

    /**
     * @param context 
     */
    public CustomImageView(Context context) {
        super(context);
    }

    /**
     * 
     * @param context 
     * @param attrs 
     */
    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 
     * @param context 
     * @param attrs 
     * @param defStyleAttr 
     */
    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 
     * @param context 
     * @param attrs 
     * @param defStyleAttr 
     * @param defStyleRes 
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * 
     * @param w 
     */
    public void setDesiredDimensions(int w) {
        desiredWidth = w;
    }

    /**
     * 
     * @param canvas 
     */
    @Override
    protected void onDraw(Canvas canvas) {

        Drawable imageDrawable = getDrawable();

        if (imageDrawable == null || getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b = ((BitmapDrawable) imageDrawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();

        if (desiredWidth < w || desiredWidth < h) {
            w = desiredWidth;
            Bitmap roundBitmap = cropBitmap(bitmap, w);
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        } else {
            super.onDraw(canvas);
        }


    }


    /**
     * crop the image bitmap in a circle with input radius
     * @param bitmap bitmap to be cropped
     * @param radius  radius of the circular bitmap in imageview.
     */
    public Bitmap cropBitmap(Bitmap bitmap, int radius) {
        Bitmap resultBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            resultBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            resultBitmap = bitmap;
        Bitmap finalBitmap = Bitmap.createBitmap(resultBitmap.getWidth(),
                resultBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(finalBitmap);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, resultBitmap.getWidth(),
                resultBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(resultBitmap.getWidth() / 2 + 0.7f,
                resultBitmap.getHeight() / 2 + 0.7f,
                resultBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(resultBitmap, rect, rect, paint);

        return finalBitmap;
    }

    /**
     *change the image drawable to the start image set.
     * @param drawable 
     */
    public void setStartImage(int drawable) {
        mStartImage = drawable;
        if (mStartImage != 0) {
            setImageResource(mStartImage);
        }
    }

    /**
     * change the image drawable to the start image set.
     */
    public void showStartImage() {
        if (mStartImage != 0)
            setImageResource(mStartImage);
    }

    /**
     * 
     * @param drawable 
     */
    public void setEndImage(int drawable) {
        mEndImage = drawable;
    }

    /**
     * change the image drawable to the end image set.
     */
    public void showEndImage() {
        if (mEndImage != 0)
            setImageResource(mEndImage);
    }


    public void setErrorImage(int drawable) {
        mErrorImage = drawable;
    }

    /**
     * change the image drawable to the end image set.
     */
    public void showErrorImage() {
        if (mErrorImage != 0)
            setImageResource(mErrorImage);
    }


    /**
     *set a image drawable for some intermediate state of progress.like in pause state
     * @param drawable
     */
    public void setProgressImage(int drawable) {
        mProgressImage = drawable;
    }

    /**
     * change the image drawable to the intermediate image.
     */
    public void showProgressImage() {
        if (mProgressImage != 0)
            setImageResource(mProgressImage);
    }



    /**
     *set a image drawable for some intermediate state of progress.like in pause state
     * @param drawable
     */
    public void setPauseImage(int drawable) {
        mPauseImage = drawable;
    }

    /**
     * change the image drawable to the intermediate image.
     */
    public void showPauseImage() {
        if (mPauseImage != 0)
            setImageResource(mPauseImage);
    }


}

