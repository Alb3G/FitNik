package com.example.fitnik.utils

import kotlin.text.Regex

fun nameIsValid(name: String): Boolean {
    val regex = Regex("^[a-zA-ZÀ-ÿ\\s]{1,150}\$")
    return name.matches(regex)
}

fun emailIsValid(email: String): Boolean {
    val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return regex.matches(email)
}

fun passwordIsValid(password: String): Boolean {
    val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+=\\-.,?]).{8,}$")
    return regex.matches(password)
}
