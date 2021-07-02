package github.sachin2dehury.chucknorris.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import github.sachin2dehury.chucknorris.api.remote.Joke
import com.example.myapplication.databinding.FragmentJokeBinding
import github.sachin2dehury.chucknorris.viewmodels.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokeFragment : Fragment(R.layout.fragment_joke) {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentJokeBinding? = null
    private val binding: FragmentJokeBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentJokeBinding.bind(view)

        subscribeToObservers()

        if (savedInstanceState != null) {
            val joke = savedInstanceState.getParcelable<Joke>("joke")
            joke?.let {
                viewModel.postJoke(joke)
            }
        } else {
            getJoke()
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_to_listFragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.joke.value?.let {
            outState.putParcelable("joke", viewModel.joke.value)
        }
    }

    private fun subscribeToObservers() = viewModel.joke.observe(viewLifecycleOwner, {
        it?.let {
            Log.d("Joke", it.toString())
            binding.textView.text = it.value
            Picasso.get().load(it.icon_url).into(binding.imageView)
        }
    })

    private fun getJoke() = try {
        viewModel.getJoke()
    } catch (e: Exception) {
        binding.root.showSnackBar(e.message ?: "Some Error occurred!")
        Log.d("Joke", e.toString())
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}