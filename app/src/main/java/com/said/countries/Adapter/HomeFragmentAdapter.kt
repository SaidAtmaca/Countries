package com.said.countries.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.said.countries.Fragments.HomeFragmentDirections
import com.said.countries.R
import com.said.countries.Retrofit.Model
import com.said.countries.databinding.RecyclerviewRowBinding

class HomeFragmentAdapter(private val countryList: Model) :
    RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerviewRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val sharedPreferences: SharedPreferences =holder.itemView.context.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        /** Checks the starposition of the item */
        val starPosition = sharedPreferences.getString(countryList.data.get(position).code, "false")
        editor.apply()

        holder.binding.countryName.text = countryList.data.get(position).name
        if (starPosition == "ok") {
            holder.binding.starImageView.setImageResource(R.drawable.ic_baseline_star_24_black)
            editor.apply()
        }
        holder.binding.starImageView.setOnClickListener {
            if (starPosition == "false") {
                holder.binding.starImageView.setImageResource(R.drawable.ic_baseline_star_24_black)
                editor.putString(countryList.data.get(position).code, "ok")
                editor.apply()
            } else if (starPosition == "ok") {
                holder.binding.starImageView.setImageResource(R.drawable.ic_baseline_star_24_grey)
                editor.putString(countryList.data.get(position).code, "false")
                editor.apply()
            }
        }
        /** Goes to detail page when the user clicks on it  */
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(countryList.data.get(position).code, starPosition)
            Navigation.findNavController(it).navigate(action)
        }

    }
        override fun getItemCount(): Int { return countryList.data.size }
    inner class MyViewHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
}