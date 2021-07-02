package github.sachin2dehury.chucknorris.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import github.sachin2dehury.chucknorris.adapters.JokeAdapter
import com.example.myapplication.databinding.FragmentListBinding
import github.sachin2dehury.chucknorris.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    var index = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListBinding.bind(view)

        subscribeToObservers()
        viewModel.getJokes()

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val index =
            (binding.list.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        outState.putInt("index", index)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun subscribeToObservers() {
        viewModel.jokes.observe(viewLifecycleOwner, {
            it?.let {
                binding.list.adapter = JokeAdapter(it)
                binding.list.layoutManager = LinearLayoutManager(context)
                binding.list.layoutManager?.scrollToPosition(index)
            }
        })
    }
}