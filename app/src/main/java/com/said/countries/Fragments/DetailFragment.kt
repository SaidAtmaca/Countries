package com.said.countries.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.navigation.Navigation
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.said.countries.R
import com.said.countries.Retrofit.CountryDetailInterface
import com.said.countries.Retrofit.DetailModel
import com.said.countries.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val BASE_URL: String ="https://wft-geo-db.p.rapidapi.com/v1/geo/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottom=requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottom.visibility=View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        val sharedPreferences: SharedPreferences =requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor= sharedPreferences.edit()
        editor.apply()


        arguments?.let {
            /** Takes the country code from previous page with bundle */
            val code=DetailFragmentArgs.fromBundle(it).code
            val starPosition = sharedPreferences.getString(code, "false")
            if(starPosition=="ok"){
                binding.starImageView.setImageResource(R.drawable.ic_baseline_star_32_black)
            }else{
                binding.starImageView.setImageResource(R.drawable.ic_baseline_star_32_grey)
            }

            binding.detailPageCountryCode.text="Country Code: "+ code


try {
    /** Creates a new RetrofitBuilder to get data over the Interface */
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(CountryDetailInterface::class.java)

    val retrofitData=retrofitBuilder.getCountryDetails(code)
    retrofitData.enqueue(object : Callback<DetailModel?> {
        override fun onResponse(call: Call<DetailModel?>, response: Response<DetailModel?>) {
            val responseBody =response.body()!!

            if (response.isSuccessful){
                /** Writes country code to the textView */
                binding.detailPageCountryName.text=responseBody.data.name
                val imageUrl=responseBody.data.flagImageUri
                val imageFrame=binding.imageFrame

                    /** Loads image with Coil Library. Because Coil Library has SVG Format support */
                    fun AppCompatImageView.loadSvg(url: String) {
                        val imageLoader = ImageLoader.Builder(this.context)
                            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
                            .build()
                        val request = ImageRequest.Builder(this.context)
                            .crossfade(true)
                            .crossfade(500)
                            .placeholder(R.drawable.loading)
                            .data(url)
                            .target(this)
                            .build()

                        imageLoader.enqueue(request)
                    }

                    imageFrame.loadSvg(imageUrl)

                /** Changes star position when the user clicks on it */
                binding.starImageView.setOnClickListener {
                    if (starPosition == "false") {
                        binding.starImageView.setImageResource(R.drawable.ic_baseline_star_32_black)
                        editor.putString(code, "ok")
                        editor.apply()
                    } else if (starPosition == "ok") {
                        binding.starImageView.setImageResource(R.drawable.ic_baseline_star_32_grey)
                        editor.putString(code, "false")
                        editor.apply()
                    }
                }



                /** Goes to Wiki Data Page of Choosen Country when the user clicks on it */
               binding.netPageButton.setOnClickListener {
                   val openUrl= Intent(android.content.Intent.ACTION_VIEW)
                   openUrl.data=Uri.parse("https://www.wikidata.org/wiki/${responseBody.data.wikiDatId}")
                   startActivity(openUrl)
               }
                /** Goes to the Homa Page when the user clicks on it */
                binding.backButton.setOnClickListener{
                    val action=DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                    Navigation.findNavController(it).navigate(action)

                    val bottom=requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottom.visibility=View.VISIBLE
                }


            }
            }


            override fun onFailure(call: Call<DetailModel?>, t: Throwable) { t.printStackTrace()}
    })
}catch(e:java.lang.Exception){  e.printStackTrace()}

        }
        return binding.root
}



}