package com.dicoding.jetpack.latihan.data.source.remote

import android.os.Handler
import com.dicoding.jetpack.latihan.data.source.remote.view.GetContentCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadCoursesCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadModulesCallback
import com.dicoding.jetpack.latihan.utils.EspressoIdlingResource.decrement
import com.dicoding.jetpack.latihan.utils.EspressoIdlingResource.increment
import com.dicoding.jetpack.latihan.utils.JsonHelper

class RemoteRepository(private val jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null
        private const val SERVICE_LATENCY_IN_MILLIS = 2000L

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        increment()
        val handler = Handler()
        handler.postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
            decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String?, callback: LoadModulesCallback) {
        increment()
        val handler = Handler()
        handler.postDelayed({
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
            decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String?, callback: GetContentCallback) {
        increment()
        val handler = Handler()
        handler.postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId))
            decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }
}