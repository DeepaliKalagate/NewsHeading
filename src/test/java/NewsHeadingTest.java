import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class NewsHeadingTest
{
    public static void main(String[] args)
    {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.get("https://news.ycombinator.com/news?p=11");

        List<WebElement> news = driver.findElements(By.cssSelector("tr.athing a.storylink"));
        List<WebElement> score = driver.findElements(By.cssSelector("tr span.score"));
        List<String> newsHeading = new ArrayList<String>();
        List<String> scoreHeading = new ArrayList<String>();

        for (WebElement n : news)
        {
            newsHeading.add(n.getText());
        }
        for (WebElement s : score)
        {
            scoreHeading.add(s.getText().substring(0, s.getText().length() - 7));
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < scoreHeading.size(); i++)
        {
            map.put(newsHeading.get(i), Integer.parseInt(scoreHeading.get(i)));
        }

        System.out.println(newsHeading);
        System.out.println(scoreHeading);
        listOfWord(newsHeading);
        driver.close();
    }

    static List<String> listOfWord(List<String> newsHeading)
    {
        List<String> listOfWords = new ArrayList<>();
        for (String words : newsHeading)
        {
            String[] arrOfString = words.split(" ");
            String world = findWord(arrOfString);
            List<String> l1 = Arrays.asList(arrOfString);
            listOfWords.addAll(l1);
            System.out.println(world);
        }
        return listOfWords;
    }

    static String findWord(String[] arr)
    {
        // Create HashMap to store word and it's frequency
        HashMap<String, Integer> hs = new HashMap<String, Integer>();

        // Iterate through array of words
        for (int i = 0; i < arr.length; i++)
        {
            // If word already exist in HashMap then increase it's count by 1
            if (hs.containsKey(arr[i]))
            {
                hs.put(arr[i], hs.get(arr[i]) + 1);
            }
            // Otherwise add word to HashMap
            else
            {
                hs.put(arr[i], 1);
            }
        }

        // Create set to iterate over HashMap
        Set<Map.Entry<String, Integer>> set = hs.entrySet();
        String key = "";
        int value = 0;

        for (Map.Entry<String, Integer> me : set)
        {
            // Check for word having highest frequency
            if (me.getValue() > value)
            {
                value = me.getValue();
                key = me.getKey();
            }
        }
        return key;
    }
}
