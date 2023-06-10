package lib.kg.youtubeparccer.data.remote

import lib.kg.youtubeparccer.BuildConfig.API_KEY
import lib.kg.youtubeparccer.core.network.BaseDataSource
import lib.kg.youtubeparccer.core.utils.channelId
import lib.kg.youtubeparccer.core.utils.part

class RemoteDataSource(private val apiService: ApiService): BaseDataSource() {
    suspend fun getPlaylist() = getResult {
        apiService.getPlaylist(
            part,
            channelId,
            API_KEY
        )
    }

    suspend fun getPlaylistItems(playlistId: String) = getResult {
        apiService.getPlaylistItems(
            part,
            API_KEY,
            playlistId
        )
    }

    suspend fun getVideo(videoId: String) = getResult {
        apiService.getVideo(
            part,
            API_KEY,
            videoId
        )
    }
}