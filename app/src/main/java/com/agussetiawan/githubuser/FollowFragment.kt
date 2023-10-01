package com.agussetiawan.githubuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agussetiawan.githubuser.Adapter.UserAdapter
import com.agussetiawan.githubuser.Model.Users.Users
import com.agussetiawan.githubuser.Model.Views.DetailView
import com.agussetiawan.githubuser.Model.Views.FollowView
import com.agussetiawan.githubuser.Model.Views.UsersView
import com.agussetiawan.githubuser.databinding.FragmentFollowBinding

class FollowFragment : Fragment(), UserAdapter.UserCallback {
    private lateinit var binding: FragmentFollowBinding
    private lateinit var followView: FollowView
    private val adapter: UserAdapter = UserAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follow, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBarFollow.visibility = View.VISIBLE
        val username = arguments?.getString("username")
        val position = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        followView = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowView::class.java)

        if (position == 1){
            if (username != null) {
                followView.setFollowers(username)
            }

            followView.followLive.observe(viewLifecycleOwner){
                if(it != null){
                    adapter.setData(it)
                    binding.rvFollow.adapter = adapter
                }

                binding.progressBarFollow.visibility = View.GONE
            }
        }else{
            if (username != null) {
                followView.setFollowing(username)
            }

            followView.followLive.observe(viewLifecycleOwner){
                if(it != null){
                    adapter.setData(it)
                    binding.rvFollow.adapter = adapter
                }

                binding.progressBarFollow.visibility = View.GONE
            }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)
    }
    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }

    override fun onUserClick(user: Users) {
        val userDetailIntent = Intent(requireActivity(), DetailActivity::class.java)
        userDetailIntent.putExtra("username", user.login)
        startActivity(userDetailIntent)
    }
}