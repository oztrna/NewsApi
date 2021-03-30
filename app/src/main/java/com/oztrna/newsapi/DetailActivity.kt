package com.oztrna.newsapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import com.orm.SugarContext
import com.orm.SugarRecord
import com.oztrna.newsapi.model.Article
import kotlinx.android.synthetic.main.activity_detail.*




class DetailActivity: AppCompatActivity() {

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        SugarContext.init(this)

        article = intent?.extras?.get("article") as Article

        Glide.with(this).load(article!!.urlToImage).into(detail_image)

        detail_title_text?.text = article!!.title
        detail_title_author?.text = article!!.author
        detail_date?.text = article!!.publishedAt
        detail_description?.text = article!!.content

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_news_source?.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", article!!.url)
            this.startActivity(intent)
        }

        val bundle = Bundle()
        bundle.putString("author", article?.author)
        FirebaseAnalytics.getInstance(this).logEvent("author", bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.nav_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, article?.url)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            R.id.nav_favorites -> {
                val long = article!!.save()
                Toast.makeText(this, long.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return false
    }
}