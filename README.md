
# SocketIoSample
## _socket.io with coroutines, lifecycles ktx, viewModels_

>We can Inject socket in viewModel by constructor injection.
>We're already implemented HILT in our project.
>Initialization of Socket will be done by HILT or by Singleton **_SocketHandler_** as I wrote in this repo.
>If you want to inject Socket, just provides the following variable in your **_Hilt Module_** 

    @Provides
    @Singleton
    val socket : Socket by lazy {
        try {
            socket = IO.socket("http://192.168.100.241:8000") 
        } catch (e: URISyntaxException) {
            throw URISyntaxException(e.message, "Wrong URI")
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }



    
