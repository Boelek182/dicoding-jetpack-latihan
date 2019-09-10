package com.dicoding.jetpack.latihan.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.ui.reader.viewmodel.CourseReaderViewModel
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment {
            return ModuleContentFragment()
        }
    }

    private var courseReaderViewModel: CourseReaderViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            courseReaderViewModel = ViewModelProviders.of(activity!!).get(CourseReaderViewModel::class.java)
            populateWebView(courseReaderViewModel?.getSelectedModule())
        }
    }

    private fun populateWebView(entity: ModuleEntity?) {
        webView.loadData(entity?.contentEntity?.mContent, "text/html", "UTF-8")
    }
}