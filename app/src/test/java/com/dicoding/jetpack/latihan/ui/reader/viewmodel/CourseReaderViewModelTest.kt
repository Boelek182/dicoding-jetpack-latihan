package com.dicoding.jetpack.latihan.ui.reader.viewmodel

import com.dicoding.jetpack.latihan.data.ContentEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyModules
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class CourseReaderViewModelTest {

    private var courseReaderViewModel: CourseReaderViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)
    private val dummyContentEntity = generateDummyCourses()[0]
    private val courseId = dummyContentEntity.courseId
    private val dummyModules = generateDummyModules(courseId)
    private var moduleId = dummyModules[0].mModuleId

    @Before
    fun setUp() {
        courseReaderViewModel = CourseReaderViewModel(academyRepository)
        courseReaderViewModel?.courseId = courseId
    }

    @Test
    fun getModules() {
        `when`<List<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)
        val moduleEntities = courseReaderViewModel?.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = dummyModules[0]
        val content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        moduleEntity.contentEntity = ContentEntity(content)
        courseReaderViewModel?.moduleId = moduleId

        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(moduleEntity)
        val entity = courseReaderViewModel?.getSelectedModule()
        verify(academyRepository).getContent(courseId, moduleId)
        assertNotNull(entity)

        val contentEntity = entity?.contentEntity
        assertNotNull(contentEntity)

        val resultContent = contentEntity?.mContent
        assertNotNull(resultContent)

        assertEquals(content, resultContent)
    }
}