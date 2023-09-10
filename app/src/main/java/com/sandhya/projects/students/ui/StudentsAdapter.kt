package com.sandhya.projects.students.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sandhya.projects.R
import com.sandhya.projects.databinding.FragmentStaffListItemBinding
import com.sandhya.projects.students.data.StudentsData
import javax.inject.Inject

class StudentsAdapter @Inject constructor() : RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    var studentsList = mutableListOf<StudentsData>()
    var onItemClick: ((StudentsData) -> Unit)? = null

    fun setStudents(students: List<StudentsData>) {
        this.studentsList = students.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val binding = FragmentStaffListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        val studentItem = studentsList[position]
        holder.bind(studentItem)
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    inner class StudentsViewHolder(private val binding: FragmentStaffListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(studentsData: StudentsData) {

            Glide.with(itemView)
                .load(studentsData.image)
                .placeholder(R.drawable.default_image)
                .fitCenter()
                .into(binding.imageView)

            binding.tvName.text = studentsData.name
            binding.tvActorName.text = studentsData.actor
            binding.tvDob.text = studentsData.dateOfBirth

            itemView.setOnClickListener {
                onItemClick?.invoke(studentsData)
            }
        }
    }
}
