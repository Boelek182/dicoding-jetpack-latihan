package com.dicoding.jetpack.latihan.ui.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DetailCourseViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var detailCourseViewModel: DetailCourseViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)
    private val dummyCourse = generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = generateDummyModules(courseId)

    @Before
    fun setUp() {
        detailCourseViewModel = DetailCourseViewModel(academyRepository)
        detailCourseViewModel?.courseId = courseId
    }

    @Test
    fun getCourse() {
        val courseEntities = MutableLiveData<CourseEntity>()
        courseEntities.value = dummyCourse
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities)

        val observer = mock(Observer::class.java) as Observer<CourseEntity?>
        detailCourseViewModel?.getCourse()?.observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }

    @Test
    fun getModules() {
        val moduleEntities = MutableLiveData<MutableList<ModuleEntity>>()
        moduleEntities.value = dummyModules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)

        val observer = mock(Observer::class.java) as Observer<MutableList<ModuleEntity>?>
        detailCourseViewModel?.getModules()?.observeForever(observer)
        verify(observer).onChanged(dummyModules)
    }
}