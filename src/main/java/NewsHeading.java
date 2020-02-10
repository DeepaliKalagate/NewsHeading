import java.util.*;

public class NewsHeading
{
    public List<String> listOfWords(List<String> news)
    {
        List<String> listOfWords = new ArrayList<String>();
        for (String s : news)
        {
            String[] arrOfString = s.split(" ");
            List<String> l1= Arrays.asList(arrOfString);
            listOfWords.addAll(l1);
        }
        System.out.println(listOfWords);
        return listOfWords;
    }

    public String findWords(List<String> arr)
    {
        Map<String,Integer> newsMap = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {

            if (newsMap.containsKey(arr.get(i))) {
                newsMap.put(arr.get(i), newsMap.get(arr.get(i)) + 1);
            }
            else {
                newsMap.put(arr.get(i), 1);
            }
        }
        String highestValue = getRepeatedWord(newsMap);
        return highestValue;
    }

    public String getRepeatedWord(Map<String,Integer> newsMap)
    {
        Set<Map.Entry<String, Integer> > set = newsMap.entrySet();
        String key = "";
        int value =0;

        for (Map.Entry<String, Integer> me : set) {
            if (me.getValue() > value) {
                value = me.getValue();
                key = me.getKey();
            }
        }
        System.out.println(key+"--"+value);
        return key;
    }

    public String getMostPopularNews(Map<String,Integer> newsMap, String mostRepetedWord)
    {
        int value=0;
        String popularWord =" ";
        for (Map.Entry<String,Integer> newNewsMap : newsMap.entrySet()) {

            if (newNewsMap.getKey().contains(mostRepetedWord)) {

                if (newNewsMap.getValue()>value)
                {
                    value=newNewsMap.getValue();
                    popularWord=newNewsMap.getKey();
                }
            }
        }
        return popularWord;
    }
}


