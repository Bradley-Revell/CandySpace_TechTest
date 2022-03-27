package com.example.candyspace_tech

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.candyspace_tech.model.Session_User
import com.example.candyspace_tech.model.User

class RecyclerAdapter_Users(var userData: Array<User>) : RecyclerView.Adapter<usersViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): usersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_user, parent, false)

        return usersViewHolder(view).listen { position, type ->
            //When a row is selected it will open the next fragment
            Session_User.userData = userData[position]
            view.setOnClickListener ( Navigation.createNavigateOnClickListener(R.id.action_FirstFragment_to_SecondFragment))
            view.performClick()
        }
    }


    override fun getItemCount(): Int {
        return userData.size
    }


    override fun onBindViewHolder(holder: usersViewHolder, row: Int){


        val userData = userData.get(row)

        //Apply the users name to the recycler view row
        holder.userName.text = userData.display_name


    }


}

class usersViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val userName = view.findViewById<TextView>(R.id.rv_textView_name)
//    val avatar = view.findViewById<ImageView>(R.id.rv_imageView_avatar)

}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}