package com.mytestapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mytestapp.R
import com.mytestapp.activity.AddUserActivity
import com.mytestapp.activity.EditUserActivity
import com.mytestapp.databinding.ItemUserBinding
import com.mytestapp.interfaces.DeleteInterface
import com.mytestapp.model.User
import com.mytestapp.roomdb.UserDatabase

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private var list: List<User>? = null
    private var activity: Activity? = null
    private var appDatabase: UserDatabase? = null
    private var dInterface: DeleteInterface? = null

    constructor(
        context: Activity?,
        list: List<User>?,
        delinterface: DeleteInterface?,

        ) {
        this.activity = context
        this.list = list
        appDatabase = this.activity?.let { UserDatabase.getInstance(it) }
        this.dInterface = delinterface
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user, parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val model = list?.get(position)
        holder.mBinding.txtNameValue.text = model!!.name
        holder.mBinding.txtMblValue.text = model.mobile
        holder.mBinding.txtBookValue.text = model.bookValue

        holder.mBinding.llDelete.setOnClickListener {

            callDeletePopup(model, activity)

        }

        holder.mBinding.llEdit.setOnClickListener {

            var intent= Intent(activity,EditUserActivity::class.java)
            intent.putExtra(
                "id",model.id)
            activity!!.startActivity(intent)
        }

    }

    private fun callDeletePopup(model: User, activity: Activity?) {
        val builder = AlertDialog.Builder(activity!!)
        //set title for alert dialog
        builder.setTitle("Delete")
        //set message for alert dialog
        builder.setMessage("Are you sure to delete this user?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            dInterface!!.deleteUser(model)
        }

        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->

            dialogInterface.dismiss()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(var mBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}
