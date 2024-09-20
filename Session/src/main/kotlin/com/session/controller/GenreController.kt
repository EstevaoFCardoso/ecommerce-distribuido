package com.session.controller

import com.session.entity.GenreEntity
import com.session.service.GenreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/genres")
class GenreController(
    private val genreService: GenreService
) {

    @GetMapping
    fun getAllGenres(): ResponseEntity<List<GenreEntity>> {
        return ResponseEntity.ok(genreService.getAllGenres())
    }

    @GetMapping("/{id}")
    fun getGenreById(@PathVariable id: Long): ResponseEntity<GenreEntity> {
        val genre = genreService.getGenreById(id)
        return if (genre != null) {
            ResponseEntity.ok(genre)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createGenre(@RequestBody genreEntity: GenreEntity): ResponseEntity<GenreEntity> {
        val newGenre = genreService.createGenre(genreEntity)
        return ResponseEntity(newGenre, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateGenre(@PathVariable id: Long, @RequestBody genreEntity: GenreEntity): ResponseEntity<GenreEntity> {
        val updatedGenre = genreService.updateGenre(id, genreEntity)
        return if (updatedGenre != null) {
            ResponseEntity.ok(updatedGenre)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteGenre(@PathVariable id: Long): ResponseEntity<Void> {
        genreService.deleteGenre(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}