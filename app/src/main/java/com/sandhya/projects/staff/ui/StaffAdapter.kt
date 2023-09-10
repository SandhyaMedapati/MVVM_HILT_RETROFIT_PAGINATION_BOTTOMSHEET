package com.sandhya.projects.staff.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sandhya.projects.R
import com.sandhya.projects.databinding.FragmentStaffListItemBinding
import com.sandhya.projects.staff.data.StaffData
import javax.inject.Inject

class StaffAdapter @Inject constructor() : RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {

    var staffList = mutableListOf<StaffData>()
    var onItemClick: ((StaffData) -> Unit)? = null

    fun setStaff(staff: List<StaffData>) {
        this.staffList = staff.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val binding = FragmentStaffListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StaffViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val staffItem = staffList[position]
        holder.bind(staffItem)
    }

    override fun getItemCount(): Int {
        return staffList.size
    }

    inner class StaffViewHolder(private val binding: FragmentStaffListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(staffData: StaffData) {

            Glide.with(itemView)
                .load(staffData.image)
                .placeholder(R.drawable.default_image)
                .fitCenter()
                .into(binding.imageView)

            binding.tvName.text = staffData.name
            binding.tvActorName.text = staffData.actor
            binding.tvDob.text = staffData.dateOfBirth

            itemView.setOnClickListener {
                onItemClick?.invoke(staffData)
            }
        }
    }
}
