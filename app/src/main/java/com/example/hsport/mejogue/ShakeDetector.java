package com.example.hsport.mejogue;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

public class ShakeDetector implements SensorEventListener {

    private static final float SHAKE_THRESHOL_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    private OnShakeListener mListener;
    private long mShakeTimeStamp;
    private int mShakeCount;

    public void setOnShakeListener (OnShakeListener listener){
        this.mListener = listener;
    }
    public interface OnShakeListener {
        public void onShake(int count);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(mListener != null){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX*gX + gY*gY + gZ*gZ);
            if (gForce > SHAKE_THRESHOL_GRAVITY){
                final long now = System.currentTimeMillis();
                if(mShakeTimeStamp + SHAKE_COUNT_RESET_TIME_MS > now){
                    return;
                }
                if (mShakeTimeStamp + SHAKE_COUNT_RESET_TIME_MS < now){
                    mShakeCount = 0;
                }
                mShakeTimeStamp = now;
                mShakeCount++;
                mListener.onShake(mShakeCount);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
