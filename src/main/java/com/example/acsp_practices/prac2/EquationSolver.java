package com.example.acsp_practices.prac2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EquationSolver extends Remote {
    List<Double> getRootsOfTheQuadraticEquation(String equation) throws RemoteException;
}
