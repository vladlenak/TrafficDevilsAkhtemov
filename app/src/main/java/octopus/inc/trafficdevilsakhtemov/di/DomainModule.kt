package octopus.inc.trafficdevilsakhtemov.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import octopus.inc.data.repository.DnsRepositoryImpl
import octopus.inc.domain.usecases.GetDnsResponseUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetDnsResponseUseCase(dnsRepositoryImpl: DnsRepositoryImpl) =
        GetDnsResponseUseCase(dnsRepositoryImpl)
}