package com.dicoding.jetpack.latihan.ui.academy.viewmodel

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

class AcademyViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var academyViewModel: AcademyViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        academyViewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        val dummyCourses = generateDummyCourses()
        val courses = MutableLiveData<MutableList<CourseEntity>>()
        courses.value = dummyCourses
        `when`(academyRepository.getAllCourses()).thenReturn(courses)

        val observer = mock(Observer::class.java) as Observer<MutableList<CourseEntity>?>
        academyViewModel?.getCourses()?.observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}