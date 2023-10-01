package com.agussetiawan.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agussetiawan.githubuser.Adapter.UserAdapter
import com.agussetiawan.githubuser.Model.Users.Users
import com.agussetiawan.githubuser.Model.Views.UsersView
import com.agussetiawan.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserAdapter.UserCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersView: UsersView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        supportActionBar?.hide()

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            usersView = ViewModelProvider(this@MainActivity).get(UsersView::class.java)
            usersView.searchUsers("kyasa")

            searchView
                .editText
                .setOnEditorActionListener{view, action, e ->
                    searchBar.text = searchView.text
                    searchView.hide()

                    if(searchBar.text?.isEmpty() == false){
                        usersView.searchUsers(searchBar.text.toString())
                    }else{
                        usersView.searchUsers("")
                    }

                    false
                }
        }

        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this@MainActivity, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)
        userAdapter = UserAdapter(this@MainActivity)
        binding.rvUser.adapter = userAdapter

        usersView.users.observe(this@MainActivity){user ->
            userAdapter.setData(user)
        }

        usersView.isLoading.observe(this@MainActivity){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
                Log.d("Is Loading", "Lagi Loading")
            }else{
                binding.progressBar.visibility = View.GONE
                Log.d("Is Loading", "Dah selesai")
            }
        }
    }

    override fun onUserClick(user: Users) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("username", user.login.toString())
        startActivity(intent)
    }
}