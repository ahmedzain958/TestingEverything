package com.hogp.testingeverything;

import android.os.Bundle;

import com.hogp.testingeverything.codinginflow.dagger2.Car;
import com.hogp.testingeverything.codinginflow.dagger2.CarComponent;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
