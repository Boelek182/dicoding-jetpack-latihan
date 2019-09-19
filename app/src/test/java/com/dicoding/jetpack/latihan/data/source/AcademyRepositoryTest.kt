package com.dicoding.jetpack.latihan.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.jetpack.latihan.data.source.remote.RemoteRepository
import com.dicoding.jetpack.latihan.data.source.remote.response.ContentResponse
import com.dicoding.jetpack.latihan.data.source.remote.response.CourseResponse
import com.dicoding.jetpack.latihan.data.source.remote.response.ModuleResponse
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateRemoteDummyContent
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateRemoteDummyCourses
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateRemoteDummyModules
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class AcademyRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote: RemoteRepository = mock(RemoteRepository::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponses: ArrayList<CourseResponse> = generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses: ArrayList<ModuleResponse> = generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content: ContentResponse? = generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getAllCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size, courseEntities.size)
    }

    @Test
    fun getCourseWithModules() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponses)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponses[0].title, resultCourse?.title)
    }

    @Test
    fun getAllModulesByCourse() {
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponses)
        val moduleEntities = academyRepository.getAllModulesByCourse(courseId)
        verify(remote).getModules(courseId)
        assertNotNull(moduleEntities)
        assertEquals(moduleResponses.size, moduleEntities.size)
    }

    @Test
    fun getBookmarkedCourses() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size, courseEntities.size)
    }

    @Test
    fun getContent() {
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponses)
        `when`(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content!!.content, resultModule?.contentEntity?.mContent)
    }
}