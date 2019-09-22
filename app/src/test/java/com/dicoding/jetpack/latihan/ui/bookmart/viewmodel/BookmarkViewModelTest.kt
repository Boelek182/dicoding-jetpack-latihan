package com.dicoding.jetpack.latihan.ui.bookmart.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class BookmarkViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var bookmarkViewModel: BookmarkViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        bookmarkViewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        val dummyCourses = generateDummyCourses()
        val courses = MutableLiveData<MutableList<CourseEntity>>()
        courses.value = dummyCourses
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)

        val observer = mock(Observer::class.java) as Observer<MutableList<CourseEntity>?>
        bookmarkViewModel?.getBookmarks()?.observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}