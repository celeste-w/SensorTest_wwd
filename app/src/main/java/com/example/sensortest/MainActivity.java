package com.example.sensortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private  SensorManager mSensorManager;
    EditText etOrientation,etGyro,etTemperature,etLight,etPressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etOrientation=(EditText) findViewById(R.id.t1);
        etGyro=(EditText) findViewById(R.id.t2);
        etTemperature=(EditText) findViewById(R.id.t3);
        etLight=(EditText)findViewById(R.id.t4);
        etPressure=(EditText)findViewById(R.id.t5);
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
    }
    @Override
    protected  void onResume(){
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的磁场传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的温度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的压力传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的光感传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);

    }
    @Override
    protected void onStop()	{
        // 取消注册
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    @Override
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }
    // 以下是实现SensorEventListener接口必须实现的方法
    // 当传感器的值发生改变时回调该方法

    @Override
    public void onSensorChanged(SensorEvent event)	{
        float[] values = event.values;
        int sensorType = event.sensor.getType();
        StringBuilder sb = null;
        switch (sensorType) {
            case Sensor.TYPE_ORIENTATION:
                sb = new StringBuilder();
                sb.append("绕Z轴转过的角度：");
                sb.append(values[0]);
                sb.append("\n绕X轴转过的角度：");
                sb.append(values[1]);
                sb.append("\n绕Y轴转过的角度：");
                sb.append(values[2]);
                etOrientation.setText(sb.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("X轴方向上的磁场强度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的磁场强度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的磁场强度：");
                sb.append(values[2]);
                etGyro.setText(sb.toString());
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("当前温度为：");
                sb.append(values[0]);
                etTemperature.setText(sb.toString());
                break;
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("当前光的强度为：");
                sb.append(values[0]);
                etLight.setText(sb.toString());
                break;
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("当前压力为：");
                sb.append(values[0]);
                etPressure.setText(sb.toString());
                break;
        }
    }
    // 当传感器精度改变时回调该方法。
    public void onAccuracyChanged(Sensor sensor, int accuracy)	{
    }
}


