package klt.mdy.socketsample

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val vm: MainViewModel by viewModels()
    private val btn: Button by lazy {
        this.findViewById(R.id.button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
        setListener()
        vm.receiveData()
    }

    private fun observe() {
        this.lifecycleScope.launchWhenCreated {
            vm.socketData.collectLatest {
                //this is the data in our activity
            }
        }
    }

    private fun setListener() {
        btn.setOnClickListener {
            vm.sendData(
                data = 1 //this will be your data
            )
        }
    }
}