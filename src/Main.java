import java.util.Scanner;
public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int row = -1;
        int col = -1;
        String player = "X";
        int moveCount = 0;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;
        boolean playing = true;
        boolean done = false;
        do
        {
            player = "X";
            playing = true;
            moveCount = 0;
            clearBoard();
            do
            {
                do
                {
                    display();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(in, "Enter row", 1, 3);
                    col = SafeInput.getRangedInt(in, "Enter col", 1, 3);
                    row--;
                    col--;
                    if (!isValidMove(row, col))
                        System.out.println("Sorry but that's not an open space!");
                }while(!isValidMove(row, col));
                board[row][col] = player;
                moveCount++;
                if (moveCount >= MOVES_FOR_WIN)
                {
                    if(isWin(player))
                    {
                        display();
                        System.out.println("Player " + player + " wins!");
                        playing = false;
                    }
                }
                if (moveCount >= MOVES_FOR_TIE)
                {
                    if(isTie())
                    {
                        display();
                        System.out.println("No one wins, it's a tie!");
                        playing = false;
                    }
                }
                if (player.equals("X"))
                    player = "O";
                else
                    player = "X";
            }while(playing);

            done = SafeInput.getYNConfirm(in, "Done Playing? ");
        }while(!done);
    }
    private static void clearBoard()
    {
        for (int row=0; row < ROW; row++)
        {
            for (int col=0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }
    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
            return true;
        return false;
    }
    private static boolean isRowWin(String player)
    {
        for (int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
                return true;
        }
        return false;
    }
    private static boolean isColWin(String player)
    {
        for (int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
                return true;
        }
        return false;
    }
    private static boolean isDiagonalWin(String player)
    {
        if (board[1][1].equals(player))
        {
            if (board[0][0].equals(player) && board[2][2].equals(player))
                return true;
            if (board[0][2].equals(player) && board[2][0].equals(player))
                return true;
        }
        return false;
    }
    private static void display()
    {
        for (int row=0; row < ROW; row++)
        {
            for (int col=0; col < COL; col++)
            {
                System.out.print("|" + board[row][col]);
            }
            System.out.println("|");
        }
    }
    private static boolean isTie()
    {
        for (int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") && !board[row][1].equals("O") && !board[row][2].equals("O"))
                return false;
            if(board[row][1].equals("X") && !board[row][0].equals("O") && !board[row][2].equals("O"))
                return false;
            if(board[row][2].equals("X") && !board[row][1].equals("O") && !board[row][0].equals("O"))
                return false;
            if(board[row][0].equals("O") && !board[row][1].equals("X") && !board[row][2].equals("X"))
                return false;
            if(board[row][1].equals("O") && !board[row][0].equals("X") && !board[row][2].equals("X"))
                return false;
            if(board[row][2].equals("O") && !board[row][1].equals("X") && !board[row][0].equals("X"))
                return false;
        }
        for (int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") && !board[1][col].equals("O") && !board[2][col].equals("O"))
                return false;
            if(board[1][col].equals("X") && !board[2][col].equals("O") && !board[0][col].equals("O"))
                return false;
            if(board[2][col].equals("X") && !board[1][col].equals("O") && !board[0][col].equals("O"))
                return false;
            if(board[0][col].equals("O") && !board[1][col].equals("X") && !board[2][col].equals("X"))
                return false;
            if(board[1][col].equals("O") && !board[2][col].equals("X") && !board[0][col].equals("X"))
                return false;
            if(board[2][col].equals("O") && !board[1][col].equals("X") && !board[0][col].equals("X"))
                return false;
        }
        if (board[1][1].equals("X") && !board[0][0].equals("O") && !board[2][2].equals("O"))
            return false;
        if (board[1][1].equals("X") && !board[2][0].equals("O") && !board[0][2].equals("O"))
            return false;
        if (board[1][1].equals("O") && !board[0][0].equals("X") && !board[2][2].equals("X"))
            return false;
        if (board[1][1].equals("O") && !board[2][0].equals("X") && !board[0][2].equals("X"))
            return false;
        return true;
    }
}