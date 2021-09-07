package org.wit.placemark

import mu.KotlinLogging

// Place definition above class declaration to make field static
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    println("Placemark Kotlin App Version 1.0")
    logger.info { "Launching Placemark Console App" }
}
