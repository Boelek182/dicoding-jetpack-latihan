package com.dicoding.jetpack.latihan.di

import android.app.Application
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.data.source.remote.RemoteRepository
import com.dicoding.jetpack.latihan.utils.JsonHelper

object Injection {
    fun provideRepository(application: Application): AcademyRepository {
        val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))
        return AcademyRepository.getInstance(remoteRepository)
    }
}