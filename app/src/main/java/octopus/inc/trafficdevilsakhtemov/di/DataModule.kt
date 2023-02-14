package octopus.inc.trafficdevilsakhtemov.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import octopus.inc.data.api.ApiService
import octopus.inc.data.repository.IpApiRepositoryImpl
import octopus.inc.data.utils.EndPoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideBaseUrl() = EndPoints.BASE_URL

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .client(provideOkHttpClient())
        .build()
        .create(ApiService::class.java)

    @Provides
    fun provideIpApiRepositoryImpl() = IpApiRepositoryImpl(provideApiService())
}