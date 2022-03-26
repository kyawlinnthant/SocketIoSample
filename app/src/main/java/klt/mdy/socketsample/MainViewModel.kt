package klt.mdy.socketsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val socket: Socket by lazy {
        SocketHandler.startSocket()
    }.apply {
        SocketHandler.launchConnection(this.value)
    }
    private val _socketData: MutableStateFlow<Int> = MutableStateFlow(-1)
    val socketData: StateFlow<Int> get() = _socketData

    override fun onCleared() {
        super.onCleared()
        SocketHandler.endConnection(socket)
    }

    fun receiveData() {
        viewModelScope.launch {
            socket.on("eventName") { args ->
                if (args[0] != null) {
                    val dataFromSocket = args[0] as Int //This will be your data to operate
                    _socketData.value = dataFromSocket
                }
            }
        }
    }

    fun sendData(data: Int) {
        viewModelScope.launch {
            socket.emit(
                "This is event",
                data //this will be your data to send
            )
        }
    }
}