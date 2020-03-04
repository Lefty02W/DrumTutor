package com.example.drumTutor

class Friend(
    private val name: String,
    val slackId: String,
    val home: String,
    val email: String,
    val phone: String
) {
    override fun toString(): String {
        return name
    }

}