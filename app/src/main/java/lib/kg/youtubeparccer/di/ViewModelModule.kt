package lib.kg.youtubeparccer.di

import lib.kg.youtubeparccer.ui.details.DetailsViewModel
import lib.kg.youtubeparccer.ui.player.PlayerViewModel
import lib.kg.youtubeparccer.ui.playlist.PlaylistViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    factory { DetailsViewModel(repository = get()) }
    factory { PlayerViewModel(repository = get()) }
    factory { PlaylistViewModel(repository = get()) }
}
