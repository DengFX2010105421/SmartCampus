package com.dengfx.smart.vm

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.smart.db.Curriculum
import com.dengfx.smart.db.CurriculumRepository

class AttendanceManagementViewModel internal constructor(
    private val repository: CurriculumRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val text: LiveData<String> = MutableLiveData<String>().apply {
        value = "考勤管理"
    }

    val curriculums: LiveData<List<Curriculum>> = repository.getCurriculums()
}

class AttendanceManagementViewModelFactory(
    private val repository: CurriculumRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    public override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return AttendanceManagementViewModel(repository, handle) as T
    }
}
