package com.example.acsp_practices.prac2;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static final String UNIQUE_BINDING_NAME = "equation.solver";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        EquationSolver equationSolver = new EquationSolverImpl();
        Registry registry = LocateRegistry.createRegistry(4321);
        Remote stub = UnicastRemoteObject.exportObject(equationSolver, 0);
        registry.bind(UNIQUE_BINDING_NAME, stub);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
