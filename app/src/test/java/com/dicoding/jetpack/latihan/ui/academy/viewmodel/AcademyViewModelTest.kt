package com.dicoding.jetpack.latihan.ui.academy.viewmodel

import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class AcademyViewModelTest {

    private var academyViewModel: AcademyViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        academyViewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        `when`<List<CourseEntity>>(academyRepository.getAllCourses()).thenReturn(generateDummyCourses())
        val courseEntities = academyViewModel?.getCourses()
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}