package com.oztrna.newsapi.model

import com.orm.SugarRecord
import java.io.Serializable

class Article : SugarRecord, Serializable {

    var author: String? = null
    var content: String? = null
    var description: String? = null
    var publishedAt: String? = null
    var source: Source? = null
    var title: String? = null
    var url: String? = null
    var urlToImage: String? = null

    fun Article() {

    }

    constructor(
            author: String?,
            content: String?,
            description: String?,
            publishedAt: String?,
            source: Source?,
            title: String?,
            url: String?,
            urlToImage: String?
    ) {
        this.author = author
        this.content = content
        this.description = description
        this.publishedAt = publishedAt
        this.source = source
        this.title = title
        this.url = url
        this.urlToImage = urlToImage
    }

    constructor() {}

}