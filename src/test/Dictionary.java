package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Dictionary {

    CacheManager existsCacheManager;
    CacheManager doesntExiCacheManager;
    BloomFilter bloomFilter;

    private String[] paths;

    public Dictionary(String... paths)
    {

        this.paths = paths;

        CacheReplacementPolicy lru = new LRU();
        CacheReplacementPolicy lfu = new LFU();

        existsCacheManager = new CacheManager(400, lru);
        doesntExiCacheManager  = new CacheManager(100, lfu);

        bloomFilter = new BloomFilter(256, "SHA1", "MD5");

        for (String path: paths)
        {

            BufferedReader file;
            try {
                
                file = new BufferedReader(new FileReader(path));
                String line = file.readLine();

                while (line != null) 
                {

				    for (String word: line.split(" "))
                    {

                        bloomFilter.add(word);

                    }

                    line = file.readLine();
			    
                }

			    file.close();

		        } 
                catch (IOException e) {

			        e.printStackTrace();

		        }

        }

    }

    public boolean query(String word)
    {

        if (existsCacheManager.query(word))
            return true;

        if (doesntExiCacheManager.query(word))
            return false;

        if (bloomFilter.contains(word))
        {

            existsCacheManager.add(word);
            return true;

        }
        else
        {

            doesntExiCacheManager.add(word);
            return false;

        }

    }

    public boolean challenge(String word)
    {

        if (IOSearcher.search(word, paths))
        {

            existsCacheManager.add(word);
            return true;

        }
        else
        {

            doesntExiCacheManager.add(word);
            return false;

        }

    }

}
