package com.gokhanaliccii.trendygifs.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.ui.list.GifListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_page, GifListFragment())
            .commit()
    }
}
