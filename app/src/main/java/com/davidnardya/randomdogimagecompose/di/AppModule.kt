package com.davidnardya.randomdogimagecompose.di

import com.davidnardya.randomdogimagecompose.api.DogApi
import com.davidnardya.randomdogimagecompose.api.RetrofitInstance
import com.davidnardya.randomdogimagecompose.repositories.DogRepository
import com.davidnardya.randomdogimagecompose.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMainViewModel(dogRepository: DogRepository) = MainViewModel(dogRepository)

    @Singleton
    @Provides
    fun provideDogRepository(dogApi: DogApi) = DogRepository(dogApi)

    @Singleton
    @Provides
    fun provideRetrofitInstance() = RetrofitInstance

    @Singleton
    @Provides
    fun provideDogService(retrofitInstance: RetrofitInstance): DogApi =
        retrofitInstance.create(DogApi::class.java)
}