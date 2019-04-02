package com.hogp.testingeverything.navcomponents.withviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Tab1ContainerFragment()
            else -> Tab2ContainerFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}