package com.dengfx.smart.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.dengfx.base.ui.MultiStateViewFragment
import com.dengfx.smart.R
import com.dengfx.smart.databinding.FragmentOperateEquipmentBinding
import com.dengfx.smart.db.AppDataBase
import com.dengfx.smart.db.Equipment
import com.dengfx.smart.db.EquipmentRepository
import com.dengfx.smart.vm.OperateEquipmentViewModel
import com.dengfx.smart.vm.OperateEquipmentViewModelFactory

class OperateEquipmentFragment :
    MultiStateViewFragment<OperateEquipmentViewModel, FragmentOperateEquipmentBinding>(
        R.layout.fragment_operate_equipment,
        OperateEquipmentViewModel::class.java
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding2.viewModel = this.viewModel

        viewDataBinding2.btnSave.setOnClickListener {
            val equipmentName = viewDataBinding2.etEquipmentName.text.toString().trim()
            val equipmentNumber = viewDataBinding2.etEquipmentNumber.text.toString().trim()
            val equipmentIP = viewDataBinding2.etEquipmentIP.text.toString().trim()
            val equipmentPersonInCharge =
                viewDataBinding2.etEquipmentPersonInCharge.text.toString().trim()
            val equipmentPhoneNumber =
                viewDataBinding2.etEquipmentPhoneNumber.text.toString().trim()

            if (TextUtils.isEmpty(equipmentName)
                || TextUtils.isEmpty(equipmentNumber)
                || TextUtils.isEmpty(equipmentIP)
                || TextUtils.isEmpty(equipmentPersonInCharge)
                || TextUtils.isEmpty(equipmentPhoneNumber)
            ) {
                ToastUtils.showShort("请填写相关信息")
                return@setOnClickListener
            }

            val equipment = Equipment(
                name = equipmentName,
                number = equipmentNumber,
                ip = equipmentIP,
                status = true,
                personInCharge = equipmentPersonInCharge,
                phoneNumber = equipmentPhoneNumber,
                location = ""
            )

            viewModel.save(equipment)
            ToastUtils.showShort("${if (viewModel.hasEquipment) "修改" else "添加"}成功")
            it.postDelayed({ viewDataBinding2.btnCancel.performClick() }, 1000)
        }

        viewDataBinding2.btnCancel.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    override fun generateViewModel(): OperateEquipmentViewModel {
        return OperateEquipmentViewModelFactory(
            EquipmentRepository.getInstance(
                AppDataBase.getInstance(attachedContext).equipmentDao()
            ),
            this,
            this.arguments
        ).create(OperateEquipmentViewModel::class.java)
    }

}
