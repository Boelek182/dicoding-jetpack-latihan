package com.dicoding.jetpack.latihan.ui.bookmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.ui.bookmart.adapter.BookmarkAdapter
import com.dicoding.jetpack.latihan.ui.bookmart.view.BookmarkFragmentCallback
import com.dicoding.jetpack.latihan.ui.bookmart.viewmodel.BookmarkViewModel
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
            bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)

            bookmarkAdapter = bookmarkViewModel?.getBookmarks()?.let { BookmarkAdapter(activity, this, it) }
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
}