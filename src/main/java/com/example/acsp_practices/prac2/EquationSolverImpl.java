package com.example.acsp_practices.prac2;

import java.rmi.RemoteException;
import java.util.List;

public class EquationSolverImpl implements EquationSolver {

    @Override
    public List<Double> getRootsOfTheQuadraticEquation(String equation) throws RemoteException {
        List<Double> coefficients = QuadraticEquationParseUtil.parseEquation(equation);
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);
        double d = b * b - 4.0 * a * c;

        if (a == 0 || d < 0) {
            return List.of();
        }

        if (d > 0.0) {
            double root1 = (-b + Math.sqrt(d)) / (2.0 * a);
            double root2 = (-b - Math.sqrt(d)) / (2.0 * a);
            return List.of(root1, root2);
        } else {
            double root1 = -b / (2.0 * a);
            return List.of(root1);
        }
    }
}
