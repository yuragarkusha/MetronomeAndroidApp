package com.yurii.harkusha.metronomelightvibrationsound;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

/**
 * Created by Yurii Harkusa on 10.04.2017.
 */

class LightManager {
    private boolean isFlashOn;
    private Context context;
    private final boolean deviceHasFlash;
    private CameraManager mCameraManager;
    private String mCameraId;
    private PackageManager packageManager;

    private Camera camera;
    private Parameters parameters;


    LightManager(Context _context) throws CameraAccessException {
        context = _context;
        packageManager = context.getPackageManager();
        deviceHasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            mCameraId = mCameraManager.getCameraIdList()[0];
        } else {
            camera = null;
        }
    }


    public void turnOnFlash() throws Exception {
        if (!isFlashOn && isDeviceHasFlash()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
            } else {
                if (camera == null) {
                    camera = Camera.open();
                    parameters = camera.getParameters();
                    parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    camera.startPreview();
                    isFlashOn = true;
                }
            }
        }
    }

    public void turnOffFlash() throws Exception {
        if (isFlashOn && isDeviceHasFlash()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);
                isFlashOn = false;
            } else {
                if (camera != null) {
                    parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                    isFlashOn = false;
                }
            }
        }
    }

    public boolean isFlashOn() {
        return isFlashOn;
    }

    public boolean isDeviceHasFlash() {
        return deviceHasFlash;
    }
}
