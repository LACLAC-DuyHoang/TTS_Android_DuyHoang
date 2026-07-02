package com.example.task3.Repository


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // khi bất kì class nào yêu cầu ProductRepository, hãy đưa vào ProductRepositoryImpl
    @Binds
    @Singleton // trong toàn bộ app, Hilt chỉ dùng một instance của repository này
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}