package com.karan.unauthorizedaccess

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app1UriString = "content://com.karan.exposedcontent.provider/t1"
        val app2UriString =  "content://com.karan.protectedcontent.provider/t1"
        val cols = arrayOf("_ID","MESSAGE")
        var u = Uri.parse(app1UriString)
        var c = contentResolver.query(u,cols,null,null,null)
        if(c.moveToFirst())
            app1_result.text = "Content accessed:"+c.getString(c.getColumnIndex("MESSAGE"))
        else
            app2_result.text = "Access Denied:Security Exception"
        u = Uri.parse(app2UriString)
        try {
            c = contentResolver.query(u,cols,null,null,null)
            if(c.moveToFirst())
                app2_result.text = "Content accessed " + (c.getColumnIndex("MESSAGE"))
            else
                app2_result.text = "Cannot access content."
        }catch (e:SecurityException) {
            app2_result.text = "Access Denied:Security Exception"
        }
    }
}
