package com.oztrna.newsapi.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.orm.SugarContext
import com.orm.SugarRecord
import com.oztrna.newsapi.DetailActivity
import com.oztrna.newsapi.R
import com.oztrna.newsapi.adapter.CustomAdapter
import com.oztrna.newsapi.model.Article
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SugarContext.init(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articles: List<Article> = SugarRecord.listAll(Article::class.java)
        Log.e("Test", articles.toString())

        context?.let {
            recycler_favorite?.layoutManager = LinearLayoutManager(it)
            recycler_favorite?.adapter = CustomAdapter(it, articles, object: CustomAdapter.OnRowClickListener {
                override fun onRowClicked(article: Article) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("article", article)
                    activity!!.startActivity(intent)
                }
            })
        }
    }
}