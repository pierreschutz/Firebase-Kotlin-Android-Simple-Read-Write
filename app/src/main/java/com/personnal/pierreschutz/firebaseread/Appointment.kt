package com.personnal.pierreschutz.firebaseread



class Appointment {

    var appointmentType : AppointmentType? = null
    var shopId : Long? = null
    var dateString : String? = null
    var startTime : Long? = null
    var userId : String? = null

    constructor()

    constructor(appointmentType : AppointmentType, shopId : Long, dateString : String, startTime : Long, userId : String) : this() {
        this.appointmentType = appointmentType
        this.shopId = shopId
        this.dateString = dateString
        this.startTime = startTime
        this.userId = userId
    }

    fun toMap() : Map<String, Any> {
        val result = mutableMapOf<String, Any>()
        result["appointmentType"] = appointmentType!!
        result["shopId"] = shopId!!
        result["dateString"] = dateString!!
        result["startTime"] = startTime!!
        result["userId"] = userId!!
        return result.toMap()
    }

    override fun toString(): String {
        return appointmentType.toString() + " Shop: " + shopId.toString() + " Date: "  + dateString.toString() + " Time: "+ startTime.toString() + " User: " + userId.toString()
    }
}


class AppointmentType() {
    var duration : Long? = null
    var typeName : String? = null

    constructor(duration: Long, typeName : String) : this() {
        this.duration = duration
        this.typeName = typeName
    }

    fun toMap() : Map<String, Any> {
        val result = mutableMapOf<String, Any>()
        result["duration"] = duration!!
        result["typeName"] = typeName!!
        return result.toMap()
    }

    override fun toString(): String {
        return "Duration: $duration Type: $typeName"
    }
}
