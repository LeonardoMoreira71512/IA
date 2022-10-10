import java.util.*;

public class BestFirst {
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;
    private State actual;
    private Ilayout objective;

    static class State {
        private final Ilayout layout; //config = 4 2\n357\n698
        private final State father; //father
        private final double g; //cost

        public State(Ilayout l, State n) {
            layout = l;
            father = n;

            if (father!=null)
                g = father.g + l.getG(); //
            else g = 0.0f;
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
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        objective = goal; //obj = 123405678
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG()-s2.getG()));
        List<State> fechados = new ArrayList<>();
        abertos.add(new State(s, null)); //insere o estado inicial na lista dos abertos, s = config, null pq n tem pai
        List<State> sucs;
        // TO BE COMPLETED
        while(true) {
            if(abertos.isEmpty()) {
                System.exit(1);
            }

            State actual = abertos.poll(); //poll removes the head of the queue or return null if queue is empty
            abertos.remove(actual);

            if(actual.layout.isGoal(goal)) {
                List<State> solutions = new ArrayList<>();
                State temp = actual;
                for(; temp.father != null; temp = temp.father) {
                    solutions.add(0, temp);
                }
                solutions.add(0, temp);
                return solutions.iterator();
            }
            else {
                sucs = this.sucessores(actual);
                fechados.add(actual);
                for(State successor : sucs) {
                    if(!fechados.contains(successor)) {
                        abertos.add(successor);
                    }
                }
            }
        }
    }

}

