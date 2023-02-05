package com.example.myshoppingonlinstore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import com.example.myshoppingonlinstore.utils.Event
class HomeViewModel: ViewModel() {
    //region Variables
    private val _openMenue = MutableLiveData<Event<Boolean?>?>()

    private val _openCard = MutableLiveData<Event<Boolean?>?>()

    private val _mutableCardVisibility = MutableLiveData<Int>()

    val mutableCardVisibility: LiveData<Int>
        get() = _mutableCardVisibility

    //endregion

    //region View model methods
    /**
     * Handle menue  button click.
     */
    fun menueClicked() {
        Timber.v("menueClicked")
        _openMenue.value = Event(true)
    }
    /**
     * Handle profile  button click.
     */
    fun cardClicked() {
        Timber.v("cardClicked")
        _openCard.value = Event(true)
    }
    //endregion
}