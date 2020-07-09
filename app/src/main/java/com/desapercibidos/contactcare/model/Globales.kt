package com.desapercibidos.contactcare.model

var contactoList: ArrayList<Contacto>? = null

data class Contacto (
    var contactoId: String = "",
    var foto : String = "@drawable/ic_person",
    var nombre: String = "",
    var telefono: String = "",
    var email: String = ""
)



