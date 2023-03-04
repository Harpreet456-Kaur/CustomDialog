package com.example.customdialog

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.UpdateLayout
import android.widget.ArrayAdapter
import com.example.customdialog.databinding.ActivityMainBinding
import com.example.customdialog.databinding.AddLayoutBinding
import com.example.customdialog.databinding.EditLayoutBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ArrayAdapter<String>
    var userList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
        binding.lvList.adapter = adapter
        binding.floatingBtn.setOnClickListener {
            val dialogbinding = AddLayoutBinding.inflate(layoutInflater)
            val customDialog = Dialog(this)
            customDialog.setContentView(dialogbinding.root)

            dialogbinding.btnOk.setOnClickListener {
                if (dialogbinding.etName.text.isEmpty()) {
                    dialogbinding.etName.error = "Enter text"
                } else {
                    userList.add(dialogbinding.etName.text.toString())
                    customDialog.dismiss()
                    adapter.notifyDataSetChanged()
                }
            }
            customDialog.show()
        }
        binding.lvList.setOnItemClickListener { adapterView, view, i, l ->
            val customDialog = Dialog(this@MainActivity)
            val dialogboxbinding = EditLayoutBinding.inflate(layoutInflater)
            customDialog.setContentView(dialogboxbinding.root)

            dialogboxbinding.btnEdit.setOnClickListener {
                if (dialogboxbinding.etName.text.isEmpty()) {
                    dialogboxbinding.etName.error = "Enter text"
                } else {
                    userList.set(i,dialogboxbinding.etName.text.toString())
                    customDialog.dismiss()
                    adapter.notifyDataSetChanged()
                }
            }
            dialogboxbinding.btnDelete.setOnClickListener {
                if (dialogboxbinding.etName.text.isEmpty()) {
                    dialogboxbinding.etName.error = "Can't delete"
                } else {
                    userList.removeAt(i)
                    customDialog.dismiss()
                    adapter.notifyDataSetChanged()
                }
            }
            customDialog.show()
        }
    }
}