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
            .setSize(size, size)
            .build()

        binding.imageView.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val viewPosition = IntArray(2)
                    v.getLocationOnScreen(viewPosition)
                    val x = event.rawX - viewPosition[0]
                    val y = event.rawY - viewPosition[1]
                    magnifier.show(x, y, x, y - size / 2)
                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    magnifier.dismiss()
                }
            }

            true
        }
    }

    companion object {

        const val size = 600
    }
}
