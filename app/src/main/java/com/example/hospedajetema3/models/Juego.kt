package com.example.hospedajetema3.models

class Juego (
    var name: String,
    var plataforma: String,
    var anno: String,
    var nota: String,
    var image: String
) {
    override fun toString(): String {
        return "Hotel(name='$name', city='$plataforma', province='$anno', phone='$nota', image='$image')"
    }
}