package com.personnal.pierreschutz.firebaseread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private val databaseHelper = FirebaseDatabaseHelper()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val typeName = findViewById<EditText>(R.id.main_typeName_value)
        val duration = findViewById<EditText>(R.id.main_duration_value)
        val shopId = findViewById<EditText>(R.id.main_shopId_value)
        val date = findViewById<EditText>(R.id.main_dateString_value)
        val startTime = findViewById<EditText>(R.id.main_startTime_value)
        val userId = findViewById<EditText>(R.id.main_userId_value)

        val addButton = findViewById<Button>(R.id.main_bounds_add)

        val appointmentList = findViewById<RecyclerView>(R.id.main_appointments)


        addButton.setOnClickListener {
            //TODO : Add your conditions for validity of inputs
            val typeNameString = typeName.text.toString()
            val durationLong = duration.text.toString().toLong()
            val shopIdLong = shopId.text.toString().toLong()
            val dateString = date.text.toString()
            val startTimeLong = startTime.text.toString().toLong()
            val userIdString = userId.text.toString()

            databaseHelper.writeAppointment(durationLong, typeNameString, shopIdLong, dateString, startTimeLong, userIdString)
        }


        databaseHelper.readAppointments(object : FirebaseDatabaseHelper.DataStatus {
            override fun DataIsLoaded(appointments: List<Appointment>) {
               RecyclerViewController(baseContext, appointmentList, appointments)
            }

            override fun DataIsInserted() {}

        })






    }


    class AppointmentViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }
}
