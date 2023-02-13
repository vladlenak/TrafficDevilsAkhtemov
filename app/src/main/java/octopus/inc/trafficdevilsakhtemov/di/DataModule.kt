package octopus.inc.trafficdevilsakhtemov.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import octopus.inc.data.api.DnsApi
import octopus.inc.data.repository.DnsRepositoryImpl
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
    @Singleton
    fun provideDnsApi(): DnsApi = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()
        .create(DnsApi::class.java)

    @Provides
    fun provideDnsRepositoryImpl() = DnsRepositoryImpl(provideDnsApi())
}