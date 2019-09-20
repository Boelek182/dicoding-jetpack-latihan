package com.dicoding.jetpack.latihan.data.source.remote.view

import com.dicoding.jetpack.latihan.data.source.remote.response.CourseResponse

interface LoadCoursesCallback {
    fun onAllCoursesReceived(courseResponses: List<CourseResponse>?)

    fun onDataNotAvailable()
}