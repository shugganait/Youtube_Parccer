package lib.kg.youtubeparccer.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lib.kg.youtubeparccer.data.remote.model.Item
import lib.kg.youtubeparccer.databinding.ItemPlaylistBinding
import lib.kg.youtubeparccer.utils.loadImage

class PlayListAdapter(private val onClick: (Item) -> Unit) : RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {

    private var list = ArrayList<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Item>) {
        this.list = list as ArrayList<Item>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            with(binding) {
                tvTitle.text = item.snippet.title
                tvCountVideos.text = item.contentDetails.itemCount.toString() + " video series"
                imgPreview.loadImage(item.snippet.thumbnails.high.url)
                itemView.setOnClickListener {
                    onClick.invoke(item)
                }

            }

        }
    }
}