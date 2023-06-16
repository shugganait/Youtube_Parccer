package lib.kg.youtubeparccer.ui.player

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.exoplayer2.*
import lib.kg.youtubeparccer.core.results.Resource
import lib.kg.youtubeparccer.core.ui.BaseActivity
import lib.kg.youtubeparccer.core.utils.ConnectionLiveData
import lib.kg.youtubeparccer.databinding.ActivityPlayerBinding
import lib.kg.youtubeparccer.databinding.DownloadAlertDialogBinding
import lib.kg.youtubeparccer.ui.details.DetailsActivity.Companion.KEY_FOR_VIDEOID
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>(), Player.Listener {

    private lateinit var dialogBinding: DownloadAlertDialogBinding
    override val viewModel: PlayerViewModel by viewModel()

    private fun inflateDialogBinding() {
        dialogBinding = DownloadAlertDialogBinding.inflate(layoutInflater)
    }

    override fun inflateViewBinding(): ActivityPlayerBinding {
        inflateDialogBinding()
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        intent.getStringExtra(KEY_FOR_VIDEOID)?.let { videoId ->
            viewModel.getVideo(videoId).observe(this) {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        with(binding) {
                            tvTitle.text = it.data?.items!![0].snippet.title
                            tvDescription.text = it.data.items[0].snippet.description
                            binding.webView.loadUrl("https://www.youtube.com/embed/${it.data.items[0].id}")
                        }
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
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setUI() {
        super.setUI()
        binding.webView.settings.javaScriptEnabled = true
    }

    override fun initClickListener() {
        super.initClickListener()
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnDownload.setOnClickListener {
            showDownloadDialog()
        }
    }

    private fun showDownloadDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)

        val dialog = dialogBuilder.create()

        val parentView = dialogBinding.root.parent as? ViewGroup
        parentView?.removeView(dialogBinding.root)

        dialogBinding.btnDownload.setOnClickListener {
            val selectedQuality = when (dialogBinding.radioGroup.checkedRadioButtonId) {
                dialogBinding.radioButton480.id -> "480p"
                dialogBinding.radioButton720.id -> "720p"
                dialogBinding.radioButton1080.id -> "1080p"
                else -> "null"
            }
            Toast.makeText(
                this,
                "Downloading video\nwith quality: $selectedQuality",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()

        }

        dialog.show()
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
}