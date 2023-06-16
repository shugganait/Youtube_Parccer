package lib.kg.youtubeparccer.ui.details

import androidx.lifecycle.LiveData
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseViewModel
import lib.kg.youtubeparccer.data.remote.model.Playlists
import lib.kg.youtubeparccer.repository.Repository

class DetailsViewModel(private val repository: Repository): BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<Playlists>> {
        return repository.getPlaylistItems(playlistId)
    }
}