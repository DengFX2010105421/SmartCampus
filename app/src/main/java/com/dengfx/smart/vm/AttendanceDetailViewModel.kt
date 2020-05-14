package com.dengfx.smart.vm

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.smart.db.Attendance
import com.dengfx.smart.db.AttendanceRepository

class AttendanceDetailViewModel internal constructor(
    private val repository: AttendanceRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val text: LiveData<String> = MutableLiveData<String>().apply {
        value = "考勤管理"
    }

    val attendances: LiveData<List<Attendance>> = repository.getAttendances()
}

class AttendanceDetailViewModelFactory(
    private val repository: AttendanceRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    public override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return AttendanceDetailViewModel(repository, handle) as T
    }
}
