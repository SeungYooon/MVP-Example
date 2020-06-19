package com.example.MvpExample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.MvpExample.R
import com.example.MvpExample.viewmodel.ContactViewModel
import com.example.MvpExample.model.Contact
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    companion object {
        const val CONTACT_NAME = "CONTACT_NAME"
        const val CONTACT_NUMBER = "CONTACT_NUMBER"
        const val CONTACT_ID = "CONTACT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        if (intent != null && intent.hasExtra(CONTACT_NAME) && intent.hasExtra(CONTACT_NUMBER) && intent.hasExtra(
                CONTACT_ID
            )
        ) {
            add_edittext_name.setText(intent.getStringExtra(CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(CONTACT_NUMBER))
            id = intent.getLongExtra(CONTACT_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString().trim()
            val number = add_edittext_number.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "please enter name and nubmer", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase()
                val contact = Contact(id, name, number, initial)
                contactViewModel.insert(contact)
                finish()
            }
        }
    }
}
