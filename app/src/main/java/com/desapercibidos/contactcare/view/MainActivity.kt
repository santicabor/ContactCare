package com.desapercibidos.contactcare.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.desapercibidos.contactcare.R
import com.desapercibidos.contactcare.model.Contacto
import com.desapercibidos.contactcare.model.contactoList
import org.jetbrains.annotations.Contract

class MainActivity : AppCompatActivity() {

    private val READ_CONTACT_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
           != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Es necesario  permiso para leer tus contactos", Toast.LENGTH_SHORT).show()
            requestContactsPermission()
        } else {
            //Permission has already been granted
            Toast.makeText(this, "YA ESTÁ CONCEDIDO", Toast.LENGTH_SHORT).show()
        }

        getContactsData()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            READ_CONTACT_PERMISSION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "Permiso CONCEDIDO", Toast.LENGTH_SHORT).show()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Volver a PEDIR", Toast.LENGTH_SHORT).show()
                }
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun requestContactsPermission() {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this, "La app no te será útil si no puede leer tus contactos.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.READ_CONTACTS), READ_CONTACT_PERMISSION_CODE)
                } else {
                    // No explanation needed, we can request the permission.
                    Toast.makeText(this, "Llamada requestPermission", Toast.LENGTH_SHORT).show()
                    ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.READ_CONTACTS), READ_CONTACT_PERMISSION_CODE)
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
    }

    fun getContactsData() {
        lateinit var contact: Contacto
        val contactList = ArrayList<Contacto>()
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )

        val cursor2 = contentResolver.query(
            ContactsContract.Data.CONTENT_URI,
            null, null, null, null
        )

        ContactsContract.CommonDataKinds.Phone.NUMBER


        if (cursor == null) {
            Toast.makeText(this, "Sin contactos", Toast.LENGTH_LONG).show()
        } else {
            //cursor.moveToFirst()
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                var foto = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
                    //cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO))
                val nombre =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    //cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME))
                val telefon =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    //cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                var email =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_STATUS))
                    //cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))


                if (foto == null) foto = "@drawable/ic_person"
                if (email == null) email = "contacto@porejemplo.com"
                contact = Contacto(id, foto, nombre, telefon, email)
                contactList.add(contact)
            }
        }
        contactoList = contactList
    }
}
