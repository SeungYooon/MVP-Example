package com.example.MvpExample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.MvpExample.base.ContactRepository
import com.example.MvpExample.model.Contact

// destroy 경우 메모리 릭 방지 위해 인자로 context 대신 application
class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}