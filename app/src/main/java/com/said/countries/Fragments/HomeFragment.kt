package com.said.countries.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.said.countries.Adapter.HomeFragmentAdapter
import com.said.countries.R
import com.said.countries.Retrofit.CountryInterface
import com.said.countries.Retrofit.Model
import com.said.countries.databinding.FragmentHomeBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val BASE_URL: String ="https://wft-geo-db.p.rapidapi.com/v1/geo/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        /** Creates a new RetrofitBuilder to get data over the Interface */
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CountryInterface::class.java)

        val retrofitData=retrofitBuilder.getCountry()

        retrofitData.enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                val responseBody= response.body()!!

                /** Connects adapter with recyclerview */
                val recyclerView = binding.homePageRecyclerView
                recyclerView.layoutManager=LinearLayoutManager(context)
                val homeFragmentAdapter= HomeFragmentAdapter(responseBody)
                recyclerView.adapter=homeFragmentAdapter
            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                t.printStackTrace()
            }
        })







        return binding.root
    }

}