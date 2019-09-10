package com.dicoding.jetpack.latihan.data

data class CourseEntity(
    var courseId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var deadline: String? = null,
    var bookmarked: Boolean? = false,
    var imagePath: String? = null
)