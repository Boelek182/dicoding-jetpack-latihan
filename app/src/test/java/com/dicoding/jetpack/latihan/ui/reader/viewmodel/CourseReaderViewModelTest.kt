package com.dicoding.jetpack.latihan.ui.reader.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpack.latihan.data.ContentEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyCourses
import com.dicoding.jetpack.latihan.utils.FakeDataDummyUnitTest.generateDummyModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class CourseReaderViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

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
        val moduleEntities = MutableLiveData<MutableList<ModuleEntity>>()
        moduleEntities.value = dummyModules
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)

        val observer = mock(Observer::class.java) as Observer<List<ModuleEntity>?>
        courseReaderViewModel?.getModules()?.observeForever(observer)
        verify(observer).onChanged(dummyModules)
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = MutableLiveData<ModuleEntity>()

        val dummyModule = dummyModules[0]
        val content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        dummyModule.contentEntity = ContentEntity(content)

        moduleEntity.value = dummyModule
        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(moduleEntity)

        courseReaderViewModel?.moduleId = moduleId

        val observer = mock(Observer::class.java) as Observer<ModuleEntity?>
        courseReaderViewModel?.getSelectedModule()?.observeForever(observer)
        verify(observer).onChanged(dummyModule)
    }
}