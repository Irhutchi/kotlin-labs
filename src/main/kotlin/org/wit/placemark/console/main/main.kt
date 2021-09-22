package org.wit.placemark.console.main

import ` org`.wit.placemark.console.models.PlacemarkMemStore
import ` org`.wit.placemark.console.models.PlacemarkModel
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

//manage multiple 'Placemark' objects
val placemarks = PlacemarkMemStore()

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
            4 -> searchPlacemark()
            -99 -> dummyData()
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
    println(" 4. Search Placemarks")
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
    var aPlacemark = PlacemarkModel()
    println("Add Placemark")
    println()
    print("Enter a Title: ")
    aPlacemark.title = readLine()!!
    print("Enter a Description: ")
    aPlacemark.description = readLine()!!

    if (aPlacemark.title.isNotEmpty() && aPlacemark.description.isNotEmpty()) {
        placemarks.create(aPlacemark.copy())
        logger.info("Placemark Added : [ $aPlacemark ]")
    }
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listPlacemarks()
    var searchId = getId()
    val aPlacemark = search(searchId)
    var tempTitle : String?
    var tempDescription : String?

    if(aPlacemark != null) {
        print("Enter a new Title for [ " + aPlacemark.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Description for [ " + aPlacemark.description + " ] : ")
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            aPlacemark.title = tempTitle
            aPlacemark.description = tempDescription
            println(
                "You updated [ " + aPlacemark.title + " ] for title " +
                        "and [ " + aPlacemark.description + " ] for description")
            logger.info("Placemark Updated : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Placemark Not Updated...")
}

fun listPlacemarks() {
    println("List All Placemarks")
    println()
    placemarks.logAll()
    logger.info("[ $placemarks# ]")
    println()
}

fun searchPlacemark() {
    var searchId = getId()
    // create Placemark object here and assign,
    val aPlacemark = search(searchId)
    // based on 'searchId' value passed to 'search()'
    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted Id

    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.findOne(id)
    return foundPlacemark
}


fun dummyData() {
    placemarks.create(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.create(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.create(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}