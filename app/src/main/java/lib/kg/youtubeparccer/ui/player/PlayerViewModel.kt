package lib.kg.youtubeparccer.ui.player

import androidx.lifecycle.LiveData
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseViewModel
import lib.kg.youtubeparccer.data.remote.model.Playlists
import lib.kg.youtubeparccer.repository.Repository

class PlayerViewModel(private val repository: Repository): BaseViewModel() {
    fun getVideo(videoId: String): LiveData<Resource<Playlists>> {
        return repository.getVideo(videoId)
    }
}