import java.util.*;

public class BestFirst {
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;
    private State actual;
    private Ilayout objective;

    static class State {
        private Ilayout layout; //config
        private State father; //
        private double g; //cost

        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father!=null)
                g = father.g + l.getG(); //
            else g = 0.0;
        }
        public String toString() {
            return layout.toString();
        }

        public double getG() {
            return g;
        }


        public int hashCode() {
            return toString().hashCode();
        }

        public boolean equals (Object o) {
            if (o==null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;

            return this.layout.equals(n.layout);

        }
    }
    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e: children) {
            if (n.father == null || !e.equals(n.father.layout)){
                State nn = new State(e, n);
                sucs.add(nn);
                sucs.remove(10)
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        objective = goal; //obj = 123405678
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG()-s2.getG()));
        fechados = new HashMap<>();
        abertos.add(new State(s, null)); //insere o estado inicial na lista dos abertos
        List<State> sucs;
        // TO BE COMPLETED
        return null;
    }
}