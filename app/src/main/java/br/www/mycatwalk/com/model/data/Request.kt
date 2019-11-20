package br.www.mycatwalk.com.model.data


data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)