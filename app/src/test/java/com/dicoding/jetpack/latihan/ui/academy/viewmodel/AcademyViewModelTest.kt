package com.dicoding.jetpack.latihan.ui.academy.viewmodel

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class AcademyViewModelTest {

    private var academyViewModel: AcademyViewModel? = null

    @Before
    fun setUp() {
        academyViewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = academyViewModel?.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}