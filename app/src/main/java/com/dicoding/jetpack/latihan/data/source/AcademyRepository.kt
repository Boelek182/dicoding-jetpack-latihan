package com.dicoding.jetpack.latihan.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jetpack.latihan.data.ContentEntity
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.remote.RemoteRepository
import com.dicoding.jetpack.latihan.data.source.remote.response.ContentResponse
import com.dicoding.jetpack.latihan.data.source.remote.response.CourseResponse
import com.dicoding.jetpack.latihan.data.source.remote.response.ModuleResponse
import com.dicoding.jetpack.latihan.data.source.remote.view.GetContentCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadCoursesCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadModulesCallback

class AcademyRepository(private val remoteRepository: RemoteRepository?) : AcademyDataSource {

    companion object {
        @Volatile
        private var INSTANCE: AcademyRepository? = null

        fun getInstance(remoteData: RemoteRepository?): AcademyRepository {
            if (INSTANCE == null) {
                synchronized(AcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AcademyRepository(remoteData)
                    }
                }
            }
            return INSTANCE as AcademyRepository
        }
    }

    override fun getAllCourses(): LiveData<MutableList<CourseEntity>?> {
        val courseResults = MutableLiveData<MutableList<CourseEntity>>()

        remoteRepository?.getAllCourses(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>?) {
                val courseList = ArrayList<CourseEntity>()
                if (courseResponses != null) {
                    for (element in courseResponses) {
                        val (id, title, description, date, imagePath) = element
                        val course = CourseEntity(id,
                                title,
                                description,
                                date,
                                false,
                                imagePath)
                        courseList.add(course)
                    }
                }
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {
            }
        })

        return courseResults
    }

    override fun getCourseWithModules(courseId: String?): LiveData<CourseEntity?> {
        val courseResult = MutableLiveData<CourseEntity>()

        remoteRepository?.getAllCourses(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>?) {
                if (courseResponses != null) {
                    for (element in courseResponses) {
                        val (id, title, description, date, imagePath) = element
                        if (id == courseId) {
                            val course = CourseEntity(id,
                                    title,
                                    description,
                                    date,
                                    false,
                                    imagePath)
                            courseResult.postValue(course)
                        }
                    }
                }
            }

            override fun onDataNotAvailable() {
            }
        })
        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String?): LiveData<MutableList<ModuleEntity>?> {
        val moduleResults = MutableLiveData<MutableList<ModuleEntity>>()

        remoteRepository?.getModules(courseId, object : LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>?) {
                val moduleList = ArrayList<ModuleEntity>()
                if (moduleResponses != null) {
                    for (element in moduleResponses) {
                        val (moduleId, courseId1, title, position) = element
                        val course = ModuleEntity(moduleId,
                                courseId1,
                                title,
                                position,
                                false)
                        moduleList.add(course)
                    }
                }
                moduleResults.postValue(moduleList)
            }

            override fun onDataNotAvailable() {
            }
        })
        return moduleResults
    }

    override fun getBookmarkedCourses(): LiveData<MutableList<CourseEntity>?> {
        val courseResults = MutableLiveData<MutableList<CourseEntity>>()

        remoteRepository?.getAllCourses(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>?) {
                if (courseResponses != null) {
                    val courseList = ArrayList<CourseEntity>()
                    for (element in courseResponses) {
                        val (id, title, description, date, imagePath) = element
                        val course = CourseEntity(id,
                                title,
                                description,
                                date,
                                false,
                                imagePath)
                        courseList.add(course)
                    }
                    courseResults.postValue(courseList)
                }
            }

            override fun onDataNotAvailable() {
            }
        })
        return courseResults
    }

    override fun getContent(courseId: String?, moduleId: String?): LiveData<ModuleEntity?> {
        val moduleResult = MutableLiveData<ModuleEntity>()

        remoteRepository?.getModules(courseId, object : LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>?) {
                val module: ModuleEntity
                if (moduleResponses != null) {
                    for (element in moduleResponses) {
                        val (id, courseId1, title, position) = element
                        if (id == moduleId) {
                            module = ModuleEntity(id, courseId1, title, position, false)
                            remoteRepository.getContent(moduleId, object : GetContentCallback {
                                override fun onContentReceived(contentResponse: ContentResponse?) {
                                    module.contentEntity = ContentEntity(contentResponse?.content)
                                    moduleResult.postValue(module)
                                }

                                override fun onDataNotAvailable() {}
                            })
                            break
                        }
                    }
                }
            }

            override fun onDataNotAvailable() {
            }
        })
        return moduleResult
    }
}