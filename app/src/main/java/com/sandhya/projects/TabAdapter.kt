package com.sandhya.projects

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sandhya.projects.characters.ui.CharactersFragment
import com.sandhya.projects.staff.ui.StaffFragment
import com.sandhya.projects.students.ui.StudentsFragment

class TabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StaffFragment()
            1 -> StudentsFragment()
            2 -> CharactersFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}
