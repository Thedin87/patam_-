package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// The Tile class
final class Tile 
{

    // Attributes of the Tile class 
    public final char letter;
    public final int score;

    // Parameterized constructor
    private Tile(char letter, int score) 
    {

        this.letter = letter;
        this.score = score;

    }   

    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + score;
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (letter != other.letter)
            return false;
        if (score != other.score)
            return false;
        return true;
    }

    // The bag class
    public static class Bag 
    {

        final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private int[] tileCount = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2};
        private Tile[] tileArray = new Tile[26];
        private int currentTilesTotal;
        private static Bag bag = null;


        private Bag() 
        {
            
            // Initilizing tileArray values
            this.tileArray[0] = new Tile('A', 1); 
            this.tileArray[1] = new Tile('B', 3);
            this.tileArray[2] = new Tile('C', 3);
            this.tileArray[3] = new Tile('D', 2);
            this.tileArray[4] = new Tile('E', 1);
            this.tileArray[5] = new Tile('F', 4);
            this.tileArray[6] = new Tile('G', 2);
            this.tileArray[7] = new Tile('H', 4);
            this.tileArray[8] = new Tile('I', 1);
            this.tileArray[9] = new Tile('J', 8);
            this.tileArray[10] = new Tile('K', 5);
            this.tileArray[11] = new Tile('L', 1);
            this.tileArray[12] = new Tile('M', 3);
            this.tileArray[13] = new Tile('N', 1);
            this.tileArray[14] = new Tile('O', 1);
            this.tileArray[15] = new Tile('P', 3);
            this.tileArray[16] = new Tile('Q', 10);
            this.tileArray[17] = new Tile('R', 1);
            this.tileArray[18] = new Tile('S', 1);
            this.tileArray[19] = new Tile('T', 1);
            this.tileArray[20] = new Tile('U', 1);
            this.tileArray[21] = new Tile('V', 4);
            this.tileArray[22] = new Tile('W', 4);
            this.tileArray[23] = new Tile('X', 8);
            this.tileArray[24] = new Tile('Y', 4);
            this.tileArray[25] = new Tile('Z', 10);

            this.currentTilesTotal = 98;

        }

        public static Bag getBag()
        {

            if (bag == null)
            {

                bag = new Bag();

            }

            return bag;

        }

        public Tile getTile(char tile)
        {

            // Check if the character is valid
            if (alphabet.indexOf(tile) == -1)
            {

                System.out.println("The char isnt valid");
                return null;

            }

            // Get the index of the character in the alphabet
            int tilePlacement = alphabet.indexOf(tile);

            if (tileCount[tilePlacement] == 0)
            {

                return null;

            }
            else
            {

                currentTilesTotal--;
                tileCount[tilePlacement]--;
                return tileArray[tilePlacement];

            }

        }

        public Tile getRand()
        {

            ArrayList<Tile> allTilesList = new ArrayList<Tile>();
            Random rnd = new Random();

            // Create a list with all the tiles
            for (int i = 0; i < tileCount.length; i++)
            {

                for (int j = 0; j < tileCount[i]; j++)
                {

                    allTilesList.add(tileArray[i]);

                }

            }

            // Get a random tile from the unempty tile list and return it
            Tile randomTile = allTilesList.get(rnd.nextInt(allTilesList.size()));
            return getTile(randomTile.letter);

        }

        public void put(Tile tile)
        {

            if (currentTilesTotal >= 98)
            {

                System.out.println("Cant add more tiles to the bag");

            }
            else
            {

                char tileChar = tile.letter;

                // Get the index of the character in the alphabet
                int tilePlacement = alphabet.indexOf(tileChar);

                tileCount[tilePlacement]++;
                currentTilesTotal++;
            }

        }

        public int size()
        {

            // int sizeCounter = 0;

            // for (int i = 0; i < tileCount.length; i++)
            // {

            //     for (int j = 0; j < tileCount[i]; j++)
            //     {

            //         sizeCounter++;

            //     }

            // }

            return currentTilesTotal;

        }

        public int[] getQuantities()
        {

            return Arrays.copyOf(tileCount, tileCount.length);

        }

    }

}
