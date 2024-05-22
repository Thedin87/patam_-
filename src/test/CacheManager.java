package test;

import java.util.HashSet;

public class CacheManager 
{

    int size;
    CacheReplacementPolicy crp;
    HashSet<String> cachedWords = new HashSet<String>();

    public CacheManager(int size, CacheReplacementPolicy crp)
    {

        this.size = size;
        this.crp = crp;

        
    }

    public boolean query(String word)
    {

        return cachedWords.contains(word);

    }

    public void add(String word)
    {

        crp.add(word);
        cachedWords.add(word);

        if (cachedWords.size() > size){

            word = crp.remove();
            cachedWords.remove(word);

        }
        

    }

}

