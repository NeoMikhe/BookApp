package com.example.bookapp.books.data

import com.example.bookapp.models.AllComentario


object ComentDataProvider {
    val comentario = AllComentario(1,
        "No es cierto es malo",
        "2022/02/21",
        1,
        "comentario",
        1
    )

    val listaComentarios = mutableListOf(
        comentario,
        AllComentario(1,
            "No es cierto es malo",
            "2022/02/21",
            1,
            "comentario",
            1
        ),
        AllComentario(1,
            "No es cierto es malo",
            "2022/02/21",
            1,
            "comentario",
            1
        ))

}