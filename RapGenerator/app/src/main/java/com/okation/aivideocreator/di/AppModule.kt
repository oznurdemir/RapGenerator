package com.okation.aivideocreator.di

import android.content.Context
import androidx.room.Room
import com.okation.aivideocreator.data.datasource.RapDataSource
import com.okation.aivideocreator.data.repo.RapRepository
import com.okation.aivideocreator.data.room.SongDatabase
import com.okation.aivideocreator.network.ApiClient
import com.okation.aivideocreator.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }
    @Provides
    @Singleton
    fun providesRapDataSource(context: Context, rapApiService: ApiService, songDatabase: SongDatabase): RapDataSource {
        return RapDataSource(context, rapApiService, songDatabase)
    }
    @Provides
    @Singleton
    fun providesKisilerRepository(rapDataSource: RapDataSource): RapRepository {
        return RapRepository(rapDataSource)
    }
    @Provides
    @Singleton
    fun providesBeatApiService(): ApiService {
        return ApiClient.getClientBeat()
    }
    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): SongDatabase =
        Room.databaseBuilder(
            context,
            SongDatabase::class.java,
            "song_db"
        ).build()
}
