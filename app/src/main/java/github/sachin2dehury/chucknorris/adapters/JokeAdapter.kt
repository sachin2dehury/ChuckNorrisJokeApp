package github.sachin2dehury.chucknorris.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import github.sachin2dehury.chucknorris.api.remote.Joke
import com.example.myapplication.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class JokeAdapter(private val list: List<Joke>) :
    RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val binding = ListItemBinding.bind(holder.itemView)
        binding.textView.text = list[position].value
        Picasso.get().load(list[position].icon_url).into(binding.imageView)
    }

    override fun getItemCount() = list.size
}