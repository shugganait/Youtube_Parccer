package lib.kg.youtubeparccer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lib.kg.youtubeparccer.BuildConfig
import lib.kg.youtubeparccer.model.Playlists
import lib.kg.youtubeparccer.remote.ApiService
import lib.kg.youtubeparccer.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel(){

    private val apiService: ApiService = RetrofitClient.create()

    fun getPlaylist(): LiveData<Playlists> {
        return playlists()
    }

    private fun playlists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylist(
            "snippet,contentDetails",
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            BuildConfig.API_KEY,
        ).enqueue(object: Callback<Playlists>{
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                print(t.stackTrace)
            }
        })

        return data
    }
}