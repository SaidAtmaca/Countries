package com.said.countries.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.said.countries.Fragments.SavedFragmentDirections
import com.said.countries.R
import com.said.countries.Retrofit.SavedCountryModel
import com.said.countries.databinding.RecyclerviewRowBinding

class SavedFragmentAdapter(private val savedCountryList: ArrayList<SavedCountryModel>):
    RecyclerView.Adapter<SavedFragmentAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=RecyclerviewRowBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sharedPreferences: SharedPreferences =holder.itemView.context.getSharedPreferences("myPref",
            Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        val starPosition=sharedPreferences.getString(savedCountryList.get(position).code,"false")

                holder.binding.countryName.text=savedCountryList.get(position).name
                holder.binding.starImageView.setImageResource(R.drawable.ic_baseline_star_24_black)

        /** Goes to detail page when the user clicks on it  */
        holder.itemView.setOnClickListener{
            val action = SavedFragmentDirections.actionSavedFragmentToDetailFragment(savedCountryList.get(position).code,starPosition)
            Navigation.findNavController(it).navigate(action)
        }
                /** Changes the star position, when the user clicks on it */
                holder.binding.starImageView.setOnClickListener {
                    holder.binding.starImageView.setImageResource(R.drawable.ic_baseline_star_24_grey)
                    editor.putString(savedCountryList.get(position).code,"false")
                    editor.clear()
                    editor.apply()
                    holder.itemView.visibility=View.GONE



            }


    }

    override fun getItemCount(): Int {
      return  savedCountryList.size
    }


}