package com.sandhya.projects

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val selectedDrawable =
            AppCompatResources.getDrawable(this@MainActivity, R.drawable.selected_tab_bg)
        val unselectedDrawable =
            AppCompatResources.getDrawable(this@MainActivity, R.drawable.unselected_tab_bg)

        val adapter = TabAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getStyledText("Staff")
                1 -> getStyledText("Student")
                2 -> getStyledText("Characters")
                else -> null
            }
        }.attach()

        tabLayout.getTabAt(0)?.view?.background = selectedDrawable

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.background = selectedDrawable
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.background = unselectedDrawable
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Empty
            }
        })
    }

    private fun getStyledText(text: String): SpannableString {
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}
