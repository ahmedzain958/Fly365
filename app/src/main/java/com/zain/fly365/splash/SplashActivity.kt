package com.zain.fly365.splash

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import com.zain.fly365.flightsearch.presentation.ui.FlightSearchActivity
import com.zain.fly365.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    val ANIMATION_DURATION: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Start animation.
        startAnimation()
    }
    private fun startAnimation() {
        // Intro animation configuration.
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            textTitle.scaleX = value
            textTitle.scaleY = value
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = ANIMATION_DURATION

        // Set animator listener.
        val intent = Intent(this, FlightSearchActivity::class.java)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                // Navigate to FlightSearchActivity on navigation end.
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}

        })
        // Start animation.
        valueAnimator.start()
    }

}
