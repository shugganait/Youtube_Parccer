package lib.kg.youtubeparccer.ui.playlist

import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseActivity
import lib.kg.youtubeparccer.ui.details.DetailsActivity
import lib.kg.youtubeparccer.ui.playlist.adapter.PlayListAdapter
import lib.kg.youtubeparccer.core.utils.ConnectionLiveData
import lib.kg.youtubeparccer.data.remote.model.Item
import lib.kg.youtubeparccer.databinding.ActivityPlaylistBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    override val viewModel: PlaylistViewModel by viewModel()
    private lateinit var adapter: PlayListAdapter

    override fun setUI() {
        super.setUI()
        adapter = PlayListAdapter(this::onClick)
    }

    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        viewModel.getPlaylist().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.rvPlaylist.adapter = adapter
                    it.data?.let { it1 -> adapter.addList(it1.items) }
                    viewModel.loading.postValue(false)
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
                Resource.Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.clNoInternet.isVisible = false
                binding.llMainLayout.isVisible = true
            } else {
                binding.clNoInternet.isVisible = true
                binding.llMainLayout.isVisible = false
            }
        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(KEY_FOR_ID, item.id)
        intent.putExtra(KEY_FOR_TITLE, item.snippet.title)
        intent.putExtra(KEY_FOR_DESC, item.snippet.description)
        intent.putExtra(KEY_FOR_COUNT, item.contentDetails.itemCount.toString())
        startActivity(intent)
    }

    companion object {
        const val KEY_FOR_ID = "FADFA"
        const val KEY_FOR_TITLE = "dadawed"
        const val KEY_FOR_DESC = "awdawdaw"
        const val KEY_FOR_COUNT = "ded"
    }
}