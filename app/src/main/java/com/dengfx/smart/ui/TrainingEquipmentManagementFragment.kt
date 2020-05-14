package com.dengfx.smart.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
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
import com.dengfx.smart.databinding.FragmentTrainingEquipmentManagementBinding
import com.dengfx.smart.databinding.ItemTrainingEquipmentManagementBinding
import com.dengfx.smart.db.AppDataBase
import com.dengfx.smart.db.Equipment
import com.dengfx.smart.db.EquipmentRepository
import com.dengfx.smart.vm.TrainingEquipmentManagementViewModel
import com.dengfx.smart.vm.TrainingEquipmentManagementViewModelFactory
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog

class TrainingEquipmentManagementFragment :
    MultiStateViewFragment<TrainingEquipmentManagementViewModel, FragmentTrainingEquipmentManagementBinding>(
        R.layout.fragment_training_equipment_management,
        TrainingEquipmentManagementViewModel::class.java
    ) {

    companion object {
        fun newInstance() = TrainingEquipmentManagementFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding2.recyclerView.adapter = TrainingEquipmentManagementAdapter().apply {

            addHeaderView(
                BaseViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_training_equipment_management,
                        viewDataBinding2.recyclerView,
                        false
                    )
                ).apply {
                    getView<AppCompatTextView>(R.id.tvEquipmentName).apply {
                        text = "设备名称"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    }
                    getView<AppCompatTextView>(R.id.tvEquipmentNumber).apply {
                        text = "编号"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvEquipmentIP).apply {
                        text = "设备IP"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvEquipmentStatus).apply {
                        text = "状态"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                }.itemView
            )

            setOnItemClickListener { adapter, view, position ->
                val equipment: Equipment =
                    (adapter as TrainingEquipmentManagementAdapter).getItem(position)
                Navigation.findNavController(view).navigate(
                    R.id.action_nav_training_equipment_management_to_nav_operate_equipment,
                    bundleOf(ARG_PARAM to equipment)
                )
            }

            setOnItemLongClickListener { adapter, view, position ->
                NormalDialog(attachedContext).apply {
                    content("是否确定删除该项?")
                    contentGravity(Gravity.CENTER)
                    btnNum(2)
                    btnText("取消", "确定")
                    isTitleShow(false)
                    setOnBtnClickL(object : OnBtnClickL {
                        override fun onBtnClick() {
                            dismiss()
                        }
                    }, object : OnBtnClickL {
                        override fun onBtnClick() {
                            val equipment: Equipment =
                                (adapter as TrainingEquipmentManagementAdapter).getItem(position)
                            viewModel.delete(equipment)
                            dismiss()
                        }
                    })
                }.show()
                true
            }
        }
        viewDataBinding2.fab.setOnClickListener {
            Navigation.findNavController(view).navigate(
                R.id.action_nav_training_equipment_management_to_nav_operate_equipment
            )
        }
        viewModel.equipments.observe(viewLifecycleOwner, Observer {
            it?.also {
                (viewDataBinding2.recyclerView.adapter as TrainingEquipmentManagementAdapter).apply {
                    data.clear()
                    data.addAll(it)
                    notifyDataSetChanged()
                }
            }
        })
    }

    override fun generateViewModel(): TrainingEquipmentManagementViewModel {
        return TrainingEquipmentManagementViewModelFactory(
            EquipmentRepository.getInstance(
                AppDataBase.getInstance(attachedContext).equipmentDao()
            ),
            this,
            this.arguments
        ).create(TrainingEquipmentManagementViewModel::class.java)
    }
}

class TrainingEquipmentManagementAdapter :
    BaseQuickAdapter<Equipment, BaseViewHolder>(R.layout.item_training_equipment_management) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemTrainingEquipmentManagementBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Equipment) {
        helper.getBinding<ItemTrainingEquipmentManagementBinding>()?.equipment = item
    }
}

