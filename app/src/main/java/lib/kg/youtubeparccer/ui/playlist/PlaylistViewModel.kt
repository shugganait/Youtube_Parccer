package lib.kg.youtubeparccer.ui.playlist

import androidx.lifecycle.LiveData
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseViewModel
import lib.kg.youtubeparccer.data.remote.model.Playlists
import lib.kg.youtubeparccer.repository.Repository

class PlaylistViewModel(private val repository: Repository): BaseViewModel(){
    fun getPlaylist(): LiveData<Resource<Playlists>> {
        return repository.getPlaylist()
    }
}