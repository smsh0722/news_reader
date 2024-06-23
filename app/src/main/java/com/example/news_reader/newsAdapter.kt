package com.example.news_reader

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class newsAdapter (val context: Context, val items: List<News> ):BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.item_news, null )

        val title = generatedView.findViewById<TextView>(R.id.textView_news_title)
        val pubTime = generatedView.findViewById<TextView>(R.id.textView_news_time)
        val description = generatedView.findViewById<TextView>(R.id.textView_news_description)

        val news = items[position]
        title.text = Html.fromHtml(news.title).toString()
        pubTime.text = news.pubDate
        description.text = Html.fromHtml(news.description).toString()

        return generatedView
    }

}