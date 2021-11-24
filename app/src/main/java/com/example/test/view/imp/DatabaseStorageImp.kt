package com.example.test.view.imp

interface DatabaseStorageImp {

    fun createDbSuccess()

    fun createDbFailure(error: String)

    fun updateDbSuccess()

    fun updateDbFailure()

    fun insertDbFailure()

    fun insertDbSuccess()
}