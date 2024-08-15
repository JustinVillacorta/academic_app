package com.example.academic

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Recyclerview : AppCompatActivity() {

    private lateinit var  RecyclerView: RecyclerView
    private lateinit var dataList: ArrayList<RecyclerviewDataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var Teacherbtn:Button
    lateinit var Moderatorbtn:Button
    lateinit var Approvedbtn:Button
    lateinit var Declinebtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        Teacherbtn = findViewById(R.id.teacherButton)
        Moderatorbtn = findViewById(R.id.moderatorButton)
        Approvedbtn = findViewById(R.id.approvedButton)
        Declinebtn = findViewById(R.id.declineButton)

        imageList = arrayOf(
            R.drawable.baseline_school_24
        )

        titleList = arrayOf(
            "testing"
        )

        RecyclerView = findViewById(R.id.recyclerview)
        RecyclerView.layoutManager = LinearLayoutManager(this)
        RecyclerView.setHasFixedSize(true)

        dataList = arrayListOf<RecyclerviewDataClass>()
        getData()

        Teacherbtn.setOnClickListener(){
            // add code here
        }

        Moderatorbtn.setOnClickListener(){
            // add code here

        }

        Approvedbtn.setOnClickListener(){
            // add code here
        }


        Declinebtn.setOnClickListener(){
            // add code here
        }

    }

    private fun getData(){
        for (i in imageList.indices){
            val dataClass = RecyclerviewDataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }

        RecyclerView.adapter = RecyclerviewAdapterClass(dataList)
    }
}