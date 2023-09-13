package com.example.scanner.di

import com.example.scanner.data.repo.MainRepoImpl
import com.example.scanner.domain.repo.MainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent:: class)
abstract class RepoModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMainRepo(
        mainRepoImpl: MainRepoImpl
    ):MainRepo
}