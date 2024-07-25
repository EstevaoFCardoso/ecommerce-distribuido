package com.movie_catalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieCatalogApplication

fun main(args: Array<String>) {
	runApplication<MovieCatalogApplication>(*args)
}
