package lib.kg.youtubeparccer.repository

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.data.remote.RemoteDataSource
import lib.kg.youtubeparccer.data.remote.model.Playlists

class Repository(private val remoteDataSource: RemoteDataSource) {
    fun getPlaylist(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getPlaylist()
        emit(response)
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = remoteDataSource.getPlaylistItems(playlistId)
        emit(response)
    }

    fun getVideo(videoId: String): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = remoteDataSource.getVideo(videoId)
        emit(response)
    }

}