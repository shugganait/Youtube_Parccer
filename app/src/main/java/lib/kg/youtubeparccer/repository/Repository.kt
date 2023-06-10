package lib.kg.youtubeparccer.repository

import android.view.animation.Transformation
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import lib.kg.youtubeparccer.BuildConfig.API_KEY
import lib.kg.youtubeparccer.core.network.RetrofitClient
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.utils.channelId
import lib.kg.youtubeparccer.core.utils.part
import lib.kg.youtubeparccer.data.remote.ApiService
import lib.kg.youtubeparccer.data.remote.RemoteDataSource
import lib.kg.youtubeparccer.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService = RetrofitClient.create()
    private val remoteDataSource = RemoteDataSource(apiService)

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



    //Прошу не обращать внимания на этот метод,
    // это нужно чтобы доставать длительности видео, пусть стоит пока не найду другой путь
    fun getVideoForDuration(videoId: String): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getVideoForDuration(
            part,
            API_KEY,
            videoId
        ).enqueue(object: Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                Resource.error(t.message.toString(), null)
            }
        })

        return data
    }
    //СТАРЫЕ МЕТОДЫ ЗАКОММЕНТИРОВАЛ ПРОСТО ТАК ЧТОБЫ БЫЛИ
//    fun getPlaylist(): LiveData<Resource<Playlists>> {
//        val data = MutableLiveData<Resource<Playlists>>()
//
//        data.value = Resource.loading()
//        apiService.getPlaylist(
//            part,
//            channelId,
//            API_KEY,
//        ).enqueue(object: Callback<Playlists> {
//            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
//                if (response.isSuccessful) {
//                    data.value = Resource.success(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<Playlists>, t: Throwable) {
//                Resource.error(t.message.toString(), null)
//            }
//        })
//
//        return data
//    }
//    fun getPlaylistItems(playlistId: String): LiveData<Resource<Playlists>> {
//        val data = MutableLiveData<Resource<Playlists>>()
//
//        data.value = Resource.loading()
//        apiService.getPlaylistItems(
//            part,
//            API_KEY,
//            playlistId
//        ).enqueue(object: Callback<Playlists> {
//            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
//                if (response.isSuccessful) {
//                    data.value = Resource.success(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<Playlists>, t: Throwable) {
//                Resource.error(t.message.toString(), null)
//            }
//        })
//
//        return data
//    }
//
//    fun getVideo(videoId: String): LiveData<Resource<Playlists>> {
//        val data = MutableLiveData<Resource<Playlists>>()
//
//        data.value = Resource.loading()
//        apiService.getVideo(
//            part,
//            API_KEY,
//            videoId
//        ).enqueue(object: Callback<Playlists> {
//            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
//                data.value = Resource.success(response.body())
//            }
//
//            override fun onFailure(call: Call<Playlists>, t: Throwable) {
//                Resource.error(t.message.toString(), null)
//            }
//        })
//
//        return data
//    }
}