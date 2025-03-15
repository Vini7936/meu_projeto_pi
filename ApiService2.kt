package com.example.meu_projeto_pi.api

interface ApiService2 {
    @GET("depesas.php")
    fun getDespesas(): Call<List<Despesas>>

}1