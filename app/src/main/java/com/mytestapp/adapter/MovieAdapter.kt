package com.mytestapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mytestapp.R
import com.mytestapp.databinding.ItemMovieBinding
import com.mytestapp.model.MovieListModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter (

    private var list: ArrayList<MovieListModel>,

) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie, parent, false
            )
        )
    }

    override fun onBindViewHolder(
        vh: ViewHolder,
        position: Int
    ) {
        val model = list[position]
        vh.mBinding.textname.text = model.name
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(var mBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(mBinding.root)

}