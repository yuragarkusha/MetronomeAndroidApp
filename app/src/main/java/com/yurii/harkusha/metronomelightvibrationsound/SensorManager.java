package com.yurii.harkusha.metronomelightvibrationsound;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;

/**
 * Created by Yurii Harkusa on 09.04.2017.
 */

class SensorManager {
    private LightManager lightManager;
    private VibrationManager vibrationManager;
    private boolean isLightTurnOn;
    private boolean isSoundTurnOn;
    private Context context;
    private int frequency;
    private int durationOfVibration;

    SensorManager(Context _context) throws CameraAccessException {
        context = _context;
        lightManager = new LightManager(context);
        durationOfVibration = 100;
        vibrationManager = new VibrationManager(context, durationOfVibration);
    }


    public void turnOnLight() throws Exception {
        lightManager.turnOnFlash();
        this.setLightTurnOn(lightManager.isLightTurnOn());
    }

    public void turnOffLight() throws Exception {
        lightManager.turnOffFlash();
        this.setLightTurnOn(lightManager.isLightTurnOn());
    }

    public void turnOnOffLight() throws Exception {
        if (!isLightTurnOn()) {
            turnOnLight();
        } else {
            turnOffLight();
        }
    }

    public void turnOnVibrate() throws CameraAccessException {
        vibrationManager.startVibrate();
    }

    public void changeDurationOfVibration(int newDuration) {
        vibrationManager.setDurationOfVibration(newDuration);
    }

    public boolean isLightTurnOn() {
        return isLightTurnOn;
    }

    public void setLightTurnOn(boolean lightTurnOn) {
        this.isLightTurnOn = lightTurnOn;
    }

    public boolean isSoundTurnOn() {
        return isSoundTurnOn;
    }

    public void setSoundTurnOn(boolean soundTurnOn) {
        this.isSoundTurnOn = soundTurnOn;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
