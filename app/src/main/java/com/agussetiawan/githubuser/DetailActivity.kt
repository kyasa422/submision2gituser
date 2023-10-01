package com.agussetiawan.githubuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.agussetiawan.githubuser.Adapter.FollowAdapter
import com.agussetiawan.githubuser.Model.Users.DetailResponse
import com.agussetiawan.githubuser.Model.Views.DetailView
import com.agussetiawan.githubuser.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailView
    private lateinit var adapter: FollowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@DetailActivity, R.layout.activity_detail)
        supportActionBar?.hide()

        val username = intent.getStringExtra("username")
        binding.progressBar.visibility = View.VISIBLE
        detailViewModel = ViewModelProvider(this@DetailActivity, ViewModelProvider.NewInstanceFactory())[DetailView::class.java]

        if (username != null) {
            detailViewModel.setUserDetail(username)
        }

        detailViewModel.userDetailLive.observe(this@DetailActivity){
            setUser(it)
            binding.progressBar.visibility = View.GONE
        }

        val followPagerAdapter = FollowAdapter(this)
        adapter = followPagerAdapter
        if (username != null) {
            adapter.username = username
        }

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = followPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }
            .attach()
    }

    @SuppressLint("SetTextI18n")
    private fun setUser(user: DetailResponse){
        with(binding){
            tvName.text = user.name
            tvUsername.text = user.login
            tvFollowers.text = "${user.followers} followers"
            tvFollowing.text = "${user.following} following"
            Glide.with(this@DetailActivity)
                .load(user.avatar_url)
                .into(profileImage)
        }
    }

    companion object{
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
    }
}