package com.personnal.pierreschutz.firebaseread

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseDatabaseHelper {

    private val database = FirebaseDatabase.getInstance()
    private val appointmentRef = database.getReference("appointments")

    private val appointmentList = mutableListOf<Appointment>()



    fun readAppointments(status : DataStatus) {
        appointmentRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w("DATABASE_ERROR", "Error while reading appointments", error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                appointmentList.clear()
                for (data in dataSnapshot.children) {
                    appointmentList.add(data.getValue(Appointment::class.java)!!)
                }

                status.DataIsLoaded(appointmentList)
            }

        })
    }

    fun writeAppointment(duration : Long, type : String, shopId : Long, date : String, startTime : Long, userId : String) {
        //Create a new instance in Database/appointments
        val key = appointmentRef.push().key
        //Add my appointment object
        appointmentRef.child(key).setValue(Appointment(AppointmentType(duration, type), shopId, date, startTime, userId))

    }






    interface DataStatus {

        fun DataIsLoaded(appointments : List<Appointment>)
        fun DataIsInserted()
    }
}