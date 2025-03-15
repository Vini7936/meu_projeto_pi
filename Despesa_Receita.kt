package com.example.meu_projeto_pi

data class Despesa_Receita {
    val DESPESA_ID: Int,
    val DESPESA_NOME: Varchar,
    val DESPESA_VALOR: Double,
    val DESPESA_VENCIMENTO: Date,
    val DESPESA_CATEGORIA: String,
    val DESPESA_TIPO: Enum('despesa', 'receita'),
    val DESPESA_RECEBEDOR: Varchar,
    val DESPESA_STATUS: Varchar
}