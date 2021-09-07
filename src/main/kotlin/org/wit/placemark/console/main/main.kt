package org.wit.placemark.console.main

import mu.KotlinLogging

// Place definition above class declaration to make field static
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    //display repeating menu, exit only when user enters '-1'
    do {
        input = menu()
        when(input) {
            1 -> println("You can choose Add Placemark")
            2 -> println("You can choose Update Placemark")
            3 -> println("You can choose List All Placemarks")
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}