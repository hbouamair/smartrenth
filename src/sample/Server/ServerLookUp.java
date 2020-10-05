package sample.Server;

import sample.Connection.Helper;
import sample.Models.Vehicule;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLookUp {

    ServerSocket serverSocket;

    OnVehiculeSyncronized onVehiculeSyncronized;

    public void setOnVehiculeSyncronized(OnVehiculeSyncronized onVehiculeSyncronized) {
        this.onVehiculeSyncronized = onVehiculeSyncronized;
    }

    public ServerLookUp() {

        try {
            serverSocket = new ServerSocket(Helper.PORT);

            Thread threadReceaver = new Thread(() -> {

                try {
                    while (true) {
                        Socket socket = serverSocket.accept();

                        Thread thread = new Thread(() -> lookupFor(socket));

                        thread.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadReceaver.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void lookupFor(Socket socket) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Vehicule v = (Vehicule) objectInputStream.readObject();
                System.out.println(v.getPosition());
                    //
                if (onVehiculeSyncronized != null)
                    onVehiculeSyncronized.receved(v);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public  interface OnVehiculeSyncronized {
        void receved(Vehicule vehicule);

    }


}
