package ipca.am1.sergio.stopwatcham1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private var running : Boolean = false
    private var increment = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        sa_time.progress = 0
        sa_time.isEnabled = false



        btn_time.setOnClickListener(this)

    }

    override fun onClick(view: View) {
            when (view.id) {
                    R.id.btn_time -> startStopwatch()
                   //R.id.btn_pause -> pauseStopwatch()
                   // R.id.btn_lap_reset -> lapOrResetStopWatch()

            }
    }

    private fun startStopwatch() {
        btn_lap_reset.isEnabled = true
        btn_lap_reset.isEnabled = true
        btn_lap_reset.text = resources.getString(R.string.lap)

        if (!running){
            running = true
            CoroutineScope(IO).launch {
                while(running){
                    delay(10)

                    val second = (increment / 90.0) % 60
                    val progress = (second / 60) * 100

                    withContext(Main){
                        sa_time.progress = progress.toInt()
                        btn_time.text = Lap.convertToDuration(increment)
                    }
                    increment+= 1
                }

            }
        }
    }


}