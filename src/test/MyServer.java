package test;


public class MyServer {

	ClientHandler clientHandler;
    int port;

    public MyServer(ClientHandler clientHandler, int port)
    {

        this.clientHandler = clientHandler;
        this.port = port;

    }


}
