package com.hs.progressbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;


/**
 * @author Harish Sharma
 */
public class ProgressImageView extends FrameLayout {

    // default values for different parameteres.
    public static final int DEFAULT_RADIUS = 75;
    public static final int DEFAULT_THICKNESS = 3;
    public static final int DEFAULT_RING_COLOR = Color.parseColor("#ffff00ff");

    private ProgressState progressState;
    private int completeRingColor=-1;

    public enum ProgressState{
        START(0),PROGRESS(1),PAUSED(2),END(3);
        int value;
        ProgressState(int i) {
            value=i;
        }

        public int value(){
            return value;
        }

    }

    // custom views
    private CustomProgressBar mProgressBar;
    private CustomImageView mImageView;


    public ProgressImageView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ProgressImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public ProgressImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * initialize all the custom parameters for the view.
     *
     * @param context
     * @param attrs
     */
    void init(Context context, AttributeSet attrs) {
        int width = dpToPx(context, DEFAULT_RADIUS);
        int thickness = dpToPx(context, DEFAULT_THICKNESS);
        int ringColor = 0;
        int startImage = 0, endImage = 0,progressImage=0,pauseImage=0;
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressImageView, 0, 0);
        try {
            if (attrs != null) {
                width = (int) a.getDimension(R.styleable.ProgressImageView_radius, DEFAULT_RADIUS);
                thickness = (int) a.getDimension(R.styleable.ProgressImageView_thickness, DEFAULT_THICKNESS);
                ringColor = a.getColor(R.styleable.ProgressImageView_ring_color, DEFAULT_RING_COLOR);
                completeRingColor = a.getColor(R.styleable.ProgressImageView_complete_ring_color, ringColor);
                startImage = a.getResourceId(R.styleable.ProgressImageView_start_image, 0);
                endImage = a.getResourceId(R.styleable.ProgressImageView_end_image, 0);
                progressImage = a.getResourceId(R.styleable.ProgressImageView_progress_image, 0);
                pauseImage = a.getResourceId(R.styleable.ProgressImageView_pause_image, 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        View imageview = View.inflate(context, R.layout.custom_image_view, this);
        View progressbar = View.inflate(context, R.layout.progressbar, this);

        mImageView = imageview.findViewById(R.id.imageview);
        int circularImageWidth = (width - thickness) * 2;
        mImageView.setDesiredDimensions(circularImageWidth);
        mImageView.setLayoutParams(new LayoutParams(circularImageWidth, circularImageWidth, Gravity.CENTER));
        mImageView.setStartImage(startImage);
        mImageView.setEndImage(endImage);
        mImageView.setProgressImage(progressImage);
        mImageView.setPauseImage(pauseImage);

        mProgressBar = progressbar.findViewById(R.id.progress);
        mProgressBar.setLayoutParams(new LayoutParams(width * 2 + thickness, width * 2 + thickness, Gravity.CENTER));

        try {
            mProgressBar.setInnerRadius(width - thickness);
            mProgressBar.setThickness(thickness);
            mProgressBar.setProgressColor(ringColor);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (a != null)
            a.recycle();
    }

    /**
     * method to set the progress for progressbar/ring around the imageview.
     *
     * @param progress this should be between 0 to 100.
     */
    public void setProgress(int progress) {
        mProgressBar.setProgress(progress);
        if (progress == 100) {
            mImageView.showEndImage();
            mProgressBar.setProgressColor(completeRingColor);
        }
    }

    /**
     * method to set the progress for progressbar/ring around the imageview.
     *
     * @param progressState current state of the progress.Represented by the #{{@link ProgressState}}
     */
    public void updateProgressState(ProgressState progressState) {
        this.progressState=progressState;
      switch (progressState){
          case START:
              mImageView.showStartImage();break;
          case PROGRESS:
              mImageView.showProgressImage();break;
          case PAUSED:
              mImageView.showPauseImage();break;
          case END:
              mImageView.showEndImage();break;
      }
    }


    public void setState(int progressState) {
        switch (progressState){
            case 0:
                this.progressState=ProgressState.START;
                mImageView.showStartImage();break;
            case 1:
                this.progressState=ProgressState.PROGRESS;
                mImageView.showProgressImage();break;
            case 2:
                this.progressState=ProgressState.PAUSED;
                mImageView.showPauseImage();break;
            case 3:
                this.progressState=ProgressState.END;
                mImageView.showEndImage();break;
        }
    }

    /**
     * @param context
     * @param dimension dimension in dp to convert in pixel
     */
    public static int dpToPx(Context context, int dimension) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dimension * density);
    }


}

