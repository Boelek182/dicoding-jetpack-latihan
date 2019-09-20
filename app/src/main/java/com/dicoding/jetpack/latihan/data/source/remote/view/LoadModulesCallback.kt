package com.dicoding.jetpack.latihan.data.source.remote.view

import com.dicoding.jetpack.latihan.data.source.remote.response.ModuleResponse

interface LoadModulesCallback {
    fun onAllModulesReceived(moduleResponses: List<ModuleResponse>?)

    fun onDataNotAvailable()
}