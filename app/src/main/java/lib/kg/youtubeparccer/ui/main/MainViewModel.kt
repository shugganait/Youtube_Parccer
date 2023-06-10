package lib.kg.youtubeparccer.ui.main

import androidx.lifecycle.LiveData
import lib.kg.youtubeparccer.App.Companion.repository
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseViewModel
import lib.kg.youtubeparccer.data.remote.model.Playlists

class MainViewModel: BaseViewModel(){
    fun getPlaylist(): LiveData<Resource<Playlists>> {
        return repository.getPlaylist()
    }
}