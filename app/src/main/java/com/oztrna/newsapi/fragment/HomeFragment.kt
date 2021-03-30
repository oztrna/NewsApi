package com.oztrna.newsapi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.oztrna.newsapi.DetailActivity
import com.oztrna.newsapi.R
import com.oztrna.newsapi.adapter.CustomAdapter
import com.oztrna.newsapi.model.Article
import com.oztrna.newsapi.model.News
import com.oztrna.newsapi.network.MyApi
import com.oztrna.newsapi.viewmodel.NewsViewModel
import com.oztrna.newsapi.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment: Fragment(), KodeinAware {

    override val kodein by kodein()

    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search?.setOnClickListener {
            btn_next?.visibility = View.VISIBLE
            btn_prev?.visibility = View.INVISIBLE
            gam()
        }

        btn_next?.setOnClickListener {
            page++
            if (page > 1) { btn_prev?.visibility = View.VISIBLE }
            gam()
        }

        btn_prev?.setOnClickListener {
            page--
            if (page == 1) { btn_prev?.visibility = View.INVISIBLE }
            gam()
        }

    }

    private fun gam() {
        val gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        val apis: MyApi = retrofit.create(MyApi::class.java)
        val call: Call<News> = apis.getNews(edit_search_name.text.toString(), page, "d54b713cc2fd47f4ba077861cf517268")
        call.enqueue(object : Callback<News?> {
            override fun onResponse(
                call: Call<News?>?,
                response: Response<News?>
            ) {
                val adapter = CustomAdapter(context!!, response.body()?.articles!!, object: CustomAdapter.OnRowClickListener {
                    override fun onRowClicked(article: Article) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra("article", article)
                        activity!!.startActivity(intent)
                    }
                })
                recycler_news?.layoutManager = LinearLayoutManager(context!!)
                recycler_news?.adapter = adapter
            }

            override fun onFailure(call: Call<News?>?, t: Throwable) { }
        })
    }
}