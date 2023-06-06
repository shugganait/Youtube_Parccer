package lib.kg.youtubeparccer.ui


import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import lib.kg.youtubeparccer.base.BaseActivity
import lib.kg.youtubeparccer.databinding.ActivityPlaylistBinding
import lib.kg.youtubeparccer.ui.MainActivity.Companion.KEY_FOR_ID
import lib.kg.youtubeparccer.utils.ConnectionLiveData

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {
    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): PlaylistViewModel {
        return ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it){
                binding.clNoInternet.isVisible = false
                binding.llMainLayout.isVisible = true
            } else {
                binding.clNoInternet.isVisible = true
                binding.llMainLayout.isVisible = false
            }
        }
    }

    override fun setUI() {
        super.setUI()
        Toast.makeText(this, intent.getStringExtra(KEY_FOR_ID), Toast.LENGTH_SHORT).show()
    }


}