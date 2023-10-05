package Abdelkoudous.AnimatedBottle

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var btn: FloatingActionButton
    private lateinit var whiteBkgrnd: ImageView
    private var handler: Handler = Handler()
    private var runnable: Runnable? = null
    private val delayMillis: Long = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsById()

        btn.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startFillingTheBottle()
                        return false
                    }

                    MotionEvent.ACTION_UP -> {
                        stopFillingTheBottle()
                        return false
                    }

                }
                return false
            }
        })


    }

    //what is really happening is that when the button is clicked the whiteLayer the covers the blue filled bottle is going up by decreasing the top margin value
    private fun startFillingTheBottle() {
        runnable = object : Runnable {
            override fun run() {
                val params = whiteBkgrnd.layoutParams as ViewGroup.MarginLayoutParams
                var marginTop = params.topMargin - 3
                params.topMargin = marginTop
                whiteBkgrnd.layoutParams = params
                handler.postDelayed(this, delayMillis)
            }
        }
        handler.post(runnable!!)
    }

    private fun stopFillingTheBottle() {
        if (runnable != null) {
            handler.removeCallbacks(runnable!!)
            runnable = null
        }
    }


    private fun findViewsById() {
        btn = findViewById(R.id.btn)
        whiteBkgrnd = findViewById(R.id.whiteBg)
    }
}