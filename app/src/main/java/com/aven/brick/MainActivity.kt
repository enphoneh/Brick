package com.aven.brick

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aven.brick.core.ActivityStarter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun secondActivity(v: View) {
        var intent = Intent(this, SecondActivity::class.java)
        ActivityStarter.startActivity(this, intent)
    }
}
