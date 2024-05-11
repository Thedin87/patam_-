package test;

import java.util.ArrayList;

public class Board 
{

    private Tile[][] gameBoard = new Tile[15][15];
    private static Board board = null;
    private int[][] specialTiles = new int[15][15];

    private Board()
    {
        this.specialTiles[0][0] = this.specialTiles[0][14] = this.specialTiles[0][7] = this.specialTiles[7][0] = this.specialTiles[7][14] = this.specialTiles[14][0] = this.specialTiles[14][7] = this.specialTiles[14][14] = 32;
        this.specialTiles[0][3] = this.specialTiles[0][11] = this.specialTiles[2][6] = this.specialTiles[2][8] = this.specialTiles[3][7] = this.specialTiles[3][0] = this.specialTiles[0][14] = this.specialTiles[6][2] = this.specialTiles[6][6] = this.specialTiles[6][8] = this.specialTiles[6][12] = this.specialTiles[7][3] = this.specialTiles[7][11] = this.specialTiles[8][2] = this.specialTiles[8][12] = this.specialTiles[8][6] = this.specialTiles[8][8] = this.specialTiles[11][0] = this.specialTiles[11][7] = this.specialTiles[11][14] = this.specialTiles[12][6] = this.specialTiles[12][8] = this.specialTiles[14][3] = this.specialTiles[14][11] = 21;
        this.specialTiles[1][1] = this.specialTiles[2][2] = this.specialTiles[3][3] = this.specialTiles[4][4] = this.specialTiles[1][13] = this.specialTiles[2][12] = this.specialTiles[3][11] = this.specialTiles[4][10] = this.specialTiles[13][1] = this.specialTiles[12][2] = this.specialTiles[11][3] = this.specialTiles[10][4] = this.specialTiles[13][13] = this.specialTiles[12][12] = this.specialTiles[11][11] = this.specialTiles[10][10] = 22;
        this.specialTiles[1][5] = this.specialTiles[1][9] = this.specialTiles[5][1] = this.specialTiles[5][5] = this.specialTiles[5][9] = this.specialTiles[5][13] = this.specialTiles[9][1] = this.specialTiles[9][5] = this.specialTiles[9][9] = this.specialTiles[9][13] = this.specialTiles[13][5] = this.specialTiles[13][9] = 31;
        this.specialTiles[7][7] = 11;

    }

    public static Board getBoard()
    {

        // check if no board has been created
        if (board == null)
        {

            board = new Board();

        }

        return board;

    }

    public Tile[][] getTiles()
    {

        Tile[][] copy = new Tile[gameBoard.length][];
        
        for (int i = 0; i < gameBoard.length; i++)
        {

            Tile[] a = gameBoard[i];
            int aLength = a.length;
            copy[i] = new Tile[aLength];
            System.arraycopy(a, 0, copy[i], 0, aLength); 

        }

        return copy;

    }

    public boolean boardLegal(Word word)
    {

        
        int wordLength = word.getTiles().length;
        int row = word.getRow();
        int col = word.getCol();
        boolean isVertical = word.isVertical();
        Tile[] tiles = word.getTiles();
        boolean validPosition = false;

        // Check if the given word starts outside the bord
        if (row >= 15 || col >= 15 || row < 0 || col < 0)
        {

            return false;

        }

        // Use the word in the correct way
        if (isVertical)
        {

            // Check if the word overflows the colomn length
            if (row + wordLength > 15)
            {

                return false;

            }

            // If the word is on the star its valid
            if (7 >= row && row + wordLength >= 7 && col ==7)
            {

                validPosition = true;

            }

            // Ititrate through all the tiles in the word
            for (int tile = 0; tile < wordLength; tile++)
            {

                // If the Tile letter is equal to the letter on the board the word is valid
                if (gameBoard[tile + row][col].letter == tiles[tile].letter)
                {

                    validPosition = true;

                }
                // If the Tile isnt empty and has another letter in it return false
                else if (gameBoard[row + tile][col].letter != tiles[tile].letter && gameBoard[row + tile][col] != null)
                {

                    return false;

                }

                // If the letter is next to a position with a tile the word is valid
                if ((gameBoard[tile + row + 1][col] != null || gameBoard[tile + row - 1][col] != null || gameBoard[tile + row][col + 1] != null || gameBoard[tile + row][col - 1] != null) && gameBoard[tile + row][col] == null)
                {

                    validPosition = true;

                }

            }

        }
        else
        {

            if (col + wordLength > 15)
            {

                return false;

            }

            if (7 >= col && col + wordLength >= 7 && row ==7)
            {

                validPosition = true;

            }

            for (int tile = 0; tile < wordLength; tile++)
            {

                if (gameBoard[row][col + tile].letter == tiles[tile].letter)
                {

                    validPosition = true;

                }
                else if (gameBoard[row][col + tile].letter != tiles[tile].letter && gameBoard[row][col + tile] != null)
                {

                    return false;

                }

                if ((gameBoard[row + 1][col + tile] != null || gameBoard[row - 1][col + tile] != null || gameBoard[row][col + tile + 1] != null || gameBoard[row][col + tile - 1] != null) && gameBoard[row][col + tile] == null)
                {

                    validPosition = true;

                }

            }

        }

        if (validPosition)
        {

            return true;

        }
        else
        {

            return false;

        }

    }

    public boolean dictonaryLegal()
    {

        return true;

    }

    public ArrayList<Word> getWords(Word word)
    {

        ArrayList<Word> wordList = new ArrayList<Word>();
        Tile[] tiles = word.getTiles();
        boolean vertical = word.isVertical();
        int row = word.getRow();
        int col = word.getCol();

        wordList.add(word);

        // Check if the word is vertical
        if (vertical)
        {

            // Ititrate through all the letters in the word
            for (int tile = 0; tile < tiles.length; tile++)
            {

                // If the square isnt null the the word wont be new so it wont get more points
                if (gameBoard[row + tile][col] != null)
                {

                    continue;

                }

                // Check if the tile to the left isnt null
                if (gameBoard[row + tile][col - 1] != null && col != 0)
                {

                    int currentCol = col - 1; 

                    while (currentCol < 15 && currentCol >= 0)
                    {

                        if (gameBoard[row + tile][currentCol] == null)
                        {

                            break;

                        }
                        currentCol--;

                    }

                    Word newWord = createWord(row + tile, currentCol, false, col);
                    wordList.add(newWord);

                }
                else if (gameBoard[row + tile][col - 1] == null && gameBoard[row + tile][col + 1] != null)
                {

                    Word newWord = createWord(row + tile, col, false, col);
                    wordList.add(newWord);

                }

            }

        }
        else
        {

            // Ititrate through all the letters in the word
            for (int tile = 0; tile < tiles.length; tile++)
            {

                // If the square isnt null the the word wont be new so it wont get more points
                if (gameBoard[row][col + tile] != null)
                {

                    continue;

                }

                // Check if the tile above isnt null
                if (gameBoard[row - 1][col + tile] != null && row != 0)
                {

                    int currentRow = row; 

                    while (currentRow < 15 && currentRow >= 0)
                    {

                        if (gameBoard[currentRow][col + tile] == null)
                        {

                            break;

                        }
                        currentRow--;

                    }

                    Word newWord = createWord(currentRow, col + tile, true, row);
                    wordList.add(newWord);

                }
                else if (gameBoard[row - 1][col + tile] == null && gameBoard[row + 1][col + tile] != null)
                {

                    Word newWord = createWord(row, col + tile, true, row);
                    wordList.add(newWord);

                }

            }

        }



        return wordList;

    }

    public Word createWord(int row, int col, boolean vertical, int cuurentChar)
    {

        ArrayList<Tile> tilesList = new ArrayList<Tile>();
        
        if (vertical)
        {

            int currentRow = row;

            while (currentRow < 15 && currentRow >=0)
            {

                if (gameBoard[currentRow][col] != null)
                {

                    tilesList.add(gameBoard[currentRow][col]);

                }
                else if (currentRow == cuurentChar)
                {

                    tilesList.add(gameBoard[cuurentChar][col]);

                }
                else
                {

                    break;

                }

                currentRow++;

            }

        }
        else
        {

            int currentCol = col;

            while (currentCol < 15 && currentCol >=0)
            {

                if (gameBoard[row][currentCol] != null)
                {

                    tilesList.add(gameBoard[row][currentCol]);

                }
                else if (currentCol == cuurentChar)
                {

                    tilesList.add(gameBoard[row][cuurentChar]);

                }
                else
                {

                    break;

                }

                currentCol++;

            }

        }

        Tile[] tilesArray = new Tile[tilesList.size()];
        tilesArray = tilesList.toArray(tilesArray);

        Word word = new Word(tilesArray, row, col, vertical);

        return word;

    }

    public int getScore(Word word)
    {

        Tile[]  tiles = word.getTiles();
        int col = word.getCol();
        int row = word.getRow();
        boolean vertical = word.isVertical();
        int multiplier = 1;
        int wordBeforeMultiplier = 0;
        boolean firstGame = true;

        outerloop:
        for (int i = 0; i < gameBoard.length; i++)
        {

            for (int j = 0; j < gameBoard.length; j++)
            {

                if (gameBoard[i][j] != null)
                    firstGame = false;
                    break outerloop;

            }

        }

        for (int tile = 0; tile < tiles.length; tile ++)
        {

            int currentRow;
            int currentCol;
            int currentTileScore = tiles[tile].score;

            if (vertical)
            {

                currentCol = col;
                currentRow = row + tile;

            }
            else
            {

                currentCol = col + tile;
                currentRow = row;

            }

            switch (specialTiles[currentRow][currentCol]) 
            {

                case 21:
                    wordBeforeMultiplier += currentTileScore * 2;
                    break;

                case 31:
                    wordBeforeMultiplier += currentTileScore * 3;
                    break;

                case 22:
                    multiplier = multiplier * 2;
                    wordBeforeMultiplier += currentTileScore;
                    break;

                case 32:
                    multiplier = multiplier * 3;
                    wordBeforeMultiplier += currentTileScore;
                    break;

                case 11:
                    if (firstGame){
                        multiplier = multiplier * 2;
                        wordBeforeMultiplier += currentTileScore;
                    }
                    else
                        wordBeforeMultiplier += currentTileScore;
                    break;
            
                default:
                    wordBeforeMultiplier += currentTileScore;
                    break;
            }

        }

        return wordBeforeMultiplier * multiplier;

    }

    public int tryPlaceWord(Word word)
    {

        Tile[]  tiles = word.getTiles();
        int col = word.getCol();
        int row = word.getRow();
        boolean vertical = word.isVertical();
        int totalScore = 0;
        Tile[] newTiles = new Tile[tiles.length];
        

        for (int tile = 0; tile < tiles.length; tile++ )
        {

            if (tiles[tile] == null)
            {

                int currentCol;
                int currentRow;

                if (vertical)
                {

                    currentRow = row + tile;
                    currentCol = col;

                }
                else
                {

                    currentRow = row;
                    currentCol = col + tile;

                }

                newTiles[tile] = gameBoard[currentRow][currentCol];

            }
            else
            {

                newTiles[tile] = tiles[tile];

            }

        }

        Word fullWord = new Word(newTiles, row, col, vertical);

        if (boardLegal(fullWord) == false)
            return 0;

        ArrayList<Word> wordList = getWords(fullWord);

        for (Word w: wordList)
        {

            totalScore += getScore(w);

        }

        // Place the word
        for (int tile = 0; tile < tiles.length; tile++)
        {

            if (tiles[tile] != null)
            {

                int currentCol;
                int currentRow;

                if (vertical)
                {

                    currentRow = row + tile;
                    currentCol = col;

                }
                {

                    currentRow = row;
                    currentCol = col + tile;

                }

                gameBoard[currentRow][currentCol] = tiles[tile];

            }

        }
        
        
        return totalScore;

    }

}
