package com.shegami.ai.voice.aigptassistant.di

import android.content.Context
import com.shegami.ai.voice.aigptassistant.common.Constants
import com.shegami.ai.voice.aigptassistant.data.remote.OpenAiApi
import com.shegami.ai.voice.aigptassistant.data.repository.OpenAiRepositoryImpl
import com.shegami.ai.voice.aigptassistant.domain.repository.OpenAiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenAiApi(): OpenAiApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAiApi::class.java)

    }

    @Provides
    @Singleton
    fun provideOpenAiRepository(api: OpenAiApi): OpenAiRepository {
        return OpenAiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}