package com.dicoding.jetpack.latihan.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.ui.reader.viewmodel.CourseReaderViewModel
import com.dicoding.jetpack.latihan.viewmodel.ViewModelFactory
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
            progressBarModuleContent.visibility = View.VISIBLE
            courseReaderViewModel = obtainViewModel(activity)
            courseReaderViewModel?.getSelectedModule()?.observe(this, Observer {
                progressBarModuleContent.visibility = View.GONE
                populateWebView(it)
            })
        }
    }

    private fun populateWebView(entity: ModuleEntity?) {
        webView.loadData(entity?.contentEntity?.mContent, "text/html", "UTF-8")
    }

    private fun obtainViewModel(activity: FragmentActivity?): CourseReaderViewModel? {
        // Use a Factory to inject dependencies into the ViewModel

        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(CourseReaderViewModel::class.java) }
    }
}