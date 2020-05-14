package com.dengfx.smart.vm

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.smart.db.Equipment
import com.dengfx.smart.db.EquipmentRepository
import kotlinx.coroutines.launch

class TrainingEquipmentManagementViewModel internal constructor(
    private val repository: EquipmentRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val text: LiveData<String> = MutableLiveData<String>().apply {
        value = "实训器材管理"
    }

    val equipments: LiveData<List<Equipment>> = repository.getEquipments()

    fun delete(equipment: Equipment) {
        viewModelScope.launch {
            repository.deleteEquipment(equipment)
        }
    }
}

class TrainingEquipmentManagementViewModelFactory(
    private val repository: EquipmentRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    public override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return TrainingEquipmentManagementViewModel(repository, handle) as T
    }
}
