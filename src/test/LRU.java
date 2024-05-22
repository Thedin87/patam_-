package test;

import java.util.LinkedList;

public class LRU implements CacheReplacementPolicy
{

    private LinkedList<String> wordList = new LinkedList<String>();

    @Override
    public void add(String word)
    {

        if (wordList.size() == 0)
        {

            wordList.add(word);
            return;

        }

        if (word == wordList.getFirst())
        {

            wordList.poll();
            wordList.add(word);

        }
        else
        {

            wordList.add(word);

        }

    }

    @Override
    public String remove()
    {

        return wordList.poll();

    }

}