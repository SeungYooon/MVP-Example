package com.example.rxjava.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxjava.R
import com.example.rxjava.adapter.ContactAdapter
import com.example.rxjava.viewmodel.ContactViewModel
import com.example.rxjava.model.Contact
import kotlinx.android.synthetic.main.activity_m_v_v_m.*

class MVVMActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_v_m)

        val adapter = ContactAdapter({ contact ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.CONTACT_NAME, contact.name)
            intent.putExtra(AddActivity.CONTACT_NUMBER, contact.number)
            intent.putExtra(AddActivity.CONTACT_ID, contact.id)
            startActivity(intent)
        }, { contact ->
            deleteDialog(contact)
        })
        main_recycleview.adapter = adapter
        main_recycleview.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        // observe -> 어느 액티비티 생명주기를 관찰할 것인지
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })

        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("아이템 삭제?")
            .setNegativeButton("no") { _, _ -> }
            .setPositiveButton("yes") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }
}
