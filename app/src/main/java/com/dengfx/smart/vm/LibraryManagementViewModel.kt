package com.dengfx.smart.vm

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.smart.db.Equipment
import com.dengfx.smart.db.EquipmentRepository

class LibraryManagementViewModel internal constructor(
    private val repository: EquipmentRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val text: LiveData<String> = MutableLiveData<String>().apply {
        value = "图书馆管理"
    }

    val equipments: LiveData<List<Equipment>> = repository.getEquipments(1)
}

class LibraryManagementViewModelFactory(
    private val repository: EquipmentRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    public override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return LibraryManagementViewModel(repository, handle) as T
    }
}

