package lib.kg.youtubeparccer.ui.playlist

import androidx.lifecycle.LiveData
import lib.kg.youtubeparccer.App
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseViewModel
import lib.kg.youtubeparccer.data.remote.model.Playlists

class PlaylistViewModel: BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<Playlists>> {
        return App.repository.getPlaylistItems(playlistId)
    }
}