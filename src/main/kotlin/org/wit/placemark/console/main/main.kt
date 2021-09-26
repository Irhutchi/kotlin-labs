package org.wit.placemark.console.main

import org.wit.placemark.console.models.PlacemarkMemStore
import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.views.PlacemarkView
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

//manage multiple 'Placemark' objects
val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 3.0")

    var input: Int

    do {
        input = placemarkView.menu()
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

fun addPlacemark(){
    var aPlacemark = PlacemarkModel()

    if (placemarkView.addPlacemarkData(aPlacemark))
        placemarks.create(aPlacemark)
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {

    placemarkView.listPlacemarks(placemarks)
    var searchId = placemarkView.getId()
    val aPlacemark = search(searchId)

    if (aPlacemark != null) {
        if (placemarkView.updatePlacemarkData(aPlacemark)) {
            placemarks.update(aPlacemark)
            placemarkView.showPlacemark(aPlacemark)
            logger.info { "Placemark Updated : [ $aPlacemark ]" }
        } else
            logger.info("Placemark not updated")
    } else
        logger.info("Placemark not updated")
}

fun listPlacemarks() {
    println("List All Placemarks")
    println()
    placemarks.logAll()
    logger.info("[ $placemarks# ]")
    println()
}

fun searchPlacemark() {
    val aPlacemark = search(placemarkView.getId())!!
    placemarkView.showPlacemark(aPlacemark)
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