package lib.kg.youtubeparccer.ui

import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import lib.kg.youtubeparccer.base.BaseActivity
import lib.kg.youtubeparccer.databinding.ActivityMainBinding
import lib.kg.youtubeparccer.model.Item
import lib.kg.youtubeparccer.model.Playlists
import lib.kg.youtubeparccer.ui.adapters.PlayListAdapter
import lib.kg.youtubeparccer.utils.ConnectionLiveData


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var adapter: PlayListAdapter

    override fun setUI() {
        super.setUI()
        adapter = PlayListAdapter(this::onClick)
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.getPlaylist().observe(this) {
            binding.rvPlaylist.adapter = adapter
            adapter.addList(it.items)
        }
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

    private fun onClick(item: Item) {
        val intent = Intent(this, PlaylistActivity::class.java)
        intent.putExtra(KEY_FOR_ID, item.id)
        startActivity(intent)
    }

    companion object {
        const val KEY_FOR_ID = "FADFA"
    }
}