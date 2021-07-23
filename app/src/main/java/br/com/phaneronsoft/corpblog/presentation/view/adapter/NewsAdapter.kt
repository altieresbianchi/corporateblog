package br.com.phaneronsoft.corpblog.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.phaneronsoft.corpblog.R
import br.com.phaneronsoft.corpblog.business.vo.NewsVO
import br.com.phaneronsoft.corpblog.databinding.RecyclerItemNewsBinding
import br.com.phaneronsoft.corpblog.utils.Util
import com.squareup.picasso.Picasso

class NewsAdapter(
    private var items: MutableList<NewsVO>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(RecyclerItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        (holder as NewsViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newItems: MutableList<NewsVO>) {
        items = newItems
        notifyDataSetChanged()
    }
}

class NewsViewHolder(
    private val binding: RecyclerItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NewsVO) {
        Picasso.get()
            .load(item.user.profilePicture)
            .placeholder(R.drawable.ic_account_circle_outline)
            .error(R.drawable.ic_account_circle_outline)
            .into(binding.circleImageAvatar)

        binding.textViewDate.text = Util.dateSqlToBPtBr(item.message.createdAt)
        binding.textViewNews.text = item.message.content
        binding.textViewUser.text = item.user.name
    }
}

