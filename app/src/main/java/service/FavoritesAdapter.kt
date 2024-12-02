package service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shinedown.R
import model.ShineLessonModel

class FavoritesAdapter(
    private val context: Context,
    private val favoritesList: List<ShineLessonModel>,
    private val onLongClickListener: (ShineLessonModel) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false)
        return FavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val shine = favoritesList[position]
        holder.bind(shine)

        // Adiciona o Listener para clique longo
        holder.itemView.setOnLongClickListener {
            onLongClickListener(shine) // Chama a função lambda que lida com a remoção
            true
        }
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.favorite_item_title)
        private val valueTextView: TextView = itemView.findViewById(R.id.favorite_item_value)

        fun bind(shine: ShineLessonModel) {
            titleTextView.text = shine.title
            valueTextView.text = "R$ ${shine.value}"
        }
    }
}

