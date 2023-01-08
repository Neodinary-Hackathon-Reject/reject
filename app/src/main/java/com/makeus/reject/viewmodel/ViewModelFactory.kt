package com.makeus.reject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.makeus.reject.network.repository.ProjectRepository
import com.makeus.reject.network.repository.RoomRepository
import com.makeus.reject.network.repository.UserRepository

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            val repository = UserRepository()
            SignupViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val repository = ProjectRepository()
            HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            val repository = ProjectRepository()
            ProjectViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProjectDetailViewModel::class.java)) {
            val repository = ProjectRepository()
            ProjectDetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProjectPeopleViewModel::class.java)) {
            val repository = RoomRepository()
            ProjectPeopleViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MateDetailViewModel::class.java)) {
            val repository = UserRepository()
            MateDetailViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}