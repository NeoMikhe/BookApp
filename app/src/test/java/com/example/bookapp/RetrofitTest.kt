package com.example.bookapp

import androidx.core.net.toUri
import com.example.bookapp.models.*
import com.example.bookapp.network.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.junit.Assert.*
import org.junit.Test
import java.io.File


class RetrofitTest {

    //Cambiar token para tests cada hora
    val authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MDA1NDAyNCwiZXhwIjoxNjcwMDU3NjI0fQ.JJJKtL9J_AvM3gG-a6MSgXtSO40P_caoycZ6DmFclUE"

    //Tests de Usuarios
    @Test
    fun login() {
        val api = RetrofitClient.getApi();
        val user = UserLogin("jordan@gmail.com","12345678")
        val call = api.loginUser(user)
        val response = call.execute();
        assertTrue(response.isSuccessful);

    }

    @Test
    fun register() {
        val api = RetrofitClient.getApi();
        val user = UserRegister("jordan", "higuera", "higuera", "jordan@email.com", "12345678", null)
        val name = MultipartBody.Part.createFormData("name", user.name)
        val first_ln = MultipartBody.Part.createFormData("first_ln", user.first_ln)
        val second_ln = MultipartBody.Part.createFormData("name", user.second_ln)
        val email = MultipartBody.Part.createFormData("name", user.email)
        val password = MultipartBody.Part.createFormData("name", user.password)


        val call = api.registerUser(name, first_ln, second_ln, email, password, null);

        val response = call.execute();

        assertTrue(response.isSuccessful)


    }


    //Tests de Libros
    @Test
    fun uploadLibro() {
        val api = RetrofitClient.getApi();
        val uri = File("C:\\Users\\jorda\\AndroidStudioProjects\\appproyectofinal\\app\\src\\test\\java\\mx\\uabcs\\appproyectofinal\\A.jpeg");
        val libro = LibroPost("Jordan", "AAAA", "aaa", "Jordan", uri);
        val filepart = MultipartBody.Part.createFormData("image", uri.path,
          uri.asRequestBody("image/*".toMediaTypeOrNull()
            ));
        val title =   MultipartBody.Part.createFormData("title", libro.title);
        val description =   MultipartBody.Part.createFormData("description", libro.description)
        val tags =   MultipartBody.Part.createFormData("tags", libro.tags)
        val authors =   MultipartBody.Part.createFormData("authors", libro.authors)
        val call = api.uploadBook(authToken,null, title, description, tags, authors);
        val response = call.execute();

        assertTrue(response.isSuccessful);
    }

    @Test
    fun getAllLibros() {
        val api = RetrofitClient.getApi();
        val call = api.getAllLibros(authToken);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    @Test
    fun updateLibro() {
        val api = RetrofitClient.getApi();
        val uri = File("C:\\Users\\jorda\\AndroidStudioProjects\\appproyectofinal\\app\\src\\test\\java\\mx\\uabcs\\appproyectofinal\\A.jpeg");
        val libro = LibroPost("AWESDAWD", "CZXCW", "ASDASD", "AWDAS", uri);
        val filepart = MultipartBody.Part.createFormData("image", uri.path,
            uri.asRequestBody("image/*".toMediaTypeOrNull()
            ));
        val title =   MultipartBody.Part.createFormData("title", libro.title);
        val description =   MultipartBody.Part.createFormData("description", libro.description)
        val tags =   MultipartBody.Part.createFormData("tags", libro.tags)
        val authors =   MultipartBody.Part.createFormData("authors", libro.authors)
        val call = api.updateBook(authToken,1,filepart, title, description, tags, authors);
        val response = call.execute();

        assertTrue(response.isSuccessful);
    }

    @Test
    fun deleteLibro() {
        val api = RetrofitClient.getApi();
        val call = api.deleteBook(authToken,7);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    @Test
    fun getSingleLibro() {
        val api = RetrofitClient.getApi();
        val call = api.getSingleBook(authToken,4);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    //Test de resenas
    @Test
    fun createResenas() {
        val api = RetrofitClient.getApi();
        val resena = PostResena("Jordan", "Esta muy bueno el libro", true);
        val call = api.uploadResena(authToken,3, resena);
        val response = call.execute();
        assertTrue(response.isSuccessful)
    }

    @Test
    fun getAllResenas() {
        val api = RetrofitClient.getApi();
        val call = api.getAllResenas(authToken);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    @Test
    fun getSingleResena() {
        val api = RetrofitClient.getApi();
        val call = api.getSingleResena(authToken,15);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    @Test
    fun deleteResena() {
        val api = RetrofitClient.getApi();
        val call = api.deleteResena(authToken,15);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    @Test
    fun updateResena() {
        val api = RetrofitClient.getApi();
        val resena = PostResena("Jordan", "Esta muy bueno el libro", true);
        val call = api.updateResena(authToken,16 ,resena);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    //Test de comentarios

    @Test
    fun createComentario(){
        val api = RetrofitClient.getApi();
        val comentario = PostComentario("Estuvo bueno el libro GG")
        val call = api.uploadComentario(authToken,4, comentario);
        val response = call.execute();
        assertTrue(response.isSuccessful)
    }

    @Test
    fun getAllComentarios() {
        val api = RetrofitClient.getApi();
        val call = api.getAllComentarios(authToken);
        val response = call.execute();
        assertTrue(response.isSuccessful)
    }

    @Test
    fun getSingleComentario() {
        val api = RetrofitClient.getApi();
        val call = api.getSingleComentario(authToken,4);
        val response = call.execute();
        assertTrue(response.isSuccessful)
    }

    @Test
    fun getAllComentariosFromResena(){
        val api = RetrofitClient.getApi();
        val call = api.getAllComentariosFromResena(authToken,4)
        val response = call.execute()
        assertTrue(response.isSuccessful)
    }

    @Test
    fun deleteComentario(){
        val api = RetrofitClient.getApi();
        val call = api.deleteComentario(authToken,4);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

    @Test
    fun updateComentario() {
        val api = RetrofitClient.getApi();
        val comentario = PostComentario("UPDATED");
        val call = api.updateComentario(authToken,7, comentario);
        val response = call.execute();
        assertTrue(response.isSuccessful);
    }

}