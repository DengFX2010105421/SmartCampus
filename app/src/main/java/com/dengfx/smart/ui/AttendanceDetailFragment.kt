package com.dengfx.smart.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dengfx.base.ui.MultiStateViewFragment
import com.dengfx.smart.R
import com.dengfx.smart.databinding.FragmentAttendanceDetailBinding
import com.dengfx.smart.databinding.ItemAttendanceDetailBinding
import com.dengfx.smart.db.AppDataBase
import com.dengfx.smart.db.Attendance
import com.dengfx.smart.db.AttendanceRepository
import com.dengfx.smart.vm.AttendanceDetailViewModel
import com.dengfx.smart.vm.AttendanceDetailViewModelFactory

class AttendanceDetailFragment :
    MultiStateViewFragment<AttendanceDetailViewModel, FragmentAttendanceDetailBinding>(
        R.layout.fragment_attendance_detail,
        AttendanceDetailViewModel::class.java
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding2.recyclerView.adapter = AttendanceDetailAdapter().apply {

            addHeaderView(
                BaseViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_attendance_detail,
                        viewDataBinding2.recyclerView,
                        false
                    )
                ).apply {
                    getView<AppCompatTextView>(R.id.tvAttendanceDate).apply {
                        text = "打卡时间"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    }
                    getView<AppCompatTextView>(R.id.tvStudentName).apply {
                        text = "学生姓名"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvStudentNumber).apply {
                        text = "学号"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvStudentClass).apply {
                        text = "班级"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                }.itemView
            )
        }

        viewModel.attendances.observe(viewLifecycleOwner, Observer {
            (viewDataBinding2.recyclerView.adapter as AttendanceDetailAdapter).data.addAll(it)
        })
    }

    override fun generateViewModel(): AttendanceDetailViewModel {
        return AttendanceDetailViewModelFactory(
            AttendanceRepository.getInstance(
                AppDataBase.getInstance(attachedContext).attendanceDao()
            ),
            this,
            this.arguments
        ).create(AttendanceDetailViewModel::class.java)
    }
}


class AttendanceDetailAdapter :
    BaseQuickAdapter<Attendance, BaseViewHolder>(R.layout.item_attendance_detail) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemAttendanceDetailBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Attendance) {
        helper.getBinding<ItemAttendanceDetailBinding>()?.attendance = item
    }
}

