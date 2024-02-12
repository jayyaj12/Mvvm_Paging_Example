package com.example.mvvmexample.util.network

class RetrofitFailureStateException(error: String ?, val code: Int) : Exception(error) {
}