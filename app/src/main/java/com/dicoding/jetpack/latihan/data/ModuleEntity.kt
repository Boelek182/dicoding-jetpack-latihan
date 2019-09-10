package com.dicoding.jetpack.latihan.data

data class ModuleEntity(
    var mModuleId: String? = null,
    var mCourseId: String? = null,
    var mTitle: String? = null,
    var mPosition: Int? = null,
    var mRead: Boolean? = false
) {
    var contentEntity: ContentEntity? = null
}