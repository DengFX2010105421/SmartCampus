package com.dengfx.smart.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dengfx.base.ui.MultiStateViewFragment
import com.dengfx.smart.ARG_PARAM
import com.dengfx.smart.R
import com.dengfx.smart.databinding.FragmentAttendanceManagementBinding
import com.dengfx.smart.databinding.ItemAttendanceManagementBinding
import com.dengfx.smart.db.AppDataBase
import com.dengfx.smart.db.Curriculum
import com.dengfx.smart.db.CurriculumRepository
import com.dengfx.smart.vm.AttendanceManagementViewModel
import com.dengfx.smart.vm.AttendanceManagementViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AttendanceManagementFragment :
    MultiStateViewFragment<AttendanceManagementViewModel, FragmentAttendanceManagementBinding>(
        R.layout.fragment_attendance_management,
        AttendanceManagementViewModel::class.java
    ) {
    companion object {
        fun newInstance() = AttendanceManagementFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding2.recyclerView.adapter = AttendanceManagementAdapter().apply {

            addHeaderView(
                BaseViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_attendance_management,
                        viewDataBinding2.recyclerView,
                        false
                    )
                ).apply {
                    getView<AppCompatTextView>(R.id.tvCurriculumName).apply {
                        text = "课程名称"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    }
                    getView<AppCompatTextView>(R.id.tvCurriculumTeacher).apply {
                        text = "老师\n姓名"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvCurriculumCollage).apply {
                        text = "学院"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvCurriculumAddress).apply {
                        text = "上课地点"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvCurriculumDate).apply {
                        text = "日期"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                }.itemView
            )
            setOnItemClickListener { adapter, view, position ->
                val curriculum: Curriculum =
                    (adapter as AttendanceManagementAdapter).getItem(position)
                Navigation.findNavController(view).navigate(
                    R.id.action_nav_attendance_management_to_nav_attendance_detail,
                    bundleOf(ARG_PARAM to curriculum)
                )
            }
        }

        viewDataBinding2.fab.setOnClickListener {
            Snackbar.make(
                view,
                "Replace with your own action",
                Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
        }

        viewModel.curriculums.observe(viewLifecycleOwner, Observer {
            (viewDataBinding2.recyclerView.adapter as AttendanceManagementAdapter).data.addAll(it)
        })
    }

    override fun generateViewModel(): AttendanceManagementViewModel {
        return AttendanceManagementViewModelFactory(
            CurriculumRepository.getInstance(
                AppDataBase.getInstance(attachedContext).curriculumDao()
            ),
            this,
            this.arguments
        ).create(AttendanceManagementViewModel::class.java)
    }
}

class AttendanceManagementAdapter :
    BaseQuickAdapter<Curriculum, BaseViewHolder>(R.layout.item_attendance_management) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemAttendanceManagementBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Curriculum) {
        val binding = helper.getBinding<ItemAttendanceManagementBinding>()
        binding?.curriculum = item
    }

}
