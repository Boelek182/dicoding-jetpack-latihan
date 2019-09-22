package com.dicoding.jetpack.latihan.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.jetpack.latihan.data.source.remote.RemoteRepository
import com.dicoding.jetpack.latihan.data.source.remote.view.GetContentCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadCoursesCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadModulesCallback
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateRemoteDummyContent
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateRemoteDummyCourses
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateRemoteDummyModules
import com.dicoding.jetpack.latihan.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class AcademyRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponses = generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = generateRemoteDummyContent(moduleId)

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadCoursesCallback).onAllCoursesReceived(courseResponses)
            return@doAnswer null
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponses.size, result?.size)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadCoursesCallback).onAllCoursesReceived(courseResponses)
            return@doAnswer null
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertNotNull(result?.title)
        assertEquals(courseResponses[0].title, result?.title)
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadModulesCallback).onAllModulesReceived(moduleResponses)
            return@doAnswer null
        }.`when`(remote).getModules(eq(courseId), any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(remote, times(1)).getModules(eq(courseId), any())

        assertNotNull(result)
        assertEquals(moduleResponses.size, result?.size)
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadCoursesCallback).onAllCoursesReceived(courseResponses)
            return@doAnswer null
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponses.size, result?.size)
    }

    @Test
    fun getContent() {
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadModulesCallback).onAllModulesReceived(moduleResponses)
            return@doAnswer null
        }.`when`(remote).getModules(eq(courseId), any())

        doAnswer { invocation ->
            (invocation.arguments[1] as GetContentCallback).onContentReceived(content)
            null
        }.`when`(remote).getContent(eq(moduleId), any())

        val resultContent =
            LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        verify(remote, times(1)).getModules(eq(courseId), any())

        verify(remote, times(1)).getContent(eq(moduleId), any())

        assertNotNull(resultContent)
        assertNotNull(resultContent?.contentEntity)
        assertNotNull(resultContent?.contentEntity?.mContent)
        assertEquals(content.content, resultContent?.contentEntity?.mContent)
    }
}