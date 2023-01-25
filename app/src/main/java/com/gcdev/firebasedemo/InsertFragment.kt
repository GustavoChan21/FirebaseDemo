package com.gcdev.firebasedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertFragment : Fragment(R.layout.fragment_insert) {

    private lateinit var etUserName: EditText
    private lateinit var etUserLastName: EditText
    private lateinit var etUserAge: EditText
    private lateinit var etUserStatus: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //code of save data with the Button "Guardar Datos"

        var btnReturnMenu = requireView().findViewById<Button>(R.id.button_returnMenu)

        btnReturnMenu.setOnClickListener{
            findNavController().navigate(R.id.action_insertFragment_to_optionsFragment)
        }

        etUserName = requireView().findViewById(R.id.editTextName)
        etUserLastName = requireView().findViewById(R.id.editTextLastName)
        etUserAge = requireView().findViewById(R.id.editTextAge)
        etUserStatus = requireView().findViewById(R.id.editTextStatus)

        btnSaveData = requireView().findViewById(R.id.button_saveData)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        btnSaveData.setOnClickListener{
            saveUserData()
        }
    }
    private fun saveUserData() {
        //getting values of the form and save on the intern's variables
        val userName = etUserName.text.toString()
        val userLastName = etUserLastName.text.toString()
        val userAge = etUserAge.text.toString()
        val userStatus = etUserStatus.text.toString()

        if (userName.isEmpty()){
            etUserName.error = "Por favor agregue un nombre"
        }
        if (userLastName.isEmpty()){
            etUserLastName.error = "Por favor agregue un apellido"
        }
        if (userAge.isEmpty()){
            etUserAge.error = "Por favor agregue la edad"
        }
        if (userStatus.isEmpty()){
            etUserStatus.error = "Por favor agregue su estado civil"
        }

        val userID = dbRef.push().key!!

        val user = UserModel(userID, userName, userLastName, userAge, userStatus)

        dbRef.child(userID).setValue(user)
            .addOnCompleteListener{

                etUserName.text.clear()
                etUserLastName.text.clear()
                etUserAge.text.clear()
                etUserStatus.text.clear()
            }.addOnFailureListener{

            }

    }


}