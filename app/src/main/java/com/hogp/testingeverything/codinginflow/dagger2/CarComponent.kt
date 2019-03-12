package com.hogp.testingeverything.codinginflow.dagger2

import com.hogp.testingeverything.MainActivity
import dagger.Component

@Component
interface CarComponent {

    fun getCar(): Car

    fun inject(mainActivity: MainActivity)
}