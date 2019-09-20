package com.dicoding.jetpack.latihan.data.source.remote.view

import com.dicoding.jetpack.latihan.data.source.remote.response.ContentResponse

interface GetContentCallback {
    fun onContentReceived(contentResponse: ContentResponse?)

    fun onDataNotAvailable()
}