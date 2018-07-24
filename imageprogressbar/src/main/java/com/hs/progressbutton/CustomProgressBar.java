package com.hs.progressbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import java.lang.reflect.Field;

/**
 * @author Harish Sharma
 */
public class CustomProgressBar extends ProgressBar {

    private Class<?> mDrawableState;


    public CustomProgressBar(Context context) {
        super(context);
    }


    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * @param canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    /**
     * set the ring color showing the progess
     *
     * @param color
     */
    public void setProgressColor(int color) {
        LayerDrawable ld = (LayerDrawable)getProgressDrawable();
        GradientDrawable drawable = (GradientDrawable) ld.findDrawableByLayerId(android.R.id.progress);
        ((GradientDrawable) drawable).setColor(color);
    }

    /**
     * set the radius for the progressbar/ring.Use this to set the size of the view.
     *
     * @param radius
     */
    public void setInnerRadius(int radius) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        Drawable drawable = getProgressDrawable();
        LayerDrawable ld = (LayerDrawable)getProgressDrawable();
        GradientDrawable drawable = (GradientDrawable) ld.findDrawableByLayerId(android.R.id.progress);
        GradientDrawable drawable1 = (GradientDrawable) ld.findDrawableByLayerId(android.R.id.secondaryProgress);

        if (mDrawableState == null) mDrawableState = resolveState();
        if (mDrawableState != null) {
            Field innerRadius = resolveDeclaredField(mDrawableState, "mInnerRadius");
            innerRadius.setInt(drawable.getConstantState(), radius);
            innerRadius.setInt(drawable1.getConstantState(), radius);
        }
    }


    public void setColor(int color) throws SecurityException, IllegalArgumentException {
        LayerDrawable ld = (LayerDrawable)getProgressDrawable();
        GradientDrawable drawable = (GradientDrawable) ld.findDrawableByLayerId(android.R.id.secondaryProgress);
        ((GradientDrawable) drawable).setColors(new int[]{color,color,color});
//        this.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));

    }


    /**
     * set the thickness of the ring.
     *
     * @param thickness
     */
    public void setThickness(int thickness) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LayerDrawable ld = (LayerDrawable)getProgressDrawable();
        GradientDrawable drawable = (GradientDrawable) ld.findDrawableByLayerId(android.R.id.progress);
        GradientDrawable drawable1 = (GradientDrawable) ld.findDrawableByLayerId(android.R.id.secondaryProgress);
        if (mDrawableState == null) mDrawableState = resolveState();
        Field innerRadius = resolveDeclaredField(mDrawableState, "mThickness");
        innerRadius.setInt(drawable.getConstantState(), thickness);
        innerRadius.setInt(drawable1.getConstantState(), thickness);
    }


    private Class<?> resolveState() {
        Class<?>[] c = GradientDrawable.class.getDeclaredClasses();
        for (Class<?> aClass : c) {
            if (aClass.getSimpleName().equals("GradientState")) return aClass;
        }
        return null;
    }


    private Field resolveDeclaredField(Class<?> aClass, String fieldName) throws NoSuchFieldException {
        Field field = aClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}

