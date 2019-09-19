package com.dicoding.jetpack.latihan.ui.bookmart.viewmodel

import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class BookmarkViewModelTest {

    private var bookmarkViewModel: BookmarkViewModel? = null
    private val academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        bookmarkViewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        `when`<List<CourseEntity>>(academyRepository.getBookmarkedCourses()).thenReturn(generateDummyCourses())
        val courseEntities = bookmarkViewModel?.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}