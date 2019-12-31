package com.example.happydb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.sql.Date

@Entity(tableName = "feeling")
data class Feeling (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
            val mode:Int,// 1= sad,2=neutral,3=happy
                    val remarks:String,
                            val created_at:Long=System.currentTimeMillis())
