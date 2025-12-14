package com.example.firstproject

fun main (){
    print("Please enter a number : ")
    var number: Any = readln()!!.toInt()
    if(number.toString().toInt() %2 == 0){
        println("$number is even")
    }else{
        println("$number is odd")
    }
}