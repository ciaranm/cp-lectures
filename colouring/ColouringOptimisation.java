/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import java.util.*;
import java.io.*;
import static choco.Choco.*;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.solver.Solver;
import choco.kernel.model.variables.integer.IntegerVariable;

public class ColouringOptimisation
{
    Model model;
    Solver solver;
    IntegerVariable[] colour; // colour[v] = k means vertex v has colour k
    IntegerVariable nColours;
    int nVertices;

    ColouringOptimisation(String fname) throws IOException
    {
        model      = new CPModel();
        solver     = new CPSolver();
        try (Scanner sc = new Scanner(new File(fname))) {
            nVertices = sc.nextInt();
            nColours  = makeIntVar("nColours", 0, nVertices);
            colour    = makeIntVarArray("colour", nVertices, 0, nVertices);
            while (sc.hasNext()) {
                int v = sc.nextInt() - 1;
                int w = sc.nextInt() - 1;
                // adjacent vertices can't have the same colour
                model.addConstraint(neq(colour[v], colour[w]));
            }

            for (IntegerVariable c : colour)
                model.addConstraint(lt(c, nColours));
        }
        solver.read(model);
    }

    int solve()
    {
        solver.minimize(solver.getVar(nColours), false);
        return solver.getVar(nColours).getVal();
    }

    long getTimeTaken() { return solver.getTimeCount(); }

    public static void main(String[] args) throws IOException
    {
        ColouringOptimisation co = new ColouringOptimisation(args[0]);
        System.out.println(co.solve());
        System.out.println(co.getTimeTaken());
    }
}

