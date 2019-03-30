package company.Controllers;


import company.Networking.INetworkConnector;

public class NetworkController {
    INetworkConnector connector = null;

    public INetworkConnector getConnector() {
        return connector;
    }

    public void setConnector(INetworkConnector connector) {
        this.connector = connector;
    }

    public NetworkController(INetworkConnector connector) {
        this.connector = connector;
    }
}

