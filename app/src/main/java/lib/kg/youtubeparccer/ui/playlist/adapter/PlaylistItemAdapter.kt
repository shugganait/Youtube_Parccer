package lib.kg.youtubeparccer.ui.playlist.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import lib.kg.youtubeparccer.data.remote.model.Item
import lib.kg.youtubeparccer.databinding.ItemPlaylistItemBinding
import lib.kg.youtubeparccer.repository.Repository
import lib.kg.youtubeparccer.utils.loadImage

class PlaylistItemAdapter(private val onClick: (Item) -> Unit, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<PlaylistItemAdapter.PlaylistItemViewHolder>() {
    private var list = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        val binding = ItemPlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistItemViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(newList: ArrayList<Item>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class PlaylistItemViewHolder(private val binding: ItemPlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Item) {
            with(binding) {
                tvTitle.text = item.snippet.title
                Repository().getVideoForDuration(item.contentDetails.videoId).observe(lifecycleOwner) {
                    val arrayList = it.items as ArrayList<Item>
                    val duration = convertDuration(arrayList[0].contentDetails.duration)
                    tvDuration.text = duration
                }
                imgPreview.loadImage(item.snippet.thumbnails.high.url)
                itemView.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDuration(durationString: String): String {
        val pattern = "PT(\\d+)M(\\d+)S".toRegex()
        val matchResult = pattern.find(durationString)

        val minutes = matchResult?.groupValues?.get(1)?.toIntOrNull() ?: 0
        val seconds = matchResult?.groupValues?.get(2)?.toIntOrNull() ?: 0

        return String.format("%02d:%02d", minutes, seconds)
    }
}