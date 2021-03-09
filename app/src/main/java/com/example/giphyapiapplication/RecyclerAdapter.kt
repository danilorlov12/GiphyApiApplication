package com.example.giphyapiapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapiapplication.model.GifList

class RecyclerAdapter(private val values: GifList, private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun getItemCount() = values.gifs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
            .load(values.gifs[position].images.fixed_height_small.url)
            .asGif()
            .error(R.drawable.ic_launcher_background)
            .into(holder.gifImageView)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gifImageView: ImageView? = null

        init {
            gifImageView = itemView.findViewById(R.id.gifImageView)

        }

    }
}