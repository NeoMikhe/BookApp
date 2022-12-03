package com.example.bookapp.models
import com.google.gson.annotations.SerializedName
import java.io.File


data class User(
    val user: UserX,
    val message: String
)

data class UserX(
    val email: String,
    val id: Int,
    val name: String,
    val role: String,
    val token: String
)

data class UserPost(
    val email: String,
    val password: String
)

data class LibroPost(
    val title: String,
    val description: String,
    val tags: String,
    val authors: String,
    val image: File
)

data class Libro(
    val `data`: DataLibros,
    val message: String
)

data class DataLibros(
    val allBooks: List<AllBook>
)

data class AllBook(
    @SerializedName("_authors")
    val authors: String,
    @SerializedName("_bookId")
    val bookId: Int,
    @SerializedName("_description")
    val description: String,
    @SerializedName("_image")
    val image: String,
    @SerializedName("_image_public_id")
    val imagePublicId: String,
    @SerializedName("_table")
    val table: String,
    @SerializedName("_tags")
    val tags: String,
    @SerializedName("_title")
    val title: String
)

data class Comentario(
    val `data`: DataComentarios,
    val message: String
)

data class DataComentarios(
    val allComentarios: List<AllComentario>
)

data class AllComentario(
    @SerializedName("_comentarioId")
    val comentarioId: Int,
    @SerializedName("_content")
    val content: String,
    @SerializedName("_created_at")
    val createdAt: String,
    @SerializedName("_resenaId")
    val resenaId: Int,
    @SerializedName("_table")
    val table: String,
    @SerializedName("_usuarioId")
    val usuarioId: Int
)

data class PostComentario(
    val content: String,
)

data class Resena(
    val `data`: DataResena,
    val message: String
)

data class PostResena(
    val title: String,
    val content: String,
    val visible: Boolean,
)

data class DataResena(
    val allResenas: List<AllResena>
)

data class AllResena(
    @SerializedName("_bookId")
    val bookId: Int,
    @SerializedName("_content")
    val content: String,
    @SerializedName("_created_at")
    val createdAt: String,
    @SerializedName("_edited_at")
    val editedAt: Any,
    @SerializedName("_resenaId")
    val resenaId: Int,
    @SerializedName("_table")
    val table: String,
    @SerializedName("_title")
    val title: String,
    @SerializedName("_usuarioId")
    val usuarioId: Int,
    @SerializedName("_visible")
    val visible: Int
)