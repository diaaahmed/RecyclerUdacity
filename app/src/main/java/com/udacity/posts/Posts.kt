package com.udacity.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.udacity.recyclerudacity.R
import com.udacity.recyclerudacity.databinding.FragmentPostsBinding

class Posts : Fragment() {

    private val ui by lazy  {
        FragmentPostsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy  {
        PostsAdapter()
    }

    private val viewModel by lazy  {
        ViewModelProvider(requireActivity())[PostsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observe()
        viewModel.getPosts()

    }

    private fun setUpRecyclerView()
    {
        ui.postsRecycler.adapter = adapter
    }

    private fun observe(){
        viewModel.posts_live_data.observe(viewLifecycleOwner){
            adapter.addPosts(it)
        }
    }
}