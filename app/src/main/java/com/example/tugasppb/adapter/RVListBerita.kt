package com.example.tugasppb.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasppb.page_berita.BeritaDetail
import com.example.tugasppb.R
import com.example.tugasppb.model.ListBerita
import com.example.tugasppb.network.RetrofitClient.BASE_URL
import com.example.tugasppb.page_berita.Berita
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_berita.view.*


class RVListBerita(
    val context: Context,
    private val myDataset: ArrayList<ListBerita>
) :
    RecyclerView.Adapter<RVListBerita.MyViewHolder>() {
    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(item: ListBerita, position: Int) {
            view.title.text = item.title
            view.desc.text = item.desc
            view.date.text = item.date
            Picasso.get().load(BASE_URL + "images/" + item.image).into(view.image);
            view.setOnClickListener {
                val page = Intent(
                    context,
                    BeritaDetail::class.java
                )
                page.putExtra("data", item)
                page.putExtra("position", position)
                (context as Activity).startActivityForResult(page, Berita.PICK_UPDATE_RESULT)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_berita, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bindData(myDataset[position], position)

    override fun getItemCount() = myDataset.size

    fun removeItem(position: Int) {
        myDataset.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: ListBerita, position: Int) {
        myDataset.add(item)
        notifyItemInserted(position)
    }

    fun addItem(item: ListBerita) {
        myDataset.add(item)
        notifyDataSetChanged()
    }

    fun changeItem(item: ListBerita, position: Int) {
        myDataset[position] = item
        notifyItemChanged(position)
    }
}

