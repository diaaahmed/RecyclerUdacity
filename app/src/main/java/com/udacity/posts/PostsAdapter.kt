package com.udacity.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.click.Click
import com.udacity.model.PostModel
import com.udacity.recyclerudacity.databinding.PostItemLayoutBinding

class PostsAdapter(var click: Click): RecyclerView.Adapter<PostsAdapter.ViewHolder>()
{
    private var postsList = mutableListOf<PostModel>()

    inner class ViewHolder(private var postItemBinding:PostItemLayoutBinding):
        RecyclerView.ViewHolder(postItemBinding.root)
    {
            fun bind(item:PostModel) = with(postItemBinding)
            {

                authorText.text = item.author
                contentText.text = item.content

                likeBtn.setOnClickListener{
                    click.shareBtn(adapterPosition)

                }

                authorText.setOnClickListener{
                    click.onClick(item)
                }

//                authorText.setOnClickListener {
//                    Toast.makeText(context, "is"+item.author, Toast.LENGTH_SHORT).show()
//                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(
            PostItemLayoutBinding.inflate
                (LayoutInflater.from(parent.context),
        parent,
                false))
    }

    override fun getItemCount(): Int = postsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(postsList[position])
    }


    fun addPosts(posts: MutableList<PostModel>)
    {
        postsList.addAll(posts)
        notifyDataSetChanged()
    }

    fun clear()
    {
        postsList.clear()
        notifyDataSetChanged()
    }

}