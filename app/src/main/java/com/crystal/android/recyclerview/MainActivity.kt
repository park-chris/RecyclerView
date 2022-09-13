package com.crystal.android.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.crystal.android.recyclerview.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // RecyclerView에 사용할 리스트 제공
        for (i in 1 until 11) {
            val mUser = User(i, "user$i")
            userList.add(mUser)
        }

    }

    override fun onResume() {
        super.onResume()

        // RecyclerView 초기화
        adapter = UserAdapter(this, userList)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

    }

}