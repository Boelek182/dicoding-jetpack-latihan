package com.dicoding.jetpack.latihan.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.di.Injection.provideRepository
import com.dicoding.jetpack.latihan.ui.academy.viewmodel.AcademyViewModel
import com.dicoding.jetpack.latihan.ui.bookmart.viewmodel.BookmarkViewModel
import com.dicoding.jetpack.latihan.ui.detail.viewmodel.DetailCourseViewModel
import com.dicoding.jetpack.latihan.ui.reader.viewmodel.CourseReaderViewModel

class ViewModelFactory(private val academyRepository: AcademyRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(provideRepository(application))
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> AcademyViewModel(academyRepository) as T
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> DetailCourseViewModel(academyRepository) as T
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> BookmarkViewModel(academyRepository) as T
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> CourseReaderViewModel(academyRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}