package org.wit.placemark.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var title =""
var description = ""

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listPlacemarks()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println("MAIN MENU")
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

fun addPlacemark(){

    println("Add Placemark")
    println()
    print("Enter a Title: ")
    title = readLine()!!
    print("Enter a Description: ")
    description = readLine()!!
    println("You entered [$title] for title and a [$description] for description")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    print("Enter a new Title for [ $title ] : ")
    print("Enter a Title: ")
    title = readLine()!!
    print("Enter a new Description for [ $description ] : ")
    description = readLine()!!
    println("You entered [$title] for title and a [$description] for description")
}

fun listPlacemarks() {
    println("You Chose List All Placemarks")
}