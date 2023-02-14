package octopus.inc.trafficdevilsakhtemov.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import octopus.inc.data.repository.IpApiRepositoryImpl
import octopus.inc.domain.usecases.GetDnsResponseUseCase
import octopus.inc.domain.usecases.GetJsonByQueryResponseUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetDnsResponseUseCase(ipApiRepositoryImpl: IpApiRepositoryImpl) =
        GetDnsResponseUseCase(ipApiRepositoryImpl)

    @Provides
    fun provideGetJsonByQueryResponseUseCase(ipApiRepositoryImpl: IpApiRepositoryImpl) =
        GetJsonByQueryResponseUseCase(ipApiRepositoryImpl)
}