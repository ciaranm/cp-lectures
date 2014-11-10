/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import java.util.*;
import java.io.*;
import static choco.Choco.*;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.solver.Solver;
import choco.kernel.model.variables.integer.IntegerVariable;

public class ColouringDecision
{
    Model model;
    Solver solver;
    IntegerVariable[] colour; // colour[v] = k means vertex v has colour k
    int nVertices;
    int nColours;

    ColouringDecision(String fname, int k) throws IOException
    {
        nColours   = k;
        model      = new CPModel();
        solver     = new CPSolver();
        try (Scanner sc = new Scanner(new File(fname))) {
            nVertices = sc.nextInt();
            colour    = makeIntVarArray("colour", nVertices, 0, k - 1);
            while (sc.hasNext()) {
                int v = sc.nextInt() - 1;
                int w = sc.nextInt() - 1;
                // adjacent vertices can't have the same colour
                model.addConstraint(neq(colour[v], colour[w]));
            }
        }
        solver.read(model);
    }

    boolean solve() { return solver.solve(); }

    long getTimeTaken() { return solver.getTimeCount(); }

    public static void main(String[] args) throws IOException
    {
        ColouringDecision cd = new ColouringDecision(args[0], Integer.parseInt(args[1]));
        System.out.println(cd.solve());
        System.out.println(cd.getTimeTaken());
    }
}

