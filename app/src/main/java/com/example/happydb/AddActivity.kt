package com.example.happydb


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private  var mode:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        buttonsave.setOnClickListener{
            if(mode==0){
                Toast.makeText(applicationContext,"Please select a mode",Toast.LENGTH_SHORT).show()

            }
            val remark=editTextremark.text.toString()
            val intent=Intent()
            intent.putExtra(Extra_Mode,mode)
            intent.putExtra(Extra_REMARK, remark)

            setResult(Activity.RESULT_OK,intent)
            finish()

        }
        imageViewhappy.setOnClickListener{
            mode=3
            it.setBackgroundColor(Color.GREEN)
        }
        imageViewneutral.setOnClickListener{
            mode=2
            it.setBackgroundColor(Color.YELLOW)
        }
        imageViewsad.setOnClickListener{
            mode=1
            it.setBackgroundColor(Color.RED)

        }


    }
    companion object{
        const val Extra_Mode="com.example.happydb.MODE"
        const val Extra_REMARK="com.example.happydb.REMARK"

    }
}
