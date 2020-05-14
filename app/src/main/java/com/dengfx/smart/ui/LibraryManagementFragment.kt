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
import com.dengfx.smart.databinding.FragmentLibraryManagementBinding
import com.dengfx.smart.databinding.ItemLibraryEquipmentManagementBinding
import com.dengfx.smart.db.AppDataBase
import com.dengfx.smart.db.Equipment
import com.dengfx.smart.db.EquipmentRepository
import com.dengfx.smart.vm.LibraryManagementViewModel
import com.dengfx.smart.vm.LibraryManagementViewModelFactory

class LibraryManagementFragment :
    MultiStateViewFragment<LibraryManagementViewModel, FragmentLibraryManagementBinding>(
        R.layout.fragment_library_management,
        LibraryManagementViewModel::class.java
    ) {

    companion object {
        fun newInstance() = LibraryManagementFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding2.recyclerView.adapter = LibraryManagementFragmentAdapter().apply {

            addHeaderView(
                BaseViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_library_equipment_management,
                        viewDataBinding2.recyclerView,
                        false
                    )
                ).apply {
                    getView<AppCompatTextView>(R.id.tvEquipmentName).apply {
                        text = "设备名称"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)

                    }
                    getView<AppCompatTextView>(R.id.tvEquipmentIP).apply {
                        text = "设备IP"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                    getView<AppCompatTextView>(R.id.tvEquipmentPhoneNumber).apply {
                        text = "管理人员电话"
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                }.itemView
            )

            setOnItemClickListener { adapter, view, position ->
                val equipment: Equipment =
                    (adapter as LibraryManagementFragmentAdapter).getItem(position)
                Navigation.findNavController(view).navigate(
                    R.id.action_nav_library_management_to_nav_library_detail,
                    bundleOf(ARG_PARAM to equipment)
                )
            }
        }

        viewModel.equipments.observe(viewLifecycleOwner, Observer {
            it?.also {
                (viewDataBinding2.recyclerView.adapter as LibraryManagementFragmentAdapter).apply {
                    data.clear()
                    data.addAll(it)
                    notifyDataSetChanged()
                }
            }
        })

    }

    override fun generateViewModel(): LibraryManagementViewModel {
        return LibraryManagementViewModelFactory(
            EquipmentRepository.getInstance(
                AppDataBase.getInstance(attachedContext).equipmentDao()
            ),
            this,
            this.arguments
        ).create(LibraryManagementViewModel::class.java)
    }
}


class LibraryManagementFragmentAdapter :
    BaseQuickAdapter<Equipment, BaseViewHolder>(R.layout.item_library_equipment_management) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemLibraryEquipmentManagementBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Equipment) {
        helper.getBinding<ItemLibraryEquipmentManagementBinding>()?.equipment = item
    }
}


