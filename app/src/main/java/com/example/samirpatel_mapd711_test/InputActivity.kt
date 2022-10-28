package com.example.samirpatel_mapd711_test
/**
 * Input Activity
 * Student Name: Samir Patel
 * Student ID: 301286671
 * MAPD 711 Test 1
 */
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var timeList = arrayOf(
        "Wkdy 12:00 AM - 6:00 AM",
        "Wkdy 6:00 AM - 7:00 AM",
        "Wkdy 7:00 AM - 9:30 AM",
        "Wkdy 9:30 AM - 10:30 AM",
        "Wkdy 10:30 AM - 2:30 PM",
        "Wkdy 2:30 PM - 3:30 PM",
        "Wkdy 3:30 PM - 6:00 PM",
        "Wkdy 6:00 PM - 7:00 PM",
        "Wkdy 7:00 PM - 12:00 AM",
        "Wknd & Holidays 12:00 AM - 11:00 AM",
        "Wknd & Holidays 11:00 AM - 7:00 PM",
        "Wknd & Holidays 7:00 PM - 12:00 AM"
    )

    private var timeOfDayIndex = 0

    private var cameraChargesLight = 4.2 //you gave us this value in mail
    private var cameraChargesHeavy = 50.0 // I took it according to the 407 website
    private var cameraChargesMultiHeavy = 50.0 // I took it according to the 407 website

    private var tripChargesLight = 1
    private var tripChargesHeavy = 2
    private var tripChargesMultiHeavy = 3

    private var lightVehicleEB = arrayOf(
        "25.29", "42.04", "47.83", "42.04", "38.47", "43.62", "49.56", "46.81", "25.29", "25.29", "34.63", "25.29"
    )

    private var lightVehicleWB = arrayOf(
        "25.29", "44.86", "54.93", "46.58", "39.07", "48.61", "58.48", "43.62", "25.29", "25.29", "34.63", "25.29"
    )

    private var heavyVehicleEB = arrayOf(
        "50.58", "84.08", "95.66", "84.08", "76.94", "97.22", "116.96", "93.62", "50.58", "50.58", "69.26", "50.58"
    )

    private var heavyVehicleWB = arrayOf(
        "50.58", "89.72", "109.86", "93.16", "78.14", "87.24", "99.12", "87.24", "50.58", "50.58", "69.26", "50.58"
    )

    private var heavyMultiVehicleEB = arrayOf(
        "75.87", "126.12", "143.49", "126.12", "115.41", "145.83", "175.44", "130.86", "75.87", "75.87", "103.89", "75.87"
    )

    private var heavyMultiVehicleWB = arrayOf(
        "75.87", "134.58", "164.79", "139.74", "117.21", "130.86", "148.68", "140.43", "75.87", "75.87", "103.89", "75.87"
    )

    private var vehicleType = "Light Vehicle"
    private var directionValue = "East Bound"
    private var timeOfDayValue = "Wkdy 12:00 AM - 6:00 AM"
    var distanceValue = 0.0f

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)


        findViewById<Button>(R.id.calculate_btn).setOnClickListener {

            val transponderValue = findViewById<Switch>(R.id.transponder_switch).isChecked
            val onlineCalValue = findViewById<CheckBox>(R.id.online_cal_check).isChecked
            var tollValue = 0.0
            if (vehicleType.contains("light", true)) {
                if (directionValue.contains("east", true)) {
                    cameraChargesLight = if (!transponderValue) {
                        4.2
                    } else {
                        0.0
                    }
                    tollValue = (distanceValue * (lightVehicleEB[timeOfDayIndex].toFloat() / 100.0)) + tripChargesLight + cameraChargesLight //𝑇𝑜𝑙𝑙=𝑑× 𝑡+𝑡𝑐+𝑐
                }
                else {
                    cameraChargesLight = if (!transponderValue) {
                        4.2
                    } else {
                        0.0
                    }
                    tollValue = (distanceValue * (lightVehicleWB[timeOfDayIndex].toFloat() / 100.0)) + tripChargesLight + cameraChargesLight //𝑇𝑜𝑙𝑙=𝑑× 𝑡+𝑡𝑐+𝑐
                }
            }
            else if (vehicleType.contains("heavy vehicle", true)) {
                if (directionValue.contains("east", true)) {
                    cameraChargesHeavy = if (!transponderValue) {
                        50.0
                    } else {
                        0.0
                    }
                    tollValue = (distanceValue * (heavyVehicleEB[timeOfDayIndex].toFloat() / 100.0)) + tripChargesHeavy + cameraChargesHeavy //𝑇𝑜𝑙𝑙=𝑑× 𝑡+𝑡𝑐+𝑐
                }
                else {
                    cameraChargesHeavy = if (!transponderValue) {
                        50.0
                    } else {
                        0.0
                    }
                    tollValue = (distanceValue * (heavyVehicleWB[timeOfDayIndex].toFloat() / 100.0)) + tripChargesHeavy + cameraChargesHeavy//𝑇𝑜𝑙𝑙=𝑑× 𝑡+𝑡𝑐+𝑐
                }
            }
            else if (vehicleType.contains("multi", true)) {
                if (directionValue.contains("east", true)) {
                    cameraChargesMultiHeavy = if (!transponderValue) {
                        50.0
                    } else {
                        0.0
                    }
                    tollValue = (distanceValue * (heavyMultiVehicleEB[timeOfDayIndex].toFloat() / 100.0)) + tripChargesMultiHeavy + cameraChargesMultiHeavy //𝑇𝑜𝑙𝑙=𝑑× 𝑡+𝑡𝑐+𝑐
                }
                else {
                    cameraChargesMultiHeavy = if (!transponderValue) {
                        50.0
                    } else {
                        0.0
                    }
                    tollValue = (distanceValue * (heavyMultiVehicleWB[timeOfDayIndex].toFloat() / 100.0)) + tripChargesMultiHeavy + cameraChargesMultiHeavy //𝑇𝑜𝑙𝑙=𝑑× 𝑡+𝑡𝑐+𝑐
                }
            }

            val sharedPreferences: SharedPreferences = this.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("vehicle_type",vehicleType)
            editor.putFloat("distance_value",distanceValue)
            editor.putString("direction_value",directionValue)
            editor.putString("time_of_day_value",timeOfDayValue)
            if (transponderValue) {
                editor.putString("transponder_value","Yes")
            } else {
                editor.putString("transponder_value","No")
            }
            editor.putBoolean("online_cal_value",onlineCalValue)
            editor.putFloat("toll_value", tollValue.toFloat())
            editor.apply()

            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }

        findViewById<EditText>(R.id.distance_et).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    distanceValue = s.toString().toFloat()
                    if (distanceValue > 24.0) {
                        findViewById<EditText>(R.id.distance_et).setText("")
                        Toast.makeText(this@InputActivity, "You must write the distance between 0 to 24 Km", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        findViewById<RadioButton>(R.id.light_vehicle_rb).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                vehicleType = "Light Vehicle"
            }
        }

        findViewById<RadioButton>(R.id.heavy_vehicle_rb).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                vehicleType = "Heavy Vehicle"
            }
        }

        findViewById<RadioButton>(R.id.heavy_multi_vehicle_rb).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                vehicleType = "Heavy Multi Vehicle"
            }
        }

        findViewById<RadioButton>(R.id.east_bound_rb).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                directionValue = "East Bound"
            }
        }

        findViewById<RadioButton>(R.id.west_bound_rb).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                vehicleType = "Heavy Multi Vehicle"
            }
        }
        initSpinner()

    }

    private fun initSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the card types list
        val timeAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, timeList)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Setting the ArrayAdapter data on the Spinner
        spinner.adapter = timeAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        timeOfDayValue = timeList[position]
        timeOfDayIndex = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}