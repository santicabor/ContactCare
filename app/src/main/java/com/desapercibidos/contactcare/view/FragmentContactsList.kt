package com.desapercibidos.contactcare.view

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.desapercibidos.contactcare.R
import com.desapercibidos.contactcare.model.ContactListAdapter
import com.desapercibidos.contactcare.model.Contacto
import com.desapercibidos.contactcare.model.contactoList
import kotlinx.android.synthetic.main.fragment_contacts_list.*


class FragmentContactsList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContactos.adapter = ContactListAdapter(contactoList!!)
        rvContactos.layoutManager = LinearLayoutManager(context)
        //rvContactos.setHasFixedSize(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
