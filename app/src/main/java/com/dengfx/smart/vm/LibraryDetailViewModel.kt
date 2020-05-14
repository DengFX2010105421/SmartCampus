package com.dengfx.smart.vm

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.smart.ARG_PARAM
import com.dengfx.smart.db.Book
import com.dengfx.smart.db.BookRepository
import com.dengfx.smart.db.Equipment

class LibraryDetailViewModel internal constructor(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val text: LiveData<String> = MutableLiveData<String>().apply {
        value = "考勤管理"
    }

    val books: LiveData<List<Book>> = repository.getBooks()

    val hasEquipment = savedStateHandle.contains(ARG_PARAM)

    val equipment = savedStateHandle.getLiveData<Equipment>(ARG_PARAM)
}

class LibraryDetailViewModelFactory(
    private val repository: BookRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    public override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return LibraryDetailViewModel(repository, handle) as T
    }
}
