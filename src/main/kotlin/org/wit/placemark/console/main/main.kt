package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.controllers.PlacemarkController
import org.wit.placemark.console.models.PlacemarkJSONStore

private val logger = KotlinLogging.logger {}

//manage multiple 'Placemark' objects
val placemarks = PlacemarkJSONStore()


fun main(args: Array<String>) {
    PlacemarkController().start()
}