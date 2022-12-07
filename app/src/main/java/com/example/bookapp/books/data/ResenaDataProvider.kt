package com.example.bookapp.books.data

import com.example.bookapp.models.AllResena

object ResenaDataProvider {
    val resena = AllResena(1, "Este libro es bueno", "2020-02-23", "", 1, "resena", "Es bueno", 1, 1)

    val resenaList = mutableListOf(
        resena,
        AllResena(
            2, "Este libro es bueno", "2020-02-23", "", 2, "resena", "Es bueno", 1, 1
        ),
                AllResena(
                3, "Este libro es bueno", "2020-02-23", "", 3, "resena", "Es bueno", 1, 1
    )
    )

}