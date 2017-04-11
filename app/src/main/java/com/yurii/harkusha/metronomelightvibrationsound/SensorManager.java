package com.yurii.harkusha.metronomelightvibrationsound;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;

/**
 * Created by Yurii Harkusa on 09.04.2017.
 */

class SensorManager {
    private LightManager lightManager;
    private boolean lightStatus;
    private boolean vibroStatus;
    private boolean soundStatus;
    private Context context;
    private int frequency;

    SensorManager(Context _context) throws CameraAccessException {
        context = _context;
        lightManager = new LightManager(context);
    }


    public void turnOnLight() throws Exception {
        lightManager.turnOnFlash();
        this.setLightStatus(lightManager.isFlashOn());
    }


    public boolean isLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(boolean lightStatus) {
        this.lightStatus = lightStatus;
    }

    public boolean isVibroStatus() {
        return vibroStatus;
    }

    public void setVibroStatus(boolean vibroStatus) {
        this.vibroStatus = vibroStatus;
    }

    public boolean isSoundStatus() {
        return soundStatus;
    }

    public void setSoundStatus(boolean soundStatus) {
        this.soundStatus = soundStatus;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
