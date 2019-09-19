package com.dicoding.jetpack.latihan.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.ui.reader.CourseReaderActivity
import com.dicoding.jetpack.latihan.ui.reader.list.adapter.ModuleListAdapter
import com.dicoding.jetpack.latihan.ui.reader.list.view.MyAdapterClickListener
import com.dicoding.jetpack.latihan.ui.reader.view.CourseReaderCallback
import com.dicoding.jetpack.latihan.ui.reader.viewmodel.CourseReaderViewModel
import com.dicoding.jetpack.latihan.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_list.*


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName
        fun newInstance(): ModuleListFragment {
            return ModuleListFragment()
        }
    }

    private var courseReaderViewModel: CourseReaderViewModel? = null
    private var moduleListAdapter: ModuleListAdapter? = null
    private var courseReaderCallback: CourseReaderCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            courseReaderViewModel = obtainViewModel(activity)
            populateRecyclerView(courseReaderViewModel?.getModules())
        }
    }

    private fun populateRecyclerView(generateDummyModules: MutableList<ModuleEntity>?) {
        progressBarModuleList.visibility = View.GONE
        moduleListAdapter = generateDummyModules?.let { ModuleListAdapter(this, it) }

        rvModuleList.layoutManager = LinearLayoutManager(context)
        rvModuleList.setHasFixedSize(true)
        rvModuleList.adapter = moduleListAdapter
        val dividerItemDecoration = DividerItemDecoration(rvModuleList.context, DividerItemDecoration.VERTICAL)
        rvModuleList.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClicked(position: Int, moduleId: String?) {
        courseReaderCallback?.moveTo(position, moduleId)
        courseReaderViewModel?.moduleId = moduleId
    }

    private fun obtainViewModel(activity: FragmentActivity?): CourseReaderViewModel? {
        // Use a Factory to inject dependencies into the ViewModel

        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(CourseReaderViewModel::class.java) }
    }
}