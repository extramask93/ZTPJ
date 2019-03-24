package com.company.NetworkConnectors;

import Model.IPracownikList;

public interface INetworkConnector {
    void connect(String ip, int port);
    void authenticate(String login, String password);
    boolean needAuthentication();
    IPracownikList obtainEmployees();
}
