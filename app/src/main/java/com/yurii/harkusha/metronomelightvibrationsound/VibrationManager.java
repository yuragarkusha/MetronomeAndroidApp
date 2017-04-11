package com.yurii.harkusha.metronomelightvibrationsound;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.os.Vibrator;

/**
 * Created by Yurii Harkusa on 11.04.2017.
 */

class VibrationManager {
    private Context context;
    private Vibrator vibrator;
    private int durationOfVibration;

    VibrationManager(Context _context, int _durationOfVibration) throws CameraAccessException {
        context = _context;
        durationOfVibration = _durationOfVibration;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void startVibrate() {
        vibrator.vibrate(durationOfVibration);
    }

    public void stopVibrate() {
        vibrator.cancel();
    }

    public int getDurationOfVibration() {
        return durationOfVibration;
    }

    public void setDurationOfVibration(int durationOfVibration) {
        this.durationOfVibration = durationOfVibration;
    }
}
