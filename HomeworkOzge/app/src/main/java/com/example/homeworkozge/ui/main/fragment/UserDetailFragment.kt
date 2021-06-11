package com.example.homeworkozge.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.homeworkozge.R
import com.example.homeworkozge.showDataNullDialog
import com.example.homeworkozge.ui.main.MainActivity
import com.example.homeworkozge.ui.main.fragment.UserDetailFragmentArgs.fromBundle
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment : Fragment() {

    val variableName by lazy {
        fromBundle(requireArguments()).userItem
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (variableName!=null){
            Glide.with(requireContext()).load(variableName?.avatar.toString()).into(imgAvatar)
            txtId.text = variableName?.id.toString()
            txtName.text = variableName?.name.toString()
            txtEmail.text = variableName?.email.toString()
        } else {
            (requireActivity() as MainActivity).showDataNullDialog()
        }


    }
}