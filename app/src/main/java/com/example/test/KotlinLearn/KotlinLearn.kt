package com.example.test.KotlinLearn

fun main() {
    println("maxNum: " + max(1, 2))
    a(1)
}

fun max(num1: Int, num2: Int) = if (num1 > num2) {
    num1
} else num2


fun a(num: Int) {
    val numTest = num
    print("numTest: " + numTest)
}