package com.example.nytplayground.common.di

import com.example.data.common.di.DataModuleBinds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [DataModuleBinds::class])
interface ApplicationModuleBinds {

}