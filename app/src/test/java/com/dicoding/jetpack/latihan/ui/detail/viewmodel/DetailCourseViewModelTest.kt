package com.dicoding.jetpack.latihan.ui.detail.viewmodel

import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyModules
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class DetailCourseViewModelTest {

    private var detailCourseViewModel: DetailCourseViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)
    private val dummyCourse = generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setUp() {
        detailCourseViewModel = DetailCourseViewModel(academyRepository)
        detailCourseViewModel?.courseId = courseId
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
        val courseEntity: CourseEntity? = detailCourseViewModel?.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        val courseId = courseEntity?.courseId
        assertNotNull(courseId)
        assertEquals(dummyCourse.courseId, courseId)
    }

    @Test
    fun getModules() {
        `when`<List<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId)).thenReturn(generateDummyModules(courseId))
        val moduleEntities = detailCourseViewModel?.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)
    }
}