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
    private boolean isLightTurnOn;
    private Context context;
    private PackageManager packageManager;
    private final boolean deviceHasFlash;
    //New camera variables
    private CameraManager cameraManager;
    private String cameraId;
    //Old camera variables
    private Camera oldCamera;
    private Parameters parametersForOldCamera;


    LightManager(Context _context) throws CameraAccessException {
        context = _context;
        packageManager = context.getPackageManager();
        deviceHasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            cameraId = cameraManager.getCameraIdList()[0];
        } else {
            oldCamera = null;
        }
    }


    public void turnOnFlash() throws Exception {
        if (!isLightTurnOn && isDeviceHasFlash()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
                isLightTurnOn = true;
            } else {
                if (oldCamera == null) {
                    oldCamera = Camera.open();
                    parametersForOldCamera = oldCamera.getParameters();
                    parametersForOldCamera.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    oldCamera.setParameters(parametersForOldCamera);
                    oldCamera.startPreview();
                    isLightTurnOn = true;
                }
            }
        }
    }

    public void turnOffFlash() throws Exception {
        if (isLightTurnOn && isDeviceHasFlash()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
                isLightTurnOn = false;
            } else {
                if (oldCamera != null) {
                    parametersForOldCamera.setFlashMode(Parameters.FLASH_MODE_OFF);
                    oldCamera.setParameters(parametersForOldCamera);
                    oldCamera.stopPreview();
                    oldCamera.release();
                    oldCamera = null;
                    isLightTurnOn = false;
                }
            }
        }
    }

    public boolean isLightTurnOn() {
        return isLightTurnOn;
    }

    public boolean isDeviceHasFlash() {
        return deviceHasFlash;
    }
}
