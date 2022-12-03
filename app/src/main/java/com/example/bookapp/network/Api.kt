package com.example.bookapp.network

import com.example.bookapp.models.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //Login Calls
    @Headers("Content-Type: application/json")
    @POST("user/sign-in")
    fun loginUser(@Body body : UserLogin) : Call<User>


    @POST("user/create")
    @Multipart
    fun registerUser(
        @Part name: MultipartBody.Part,
        @Part first_ln: MultipartBody.Part,
        @Part second_ln: MultipartBody.Part,
        @Part email: MultipartBody.Part,
        @Part password: MultipartBody.Part,
        @Part file: MultipartBody.Part?
    ) : Call<User>

    //Libros Calls
    @POST("libro/create")
    @Multipart
    fun uploadBook(
        @Header("Authorization") authToken: String?,
        @Part file: MultipartBody.Part?,
        @Part title: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part tags: MultipartBody.Part,
        @Part authors: MultipartBody.Part,
    ) : Call<Libro>

    @DELETE("libro/delete/{bookId}")
    fun deleteBook(@Header("Authorization") authToken : String? ,@Path("bookId") bookId: Int) : Call<Libro>

    @PATCH("libro/update/{bookId}")
    @Multipart
    fun updateBook(
        @Header("Authorization") authToken : String? ,
        @Path("bookId") bookId: Int,
        @Part file: MultipartBody.Part?,
        @Part title: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part tags: MultipartBody.Part,
        @Part authors: MultipartBody.Part,

        ) : Call<Libro>

    @GET("libro/{bookId}")
    fun getSingleBook(@Header("Authorization") authToken : String? ,@Path("bookId") bookId: Int) : Call<Libro>

    @GET("libro/")
    fun getAllLibros(@Header("Authorization") authToken : String? ,) : Call<Libro>


    //Reseñas Calls

    @GET("resena/")
    fun getAllResenas(@Header("Authorization") authToken : String? ,) : Call<Resena>

    @GET("resena/{resenaId}")
    fun getSingleResena(@Header("Authorization") authToken : String? ,@Path("resenaId") resenaId: Int) : Call<Resena>

    @POST("resena/create/{bookId}")
    fun uploadResena(@Header("Authorization") authToken : String? ,@Path("bookId") bookId: Int,@Body body: PostResena) : Call<Resena>

    @DELETE("resena/{resenaId}")
    fun deleteResena(@Header("Authorization") authToken : String? ,@Path("resenaId") resenaId: Int) : Call<Resena>

    @PATCH("resena/{resenaId}")
    fun updateResena(@Header("Authorization") authToken : String? ,@Path("resenaId") resenaId: Int, @Body body : PostResena) : Call<Resena>



    //Comentarios Calls

    @GET("comentario/")
    fun getAllComentarios(@Header("Authorization") authToken : String? ,) : Call<Comentario>


    @GET("comentario/{comentarioId}")
    fun getSingleComentario(@Header("Authorization") authToken : String? ,@Path("comentarioId") comentarioId: Int) : Call<Comentario>

    @GET("comentario/resena/{resenaId}")
    fun getAllComentariosFromResena(@Header("Authorization") authToken : String? ,@Path("resenaId") resenaId: Int) : Call<Comentario>


    @POST("comentario/create")
    fun uploadComentario(@Header("Authorization") authToken : String? ,@Query("resena") resena : Int, @Body body : PostComentario) : Call<Comentario>

    @DELETE("comentario/{comentarioId}")
    fun deleteComentario(@Header("Authorization") authToken : String? ,@Path("comentarioId") comentarioId: Int) : Call<Comentario>


    @PATCH("comentario/{comentarioId}")
    fun updateComentario(@Header("Authorization") authToken : String? ,@Path("comentarioId") comentarioId: Int, @Body body: PostComentario) : Call<Comentario>



}