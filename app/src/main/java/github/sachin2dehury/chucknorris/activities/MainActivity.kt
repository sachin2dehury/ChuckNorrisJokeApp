package github.sachin2dehury.chucknorris.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.listFragment -> navController.navigate(R.id.action_to_listFragment)
                R.id.jokeFragment -> navController.navigate(R.id.action_to_jokeFragment)
            }
            true
        }
    }
}