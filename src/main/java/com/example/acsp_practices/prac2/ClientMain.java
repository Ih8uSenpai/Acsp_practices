package com.example.acsp_practices.prac2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {
    public static final String UNIQUE_BINDING_NAME = "equation.solver";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(4321);
        EquationSolver equationSolver = (EquationSolver) registry.lookup(UNIQUE_BINDING_NAME);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String equation = scanner.nextLine();
            System.out.println(equationSolver.getRootsOfTheQuadraticEquation(equation));
        }
    }
}
