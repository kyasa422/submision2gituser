package com.agussetiawan.githubuser.Adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.agussetiawan.githubuser.FollowFragment

class FollowAdapter(
activity: AppCompatActivity
): FragmentStateAdapter(activity) {
    var username = ""

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply{
            putInt(FollowFragment.ARG_SECTION_NUMBER, position + 1)
            putString("username", username)
        }

        return fragment
    }
}