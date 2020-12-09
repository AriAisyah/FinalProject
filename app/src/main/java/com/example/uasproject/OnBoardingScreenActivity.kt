package com.example.uasproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_on_boarding_screen.*

class OnBoardingScreenActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        //Isi data nya
        listOf(
            IntroSlide(
                "Sunlight",
                "Sunlight is the light and energy that comes from the sun",
                R.drawable.ic_launcher_background
            ),

            IntroSlide(
                "Pay Online",
                "Electronic bill payment is a feature of online, mobile and telephone banking",
                R.drawable.ic_launcher_background
            ),

            IntroSlide(
                "Video Streaming",
                "Streaming media is multimedia that is constantly received by and presented to an end-user",
                R.drawable.ic_launcher_background
            )

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        //panggil adapter nya di viewpager
        viewPager.adapter = introSliderAdapter

        // menaruh indicator
        setUpIndicators()

        // menaruh indicator sesuai posisi nya
        setCurrentIndicators(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                setCurrentIndicators(position)
            }
        })

        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                viewPager.currentItem += 1

            } else {
                Intent(applicationContext, SignUpActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        btnSkip.setOnClickListener {
            Intent(applicationContext, SignUpActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }

    private fun setUpIndicators() {
        // indicator = nampung jumlah data
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            dots.addView(indicators[i])
        }

    }

    private fun setCurrentIndicators(index: Int) {
        //child count = buat ngitung widget nya
        val childCount = dots.childCount

        for (i in 0 until childCount) {
            val imageView = dots.get(i) as ImageView

            //jika di posisi
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }

        }
    }
}