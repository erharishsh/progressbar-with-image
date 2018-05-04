# progressbar-with-image
A custom android component to show progress of some asynchronous operation with different images for different states.
![picture](https://user-images.githubusercontent.com/18004938/39627475-a9381c64-4fc3-11e8-80cb-285a245138b2.png)
![picture](https://user-images.githubusercontent.com/18004938/39627434-88a71db0-4fc3-11e8-95a0-8c4a243bdeee.png)
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
        app:ring_color="#00ff0f"
        android:layout_height="wrap_content">
    </com.hs.progressbutton.ProgressImageView>
```

Here is the list of properties you must use to configure this view:
 1. app:radius (in dp) ==> sets the size of the view.
 2. app:thickness (in dp)  ==> sets the thickness of the progress ring.
 3. app:start_image (drawable)  ==> image to be shown when in progress
 4. app:end_image (drawable)  ==> image to be shown on completion of progress.
 5. app:ring_color (color)  ==> color of the circular progressbar.
