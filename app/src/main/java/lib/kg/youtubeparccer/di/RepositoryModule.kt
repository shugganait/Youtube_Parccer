package lib.kg.youtubeparccer.di

import lib.kg.youtubeparccer.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module{
    single { Repository(remoteDataSource = get()) }
}