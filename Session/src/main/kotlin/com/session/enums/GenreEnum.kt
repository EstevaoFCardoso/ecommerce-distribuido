package com.session.enums

enum class GenreEnum(
    val displayName: String
) {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western"),
    UNKNOWN("Unknown");

    companion object {
        fun fromName(displayName: String): GenreEnum? {
            return entries.find { it.displayName.equals(displayName, ignoreCase = true) }
        }
    }
}