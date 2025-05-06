package com.ltu.m7019e.themoviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.themoviedb.database.MovieRepository
import com.ltu.m7019e.themoviedb.network.NetworkMonitor

class MovieDBViewModelFactory(
    private val repository: MovieRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDBViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDBViewModel(repository, networkMonitor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}