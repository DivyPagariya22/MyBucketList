package com.example.android.mybucketlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mybucketlist.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding :ActivityMainBinding


class MainActivity : AppCompatActivity() , BucketRV.ClickListner {

    private lateinit var adapter: BucketRV
    lateinit var viewModel: BucketViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
         adapter = BucketRV(this,this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(BucketViewModel::class.java)
        viewModel.allGoals.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(bucket: Bucket) {
        viewModel.deleteGoal(bucket)
        Toast.makeText(this,"${bucket.goal} Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun onDoneClicked(view: View,pos:Int,bucket: Bucket,holder: BucketRV.BucketViewHolder) {
        view.setBackgroundResource(R.drawable.done_corners)
        holder.done.isVisible= true
        val goal = bucket.goal
        val i = bucket.id
        val newBucket = Bucket(i,goal,1)
        viewModel.update(newBucket)
    }


    fun Submit(view: View) {
        val Goal = binding.input.text.toString()
        if(Goal.isNotEmpty()){
            viewModel.insertGoal(Bucket(0,Goal,0))
            Toast.makeText(this, "${Goal} Inserted", Toast.LENGTH_SHORT).show()
        }
        binding.input.setText("")
    }
}


