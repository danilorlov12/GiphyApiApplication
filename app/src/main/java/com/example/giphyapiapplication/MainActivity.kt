package com.example.giphyapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.giphyapiapplication.model.GifList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gif_images)
        val recyclerView: RecyclerView = findViewById(R.id.gifImagesRecyclerView)
        setUpRecyclerView(recyclerView)
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        val list: GifList? = loadGifs(recyclerView)
        val myAdapter = list?.let { RecyclerAdapter(it, this) }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = myAdapter
    }

    private fun loadGifs(recyclerView: RecyclerView) : GifList? {
        val destinationService = ServiceBuilder.buildService(CountryService::class.java)
        val requestCall = destinationService.getAffectedCountryList()
        var gifList : GifList? = null
        requestCall.enqueue(object : Callback<GifList> {
            override fun onResponse(call: Call<GifList>, response: Response<GifList>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    gifList = response.body()!!
                    Log.d("Response", "list size : ${gifList!!.gifs[0].images.fixed_height_small.url}")
                    recyclerView.apply {
                        setHasFixedSize(true)
                        adapter = RecyclerAdapter(response.body()!!, this@MainActivity)
                    }
                } else {
                    Log.d("else","Something went wrong ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GifList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT)
                    .show()
                Log.d("onFailure", "Something went wrong $t")
            }
        })
        return gifList
    }
}