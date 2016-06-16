package com.topspeedsnail.mycamera2;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    Camera cam = null;

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(this);

        jpegCallback = new PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));

                    outStream.write(data);
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }

                Toast.makeText(getApplicationContext(), "已保存", Toast.LENGTH_LONG).show();
                refreshCamera();
            }
        };
    }


    public void captureImage(View v) throws IOException {
        cam.takePicture(null, null, jpegCallback);
    }

    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            cam.stopPreview();
        } catch (Exception e) {
        }

        try {
            cam.setPreviewDisplay(surfaceHolder);
            cam.startPreview();
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            cam = Camera.open();
        } catch (RuntimeException e) {
            System.err.println(e);
            return;
        }

        Camera.Parameters param;
        param = cam.getParameters();
        param.setPreviewSize(352, 288);
        cam.setParameters(param);

        try {
            cam.setPreviewDisplay(surfaceHolder);
            cam.startPreview();
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cam.stopPreview();
        cam.release();
        cam = null;
    }
}
