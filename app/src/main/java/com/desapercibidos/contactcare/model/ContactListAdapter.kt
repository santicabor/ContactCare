package com.desapercibidos.contactcare.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.desapercibidos.contactcare.R
import com.desapercibidos.contactcare.databinding.ContactItemBinding
import com.desapercibidos.contactcare.view.ContactClickListener

class ContactListAdapter (private val contactList: ArrayList<Contacto>):
    RecyclerView.Adapter<ContactListAdapter.ContactsViewHolder>(),
    ContactClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ContactItemBinding>(inflater, R.layout.contact_item, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.view.contacto = contactList[position]
        holder.view.listenClic = this
    }

    override fun getItemCount() = contactList.size


    override fun onClick(v: View) {
        TODO("Not yet implemented")
    }

    class ContactsViewHolder (var view: ContactItemBinding) : RecyclerView.ViewHolder (view.root)
}