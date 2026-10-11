/*
202419094
Nguyen Chinh Thai
*/

public interface INetworkable {
    String getIpAddress();

    boolean isConnected();

    void connect(String ipAddress);

    void disconnect();
}
