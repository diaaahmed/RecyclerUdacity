package com.udacity.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.model.PostModel

class PostsViewModel: ViewModel()
{
    var posts_live_data = MutableLiveData<MutableList<PostModel>>()

    val posts_list_livedata: LiveData<MutableList<PostModel>> get() = posts_live_data

    fun getPosts()
    {
        val fake_posts = mutableListOf<PostModel>(
            PostModel("","Diaa","This is first content"),
            PostModel("","Ahmed","This is second content"),
            PostModel("","Mohamed","This is third content"),
            PostModel("","Ali","This is fourth content"),
            PostModel("","Khaled","This is fifth content"),
            PostModel("","Diaa","This is first content"),
            PostModel("","Diaa","This is first content"),
            PostModel("","Diaa","This is first content")
        )

        posts_live_data.postValue(fake_posts)
    }

}