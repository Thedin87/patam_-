package test;

import java.util.List;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class BloomFilter {

    private BitSet bits;
    private List<String> algs;
    private int size;
    
	
    public BloomFilter(int size, String... algs)
    {

        this.bits = new BitSet(size);
        this.algs = Arrays.asList(algs);
        this.size = size;

    }

    public void add(String word)
    {

        List<Integer> positionList = getPositions(word);

        for (int position: positionList)
        {

            bits.set(position);

        }

    }

    private List<Integer> getPositions(String word)
    {

        List<Integer> positionList = new ArrayList<Integer>();

        for (String alg: algs)
        {

            MessageDigest md;
            try
            {
                md = MessageDigest.getInstance(alg);
                byte[] bts = md.digest(word.getBytes());
                BigInteger bigInt = new BigInteger(bts);
                int numAbs = Math.abs(bigInt.intValue());
                int mod = numAbs % size;
                positionList.add(mod);
            }
            catch(NoSuchAlgorithmException x)
            {}

        }

        return positionList;

    }

    public boolean contains(String word)
    {

        List<Integer> positionList = getPositions(word);

        for (int position: positionList)
        {

            if (!bits.get(position))
                return false;

        }

        return true;

    }

    @Override
    public String toString()
    {

        String binary = "";

        for (int i = 0; i < bits.length(); i++) {

            binary = binary + (bits.get(i) ? 1 : 0);

        }

        return binary;

    }

}
