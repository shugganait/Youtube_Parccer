package lib.kg.youtubeparccer.ui.details.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import lib.kg.youtubeparccer.data.remote.model.Item
import lib.kg.youtubeparccer.databinding.ItemPlaylistItemBinding
import lib.kg.youtubeparccer.utils.loadImage

class DetailsAdapter(
    private val onClick: (Item) -> Unit,
) : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    private var list = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding =
            ItemPlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
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

    inner class DetailsViewHolder(private val binding: ItemPlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Item) {
            with(binding) {
                tvTitle.text = item.snippet.title
                tvDuration.text = "null(("
                try {
                    imgPreview.loadImage(item.snippet.thumbnails.high.url)
                }catch (e: java.lang.NullPointerException) {
                    Log.e("shug", "bind: ${e.message}", )
                }

                itemView.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}