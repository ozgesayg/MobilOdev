package com.example.homeworkozge.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkozge.R
import com.example.homeworkozge.data.adapter.viewholder.UserViewHolder
import com.example.homeworkozge.data.model.User
import com.example.homeworkozge.data.repo.MainRepo
import com.example.homeworkozge.showDataNullDialog
import com.example.homeworkozge.toast
import com.example.homeworkozge.ui.main.MainActivity
import com.example.homeworkozge.ui.main.MainVmProviderFactory
import com.example.homeworkozge.ui.main.vm.MainViewModel
import com.example.homeworkozge.util.Resource
import java.util.ArrayList

class UserListFragment : Fragment() {

    private lateinit var ulViewModel: MainViewModel
    private lateinit var adapter: RecyclerAdapter
    private lateinit var recyclerUser: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerUser = view.findViewById(R.id.recyclerViewUser)
        singleton()

        ulViewModel.userListResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    (requireActivity() as MainActivity).showLoadingView()
                }
                is Resource.Error -> {
                    (requireActivity() as MainActivity).hideLoadingView()
                    response.message?.let { message ->
                        (activity as MainActivity).toast(message,3000)
                    }
                }
                is Resource.Success -> {
                    (requireActivity() as MainActivity).hideLoadingView()
                    response.data?.let { userResponse ->
                        if (userResponse != null) {
                            val userList = userResponse.toList()
                            adapter.setAdapterList(userList)
                        } else {
                            (requireActivity() as MainActivity).showDataNullDialog()
                        }
                    }
                }
            }
        })

        adapter.itemOnClickListener {
            val action = UserListFragmentDirections.actionUserList2Detail(it as User)
            findNavController().navigate(action)
        }
    }

    fun singleton(){
        ulViewModel = ViewModelProvider(this, MainVmProviderFactory((activity as MainActivity).application, MainRepo())).get(
            MainViewModel::class.java)
        adapter = RecyclerAdapter()
        recyclerUser.adapter = adapter
        recyclerUser.layoutManager = LinearLayoutManager(activity)
        ulViewModel.getUserList()

    }

    inner class RecyclerAdapter: RecyclerView.Adapter<UserViewHolder>() {
        var listener: ((item:Any) -> Unit)? = null
        val items= ArrayList<Any>()

        fun itemOnClickListener(itemClickListener: ((Any) -> Unit)) {
            listener = itemClickListener
        }

        fun setAdapterList(elements:List<Any>){
            this.items.clear()
            this.items.addAll(elements)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
             UserViewHolder(parent,listener)

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val userItem = items[position]
            holder.bind(userItem as User, context!!)

        }

        override fun getItemCount() = items.size

    }

}