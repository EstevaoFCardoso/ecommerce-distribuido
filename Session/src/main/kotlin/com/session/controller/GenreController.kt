package com.session.controller

import com.session.dto.GenreDTO
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
    fun getAllGenres(): ResponseEntity<List<GenreDTO>> {
        return ResponseEntity.ok(genreService.getAllGenres())
    }

    @GetMapping("/{id}")
    fun getGenreById(@PathVariable id: Long): ResponseEntity<GenreDTO> {
        val genre = genreService.getGenreById(id)
        return if (genre != null) {
            ResponseEntity.ok(genre)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createGenre(@RequestBody genreDTO: GenreDTO): ResponseEntity<GenreDTO> {
        val newGenre = genreService.createGenre(genreDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(newGenre)
    }

    @PutMapping("/{id}")
    fun updateGenre(@PathVariable id: Long, @RequestBody genreDTO: GenreDTO): ResponseEntity<GenreDTO> {
        return genreService.updateGenre(id, genreDTO).let { updatedGenre ->
            ResponseEntity.ok(updatedGenre)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteGenre(@PathVariable id: Long): ResponseEntity<Void> {
        genreService.deleteGenre(id)
        return if (genreService.deleteGenre(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}