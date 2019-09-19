package com.dicoding.jetpack.latihan.data.source

import com.dicoding.jetpack.latihan.data.ContentEntity
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.remote.RemoteRepository

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

    override fun getAllCourses(): MutableList<CourseEntity> {
        val courseResponses = remoteRepository?.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        if (courseResponses != null) {
            for (i in courseResponses.indices) {
                val response = courseResponses[i]
                val course = CourseEntity(response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath)
                courseList.add(course)
            }
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String?): CourseEntity? {
        var course: CourseEntity? = null
        val courses = remoteRepository?.getAllCourses()
        if (courses != null) {
            for (i in courses.indices) {
                val (id, title, description, date, imagePath) = courses[i]
                if (id == courseId) {
                    course = CourseEntity(id,
                            title,
                            description,
                            date,
                            false,
                            imagePath)
                }
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String?): MutableList<ModuleEntity> {
        val moduleList = ArrayList<ModuleEntity>()
        val moduleResponses = remoteRepository?.getModules(courseId)
        if (moduleResponses != null) {
            for (i in moduleResponses.indices) {
                val (moduleId, courseId1, title, position) = moduleResponses[i]
                val course = ModuleEntity(moduleId,
                        courseId1,
                        title,
                        position,
                        false)
                moduleList.add(course)
            }
        }
        return moduleList
    }

    override fun getBookmarkedCourses(): MutableList<CourseEntity> {
        val courseList = ArrayList<CourseEntity>()
        val courses = remoteRepository?.getAllCourses()
        if (courses != null) {
            for (i in courses.indices) {
                val (id, title, description, date, imagePath) = courses[i]
                val course = CourseEntity(id,
                        title,
                        description,
                        date,
                        false,
                        imagePath)
                courseList.add(course)
            }
        }
        return courseList
    }

    override fun getContent(courseId: String?, moduleId: String?): ModuleEntity? {
        val moduleResponses = remoteRepository?.getModules(courseId)
        var module: ModuleEntity? = null
        if (moduleResponses != null) {
            for (i in moduleResponses.indices) {
                val (id, courseId1, title, position) = moduleResponses[i]
                if (id == moduleId) {
                    module = ModuleEntity(id, courseId1, title, position, false)
                    module.contentEntity = ContentEntity(remoteRepository?.getContent(moduleId)?.content)
                    break
                }
            }
        }
        return module
    }
}