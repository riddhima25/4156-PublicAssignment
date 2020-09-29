package models;

public class GameBoard {

  private Player p1;

  private Player p2;

  private boolean gameStarted;

  private int turn;

  private char[][] boardState;

  private int winner;

  private boolean isDraw;

  /**
   * Sets default values for new GameBoard object.
   */
  public GameBoard() {
    gameStarted = false;
    turn = 1;
    boardState = new char[3][3];
    winner = 0;
    isDraw = false;
  }

  /**
   * Determines if move is valid or not.
   * 
   * @param move to check for validity
   * @return boolean indicating if move is valid or not.
   */
  public boolean isValidMove(Move move) {
    boolean isPlayerTurn = (move.getPlayer() != null) && move.getPlayer().getId() == turn;
    if (!isPlayerTurn || winner != 0 || gameStarted == false || isDraw == true) {
      return false;
    }
    boolean moveLocationEmpty = boardState[move.getMoveX()][move.getMoveY()] == '\u0000';
    return moveLocationEmpty;
  }

  /**
   * Determines if the game is a draw.
   * 
   * @return boolean indicating if game is a draw.
   */
  public boolean gameIsDraw() {
    for (int r = 0; r < boardState.length; r++) {
      for (int c = 0; c < boardState.length; c++) {
        // check if any board position is empty
        if (boardState[r][c] == '\u0000') {
          return false;
        }
      }
    }
    return (winner == 0);
  }

  /**
   * Determines if a player has won the game and returns their player id. If no player has won,
   * returns 0.
   * 
   * @return id of player who has won
   */
  public int getGameWinner() {
    char winnerType = '\u0000';
    // check rows
    for (int r = 0; r < boardState.length; r++) {
      if (boardState[r][0] != '\u0000' && boardState[r][0] == boardState[r][1]
          && boardState[r][1] == boardState[r][2]) {
        winnerType = boardState[r][0];
        break;
      }
    }
    // check columns
    for (int c = 0; c < boardState[0].length; c++) {
      if (boardState[0][c] != '\u0000' && boardState[0][c] == boardState[1][c]
          && boardState[1][c] == boardState[2][c]) {
        winnerType = boardState[0][c];
        break;
      }
    }
    // check diagonals
    if (boardState[0][0] != '\u0000' && boardState[0][0] == boardState[1][1]
        && boardState[1][1] == boardState[2][2]) {
      winnerType = boardState[0][0];
    }
    if (boardState[0][2] != '\u0000' && boardState[0][2] == boardState[1][1]
        && boardState[1][1] == boardState[2][0]) {
      winnerType = boardState[0][2];
    }
    // return id of player associated with winning type.
    if (winnerType == '\u0000') {
      return 0;
    } else if (winnerType == p1.getType()) {
      return 1; 
    } else {
      return 2;
    }
  }

  /**
   * Assuming isValidMove has already been called, makes move and updates game board.
   * 
   * <p>Note: Also checks for game winner or game draw after executing move.
   * 
   * @param move to be executed
   */
  public void makeMove(Move move) {
    boardState[move.getMoveX()][move.getMoveY()] = move.getPlayer().getType();
    // switch turn
    if (turn == 1) {
      turn = 2;
    } else {
      turn = 1;
    }
    // check for winner and draw
    int gameWinner = getGameWinner();
    if (gameWinner != 0) {
      winner = gameWinner;
      gameStarted = false;
    } else {
      if (gameIsDraw()) {
        isDraw = true;
        gameStarted = false;
      }
    }
  }

  /**
   * Returns player one.
   * 
   * @return the p1
   */
  public Player getP1() {
    return p1;
  }

  /**
   * Sets player one.
   * 
   * @param p1 the p1 to set
   */
  public void setP1(Player p1) {
    this.p1 = p1;
  }

  /**
   * Returns player 2.
   * 
   * @return the p2
   */
  public Player getP2() {
    return p2;
  }

  /**
   * Sets player 2.
   * 
   * @param p2 the p2 to set
   */
  public void setP2(Player p2) {
    this.p2 = p2;
  }

  /**
   * Returns boolean indicating whether game has started.
   * 
   * @return the gameStarted
   */
  public boolean isGameStarted() {
    return gameStarted;
  }

  /**
   * Sets gameStarted variable.
   * 
   * @param gameStarted the gameStarted to set
   */
  public void setGameStarted(boolean gameStarted) {
    this.gameStarted = gameStarted;
  }

  /**
   * Gets turn indicating which player's turn it is.
   * 
   * <p>Note: turn will take on values 1 or 2 corresponding to player 1 and player 2 respectively.
   * 
   * @return the turn
   */
  public int getTurn() {
    return turn;
  }

  /**
   * Sets turn variable indicating which player's turn it is.
   * 
   * @param turn the turn to set
   */
  public void setTurn(int turn) {
    this.turn = turn;
  }

  /**
   * Returns boardState.
   * 
   * @return the boardState
   */
  public char[][] getBoardState() {
    return boardState;
  }

  /**
   * Sets boardState.
   * 
   * @param boardState the boardState to set
   */
  public void setBoardState(char[][] boardState) {
    this.boardState = boardState;
  }

  /**
   * Returns id of the winner.
   * 
   * @return the winner
   */
  public int getWinner() {
    return winner;
  }

  /**
   * Sets winner variable with id of player that has won.
   * 
   * @param winner the winner to set
   */
  public void setWinner(int winner) {
    this.winner = winner;
  }

  /**
   * Returns boolean indicating whether game has ended in a draw.
   * 
   * @return the isDraw
   */
  public boolean isDraw() {
    return isDraw;
  }

  /**
   * Sets isDraw variable.
   * 
   * @param isDraw the isDraw to set
   */
  public void setDraw(boolean isDraw) {
    this.isDraw = isDraw;
  }

}
