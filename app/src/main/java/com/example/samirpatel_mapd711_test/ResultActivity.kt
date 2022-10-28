package com.example.samirpatel_mapd711_test
/**
 * Result Activity
 * Student Name: Samir Patel
 * Student ID: 301286671
 * MAPD 711 Test 1
 */
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

        findViewById<TextView>(R.id.vehicle_size_value).text = sharedPreferences.getString("vehicle_type","vehicle_type")
        findViewById<TextView>(R.id.distance_value).text = "${sharedPreferences.getFloat("distance_value",0.0f)} Km"
        findViewById<TextView>(R.id.time_of_day_value).text = sharedPreferences.getString("time_of_day_value","time_of_day_value")
        findViewById<TextView>(R.id.direction_value).text = sharedPreferences.getString("direction_value","direction_value")
        findViewById<TextView>(R.id.transponder_value).text = sharedPreferences.getString("transponder_value","transponder_value")
        findViewById<TextView>(R.id.toll_value).text = "$${sharedPreferences.getFloat("toll_value",0.0f)}"

        if (sharedPreferences.getBoolean("online_cal_value",false)) {
            loadWebView()
        }
    }

    private fun loadWebView() {
        val web_view_final = findViewById<View>(R.id.web_view) as WebView
        web_view_final.visibility = View.VISIBLE
        web_view_final.settings.javaScriptEnabled = true
        web_view_final.loadUrl("https://www.407etr.com/en/tolls/tolls/toll-calculator.html")
    }
}