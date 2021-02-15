package org.mightyfrog.android.magnifiersample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Magnifier
import androidx.appcompat.app.AppCompatActivity
import org.mightyfrog.android.magnifiersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val magnifier = Magnifier.Builder(binding.imageView)
            .setCornerRadius(300f)
            .setElevation(20f)
            .setInitialZoom(4f)
            .setSize(600, 600)
            .build()

        binding.imageView.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val viewPosition = IntArray(2)
                    v.getLocationOnScreen(viewPosition)
                    magnifier.show(event.rawX - viewPosition[0], event.rawY - viewPosition[1])
                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    magnifier.dismiss()
                }
            }

            true
        }
    }
}