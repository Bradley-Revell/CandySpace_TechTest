package com.example.candyspace_tech

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.candyspace_tech.model.User

class RecyclerAdapter_Users (val context: Context, var userData: Array<User>) : RecyclerView.Adapter<usersViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): usersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_user, parent, false)

        return usersViewHolder(view).listen { position, type ->
            //When a row is selected it will open the next fragment
            view.setOnClickListener ( Navigation.createNavigateOnClickListener(R.id.action_FirstFragment_to_SecondFragment))
            view.performClick()
        }
    }


    override fun getItemCount(): Int {
        return userData.size
    }

    private lateinit var viewModel: MainViewModel

    override fun onBindViewHolder(holder: usersViewHolder, row: Int){

        Log.e("DATA", userData.get(row).toString())
        val userData = userData.get(row)

        holder.caseName.text = userData.display_name
        //USING THE LIBRARY 'PICASSO' you can pass the display into


    }


}

class usersViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val caseName = view.findViewById<TextView>(R.id.rv_textView_name)
//    val avatar = view.findViewById<ImageView>(R.id.rv_imageView_avatar)

}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}