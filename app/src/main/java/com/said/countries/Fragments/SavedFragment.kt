package com.said.countries.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.said.countries.Adapter.SavedFragmentAdapter
import com.said.countries.R
import com.said.countries.Retrofit.*
import com.said.countries.databinding.FragmentSavedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private val BASE_URL: String ="https://wft-geo-db.p.rapidapi.com/v1/geo/"
    private lateinit var sendList: ArrayList<SavedCountryModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container,false)

        /** Creates a new RetrofitBuilder to get data over the Interface */
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(SavedCountryInterface::class.java)

        val retrofitData=retrofitBuilder.getCountry()

        retrofitData.enqueue(object : Callback<SavedModel?> {
            override fun onResponse(call: Call<SavedModel?>, response: Response<SavedModel?>) {
                val responseBody= response.body()!!

                val sharedPreferences: SharedPreferences =context!!.getSharedPreferences("myPref",
                    Context.MODE_PRIVATE)
                val editor=sharedPreferences.edit()

                /** Checks items and if the item is favourite , adds it to the favourite list */
                    sendList= ArrayList<SavedCountryModel>()
                    for (i in 0..responseBody.data.size-1){

                        val starPosition=sharedPreferences.getString(responseBody.data.get(i).code,"false")
                        if (starPosition=="ok"){
                            sendList.add(responseBody.data.get(i))
                        }

                    }
                /** Connects adapter with recyclerview */
                    val recyclerView = binding.savedPageRecyclerView
                    recyclerView.layoutManager= LinearLayoutManager(context)
                    val savedFragmentAdapter= SavedFragmentAdapter(sendList)
                    recyclerView.adapter=savedFragmentAdapter


            }

            override fun onFailure(call: Call<SavedModel?>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return binding.root
    }



}