package com.example.happydb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private  lateinit var feelingViewModel: FeelingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FeelingAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Initialise ViewModel
        feelingViewModel= ViewModelProvider(this).get(FeelingViewModel::class.java)

        //Initialise data
        feelingViewModel.allFeelings.observe(this, Observer { feelingList: List<Feeling> ->
            feelingList.let {
                if (it.isNotEmpty()) {
                    adapter.setFeeling(it)
                    Toast.makeText(applicationContext, "Num:" + it.size, Toast.LENGTH_SHORT).show()

                }
            }
        }
        )

        fab.setOnClickListener {
           val intent=Intent(this,AddActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            if(requestCode== REQUEST_CODE){
                val _mode=data?.getIntExtra(AddActivity.Extra_Mode,0)
                val _remark=data?.getStringExtra(AddActivity.Extra_REMARK)
                val feeling=Feeling(id=0,mode = _mode!!,remarks = _remark!!)
            feelingViewModel.insertFeeling(feeling)
            }

    super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object{
        const val REQUEST_CODE=1
    }
}
