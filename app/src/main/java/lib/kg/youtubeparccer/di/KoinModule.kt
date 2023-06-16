package lib.kg.youtubeparccer.di

import lib.kg.youtubeparccer.core.network.networkModule

val koinModules = listOf(
    repositoryModule,
    networkModule,
    viewModelModule,

)