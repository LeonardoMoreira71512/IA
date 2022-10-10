import java.util.ArrayList;
import java.util.List;

class Board implements Ilayout, Cloneable {
    private static final int[] tuplePos = {1, 0, 0, -1, -1, 0, 0, 1};
    private static final int dim=3; //dim of the board
    private final int board[][];
    private int X;
    private int Y;

    public Board(){
        this.board = new int[dim][dim];
    }

    //Constructor receiving a String that represents the board
    public Board(String str) throws IllegalStateException {
        if (str.length() != dim*dim) //size of str different than dim*dim then throw exception
            throw new IllegalStateException("Invalid arg in Board constructor");

        board = new int[dim][dim]; //initialize board
        int strPos=0;
        for(int i=0; i<dim; i++){
            for(int j=0; j<dim; j++){
                board[i][j] = Character.getNumericValue(str.charAt(strPos++)); //board will get initialized with the numeric value of each character of the string one by one

                if(board[i][j] == 0){ //if board is null, x = 0 and y = 0
                    this.X = j;
                    this.Y = i;
                }
            }
        }
    }

    //Constructor receiving a Board variable and copy it to the original board, which is an int[][] type variable
    public Board(Board b) {
        this.board = new Board().board;
        for(int i = 0; i < dim; i++) {
            System.arraycopy(b.board[i], 0, this.board[i], 0, dim); //copy start position of an array to the destination array with "dim" being the num of components to be copied
        }
        //update x and y in the board
        this.X = b.X;
        this.Y = b.Y;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> children = new ArrayList<>();
        Board newBoard;

        for(int i = 1; i < tuplePos.length; i += 2) {
            newBoard = new Board(this);

            int dx = this.X - tuplePos[i];
            int dy = this.Y - tuplePos[i - 1];

            // Checks if out-of-bounds
            if((dx >= dim || dx < 0) || (dy >= dim || dy < 0)) {
                continue;
            }

            // Create new valid child state
            int temp = newBoard.board[dy][dx];
            newBoard.board[dy][dx] = 0;
            newBoard.board[this.Y][this.X] = temp;
            newBoard.X = dx;
            newBoard.Y = dy;

            // Add child state to list
            children.add(newBoard);
        }

        return children;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return l.equals(this);
    }

    public String toString(){
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim - 1; j++) {
                output.append(this.board[i][j] == 0 ? " " : String.format("%d", this.board[i][j])); //check if has a 0 than add a space
            }
            output.append(this.board[i][dim - 1] == 0 ? " \n" : String.format("%d\n", this.board[i][dim - 1]));
        }
        return output.toString();
    }

    @Override
    public double getG() {
        return 1;
    }

    public boolean equals(Object o){
        if(o == null) {
            return false;
        }

        if(!(o instanceof Board)) {
            return false;
        }

        Board other = (Board) o;

        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                if(other.board[i][j] != this.board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public int hashCode(){
        return super.hashCode();
    }


}
