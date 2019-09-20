package com.dicoding.jetpack.latihan.ui.bookmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.ui.bookmart.adapter.BookmarkAdapter
import com.dicoding.jetpack.latihan.ui.bookmart.view.BookmarkFragmentCallback
import com.dicoding.jetpack.latihan.ui.bookmart.viewmodel.BookmarkViewModel
import com.dicoding.jetpack.latihan.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    companion object {
        fun newInstance(): Fragment {
            return BookmarkFragment()
        }
    }

    private var bookmarkViewModel: BookmarkViewModel? = null
    private var bookmarkAdapter: BookmarkAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            bookmarkViewModel = obtainViewModel(activity)

            bookmarkAdapter = BookmarkAdapter(activity, this, null)

            bookmarkViewModel?.getBookmarks()?.observe(this, Observer {
                progressBarBookMark.visibility = View.GONE
                bookmarkAdapter?.mCourses = it
                bookmarkAdapter?.notifyDataSetChanged()
            })

            rvBookmark.layoutManager = LinearLayoutManager(activity)
            rvBookmark.setHasFixedSize(true)
            rvBookmark.adapter = bookmarkAdapter
        }
    }

    override fun onShareClick(course: CourseEntity?) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(activity)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(String.format("Segera daftar kelas %s di dicoding.com", course?.title))
                    .startChooser()
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): BookmarkViewModel? {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(BookmarkViewModel::class.java) }
    }
}