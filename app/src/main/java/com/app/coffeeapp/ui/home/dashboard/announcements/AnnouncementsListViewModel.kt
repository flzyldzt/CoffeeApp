package com.app.coffeeapp.ui.home.dashboard.announcements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.coffeeapp.domain.announcements.AnnouncementsUiModel
import com.app.coffeeapp.domain.announcements.AnnouncementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementsListViewModel @Inject constructor(
    private val announcementsUseCase: AnnouncementsUseCase
) : ViewModel() {

    private val _announcements = MutableLiveData<List<AnnouncementsUiModel>>()
    val announcements: LiveData<List<AnnouncementsUiModel>> = _announcements

    init {
        loadCampaigns()
    }

    private fun loadCampaigns() {
        viewModelScope.launch {
            _announcements.value = announcementsUseCase()
        }
    }
}