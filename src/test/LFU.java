package test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class LFU implements CacheReplacementPolicy
{

    private LinkedHashMap<String, Integer>  wordMap = new LinkedHashMap<String, Integer>();

    @Override
    public void add(String word)
    {

        if (wordMap.containsKey(word))
        {

            int count = wordMap.get(word) + 1;
            wordMap.remove(word);
            wordMap.put(word, count);

        }
        else
        {

            wordMap.put(word, 1);

        }


    }

    @Override
    public String remove()
    {

        if (wordMap.size() == 0)
        {

            return null;

        }
        
        Set<String> keys = wordMap.keySet();
        int minValue = 0;
        String minKey = "";
        for (Map.Entry<String,Integer> entry: wordMap.entrySet())
        {

            minValue = entry.getValue();
            minKey = entry.getKey();
            break;

        }
        

        for (String key: keys )
        {

            if (wordMap.get(key) < minValue)
            {

                minValue = wordMap.get(key);
                minKey = key;

            }

        }

        wordMap.remove(minKey);

        if (minValue != 1)
        {

            wordMap.put(minKey, minValue - 1);

        }


        return minKey;

    }

}