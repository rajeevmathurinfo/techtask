package com.techtaskapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtaskapp.domain.model.CatItem
import com.techtaskapp.domain.repository.CatRepository
import com.techtaskapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val catRepository: CatRepository) : ViewModel() {

    private val _user = MutableLiveData<Result<List<CatItem>>>()
    val cat: LiveData<Result<List<CatItem>>> get() = _user

    init {
        getCats(20)
    }

    fun getCats(limit: Int) {
        viewModelScope.launch {
            _user.value = Result.Loading
            val result = catRepository.getCats(limit)
            _user.value = result
        }
    }
}


