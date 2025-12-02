package com.joser;

import com.joser.application.ports.input.RouterNetworkInputPort;
import com.joser.application.ports.output.RouterNetworkOutputPort;
import com.joser.application.usecases.RouterNetworkUseCase;
import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.framework.adapters.input.RouterNetworkAdapter;
import com.joser.framework.adapters.input.rest.RouterNetworkRestAdapter;
import com.joser.framework.adapters.input.stdin.RouterNetworkCliAdapter;
import com.joser.domain.valueobject.RouterId;
import com.joser.framework.adapters.output.file.RouterNetworkFileAdapter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class Main {

    RouterNetworkAdapter inputAdapter;
    RouterNetworkUseCase useCase;
    RouterNetworkOutputPort outputPort;

    public static void main(String[] args) {
        var adapter = "cli";
        if (args.length > 0) {
            adapter = args[0];
        }
        new Main().setAdapter(adapter);
    }

    void setAdapter(String adapter) {
        switch (adapter) {
            /*case "rest":
                outputPort = RouterNetworkH2Adapter.getInstance();
                useCase = new RouterNetworkInputPort(outputPort);
                inputAdapter = new RouterNetworkRestAdapter(useCase);
                rest();
                break;*/
            default:
                outputPort = RouterNetworkFileAdapter.getInstance();
                useCase = new RouterNetworkInputPort(outputPort);
                inputAdapter = new RouterNetworkCliAdapter(useCase);
                cli();
        }
    }

    private void cli() {
        Scanner scanner = new Scanner(System.in);
        inputAdapter.processRequest(scanner);
    }

    private void rest() {
     try {
        System.out.println("REST endpoint listening on port 8080");
        var httpserver = HttpServer.create(new InetSocketAddress(8080), 0 );
        inputAdapter.processRequest(httpserver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}