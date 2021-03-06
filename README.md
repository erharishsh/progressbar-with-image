# progressbar-with-image
A custom android component to show progress of some asynchronous operation with different images for different states.
![picture](https://user-images.githubusercontent.com/18004938/39628095-8f3b3c18-4fc5-11e8-82f7-ef0dd72baaee.png)
![picture](https://user-images.githubusercontent.com/18004938/39628143-b26f4594-4fc5-11e8-9e28-a706874e5510.png)
## USAGE
To make a circular progressbar with image just add the ProgressImageView in your XML.
Add the following line in your build.gradle to grab the library via gradle.

`compile 'com.hs.progressbutton:imageprogressbar:1.0'`

## XML
```
  <com.hs.progressbutton.ProgressImageView
        android:id="@+id/layout"
        android:layout_width="wrap_content"
        app:radius="100dp"
        app:thickness="10dp"
        app:start_image="@drawable/download_gray"
        app:end_image="@drawable/download"
        app:pause_image="@drawable/download_blue"
        app:progress_image="@drawable/download_grey"
        app:ring_color="#00ff0f"
        android:layout_height="wrap_content">
    </com.hs.progressbutton.ProgressImageView>
```

Here is the list of properties you must use to configure this view:
 1. app:radius (in dp) ==> sets the size of the view.
 2. app:thickness (in dp)  ==> sets the thickness of the progress ring.
 3. app:start_image (drawable)  ==> initial image to be shown on the view
 4. app:progress_image (drawable)  ==> image to be shown when in progress
 5. app:pause_image (drawable)  ==> image to be shown when progress is paused.
 6. app:end_image (drawable)  ==> image to be shown on completion of progress.
 7. app:ring_color (color)  ==> color of the circular progressbar.
 8. app:complete_ring_color (color) ==> color of circular progressbar on 100% progress
 9. app:state (integer) ==> use to define various states of view based on which image changes.
  
 
 
 #Note:
 images on the view can be updated by calling updateProgressState method over the view with current state of progress.
  Different states are (0==> ProgressImageView.ProgressState.START, 1==> ProgressImageView.ProgressState.PROGRESS,2==>   
  ProgressImageView.ProgressState.PAUSED,3==> ProgressImageView.ProgressState.END)
