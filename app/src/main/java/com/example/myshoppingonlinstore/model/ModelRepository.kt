package com.example.myshoppingonlinstore.model

import com.example.myshoppingonlinstore.api.ApiRepo
import com.example.myshoppingonlinstore.model.preferences.PreferencesRepo

class ModelRepository(
    private val _preferencesRepository: PreferencesRepo,
    private val _apiRepository: ApiRepo
):  ModelRepo {


}