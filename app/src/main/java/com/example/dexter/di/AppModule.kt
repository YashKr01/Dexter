package com.example.dexter.di

import android.content.Context
import androidx.room.Room
import com.example.dexter.api.ApiInterface
import com.example.dexter.database.JobDao
import com.example.dexter.database.JobDatabase
import com.example.dexter.utils.Constants.Companion.BASE_URL
import com.example.dexter.utils.Constants.Companion.DATABASE_NAME
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): JobDatabase =
        Room.databaseBuilder(context, JobDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDao(jobDatabase: JobDatabase): JobDao = jobDatabase.jobDao()

}