package com.dengfx.smart.vm

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.smart.ARG_PARAM
import com.dengfx.smart.db.Equipment
import com.dengfx.smart.db.EquipmentRepository
import kotlinx.coroutines.launch

class OperateEquipmentViewModel internal constructor(
    val repository: EquipmentRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val text: LiveData<String> = MutableLiveData<String>().apply {
        value = "添加设备"
    }

    val hasEquipment = savedStateHandle.contains(ARG_PARAM)

    val equipment = savedStateHandle.getLiveData<Equipment>(ARG_PARAM)

    fun save(equipment: Equipment) {
        if (hasEquipment) {
            this.equipment.value?.apply {
                name = equipment.name
                number = equipment.number
                ip = equipment.ip
                personInCharge = equipment.personInCharge
                phoneNumber = equipment.phoneNumber
                viewModelScope.launch {
                    repository.updateEquipment(this@apply)
                }
            }
        } else {
            viewModelScope.launch {
                repository.insertEquipment(equipment)
            }
        }
    }

}

class OperateEquipmentViewModelFactory(
    private val repository: EquipmentRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    public override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return OperateEquipmentViewModel(repository, handle) as T
    }
}
