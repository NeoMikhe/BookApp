package com.example.bookapp.books.data

import com.example.bookapp.R

object DataProvider {

    val book =
        Books(
            id = 1,
            title = "The Feynman Lectures on Physics (Richard Feynman)",
            tags = "ciencia, matematicas",
            description = "Es un conjunto de libros sobre física basado en las conferencias de Richard P. Feynman, premio Nobel de física.",
            bookImageId = R.drawable.b1
        )

    val bookList = listOf(
        book,
        Books(
            id = 2,
            title = "Cosmos (Carl Sagan)",
            tags = "ciencia, astronomia",
            description = "Cosmos es uno de los libros más famosos de Carl Sagan, y está basado en su archiconocida serie “Cosmos: un viaje personal”.",
            bookImageId = R.drawable.b2
        ),
        Books(
            id = 3,
            title = "El gen egoísta (Richard Dawkins)",
            tags = "biologia, ciencia",
            description = "El gen egoísta: las bases biológicas de nuestra conducta es el libro de referencia de Richard Dawkins y, aunque ya clásico, sigue siendo un libro muy popular.",
            bookImageId = R.drawable.b3
        ),
        Books(
            id = 4,
            title = "Pensar rápido, pensar despacio (Daniel Kahneman)",
            tags = "ciencia, filosofia",
            description = "Pensar rápido, pensar despacio de Daniel Kahneman, Premio Nobel de Economía, es uno de los libros del siglo XXI más conocidos en el ámbito de las ciencias comportamentales..",
            bookImageId = R.drawable.b4
        ),
        Books(
            id = 5,
            title = "The Immortal Life of Henrietta Lacks (Rebecca Skloot)",
            tags = "ciencia, biologia, historia",
            description = "The Immortal Life of Henrietta Lacks (La inmortal vida de Henrietta Lacks) fue escrito por Rebecca Skloot y trata de la vida de Henrietta Lacks, una mujer afroamericana que fue de vital importancia en la investigación acerca el cáncer.",
            bookImageId = R.drawable.b5
        ),
        Books(
            id = 6,
            title = "El hombre que confundió a su mujer con un sombrero (Oliver Sacks)",
            tags = "ciencia, filosofia",
            description = "El hombre que confundió a su mujer con un sombrero fue publicado en 1985 y escrito por el neurólogo Oliver Sacks, siendo esta obra un referente en el género anamnésico.",
            bookImageId = R.drawable.b6
        ),
        Books(
            id = 7,
            title = "El origen de las especies (Charles Darwin)",
            tags = "ciencia, biologia",
            description = "El libro fundacional de todo lo que es el campo de la biología evolucionista, tratándose de una de las piezas científicas más importantes jamás escritas..",
            bookImageId = R.drawable.b7
        ),
        Books(
            id = 8,
            title = "El futuro de nuestra mente (Michio Kaku)",
            tags = "ciencia, fisica, filosofia",
            description = "Una narrativa que estaría entre la frontera de los últimos avances neurocientíficos y la ciencia ficción.",
            bookImageId = R.drawable.b8
        ),
        Books(
            id = 9,
            title = "Brevísima historia del tiempo (Stephen Hawking)",
            tags = "ciencia, matemticas, fisica, astronomia",
            description = "En él se exponen temas de cosmología, como el Big Bang y los agujeros negros, pero de una forma sencilla, apta para la audiencia no especializada.",
            bookImageId = R.drawable.b9
        ),
        Books(
            id = 10,
            title = "El bonobo y los diez mandamientos (Frans de Waal)",
            tags = "ciencia, filosofia",
            description = "Frans de Waal, biólogo neerlandés, expone en El bonobo y los diez mandamientos una forma de ver a estos primates que no deja indiferente.",
            bookImageId = R.drawable.b10
        ),
    )
}