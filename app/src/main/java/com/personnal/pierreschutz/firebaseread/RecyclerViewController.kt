package com.personnal.pierreschutz.firebaseread

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView

class RecyclerViewController(val mContext : Context, private val recyclerView: RecyclerView, private val appointmentList: List<Appointment>) {

    init {
        recyclerView.adapter = AppointmentListAdapter(appointmentList)
        recyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    inner class AppointmentItemView(val parent : ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.appointment_item, parent, false)) {

        private val title : TextView = itemView.findViewById(R.id.appointment_item_title)
        private val content : TextView = itemView.findViewById(R.id.appointment_item_content)

        fun bind(appointment : Appointment, position : Int) {
            title.text =  "Appointment $position"
            content.text = appointment.toString()
        }

    }

    inner class AppointmentListAdapter(private val appointments : List<Appointment>) : RecyclerView.Adapter<AppointmentItemView>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentItemView {
            return AppointmentItemView(parent)
        }

        override fun getItemCount(): Int {
            return appointments.size
        }

        override fun onBindViewHolder(holder: AppointmentItemView, position: Int) {
           holder.bind(appointments[position], position)
        }

    }


}