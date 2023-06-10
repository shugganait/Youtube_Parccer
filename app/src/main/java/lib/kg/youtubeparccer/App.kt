package lib.kg.youtubeparccer

import android.app.Application
import lib.kg.youtubeparccer.repository.Repository

class App: Application() {
    companion object {
        val repository = Repository()
    }
}