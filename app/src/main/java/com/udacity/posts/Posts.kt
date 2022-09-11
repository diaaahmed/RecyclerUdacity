package com.udacity.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.udacity.click.Click
import com.udacity.model.PostModel
import com.udacity.recyclerudacity.databinding.FragmentPostsBinding
import kotlinx.coroutines.delay

class Posts : Fragment(), SwipeRefreshLayout.OnRefreshListener, Click {

    private val ui by lazy  {
        FragmentPostsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy  {
        PostsAdapter(this)
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
        ui.swipeRefresh.setOnRefreshListener(this)
        observe()
        viewModel.getPosts()

    }

    private fun setUpRecyclerView()
    {
        ui.postsRecycler.adapter = adapter
    }

    private fun observe(){
        viewModel.posts_list_livedata.observe(viewLifecycleOwner){
            adapter.addPosts(it)
        }
    }

    override fun onRefresh()
    {
        adapter.clear()
        lifecycleScope.launchWhenCreated {
            delay(2000)
            viewModel.getPosts()
            ui.swipeRefresh.isRefreshing = false

        }
    }

    override fun onClick(post: PostModel)
    {
        Toast.makeText(requireContext(), "" +
                "Name is ${post.author} and content is ${post.content}", Toast.LENGTH_SHORT).show()
    }

    override fun shareBtn(position: Int)
    {
        Toast.makeText(requireContext(), " Position is $position", Toast.LENGTH_SHORT).show()
    }
}