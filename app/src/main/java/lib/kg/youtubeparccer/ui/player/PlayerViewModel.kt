package lib.kg.youtubeparccer.ui.player

import androidx.lifecycle.LiveData
import lib.kg.youtubeparccer.App
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseViewModel
import lib.kg.youtubeparccer.data.remote.model.Playlists

class PlayerViewModel: BaseViewModel() {
    fun getVideo(videoId: String): LiveData<Resource<Playlists>> {
        return App.repository.getVideo(videoId)
    }
}