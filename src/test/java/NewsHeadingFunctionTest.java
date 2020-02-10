import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadingFunctionTest
{
    NewsHeading function=new NewsHeading();
    static List<String> getTestData()
    {
        List<String> listOfSentence = new ArrayList<>();

        listOfSentence.add("Nagpur is famous for its orange");
        listOfSentence.add("Pune is the cultural capital of maratha people");
        listOfSentence.add("Mumbai is the capital city of maharashtra state");

        return listOfSentence;
    }

    @Test
    public void givenMethodToCheckCountOfWordsInListIsCorrectOrNot()
    {
        List<String> listOfSentence = getTestData();
        List<String> listOfWords = function.listOfWords(listOfSentence);
        Assert.assertEquals(listOfWords.size(),22);
    }

    @Test
    public void givenMethodToCheckMostRepeatedWordInList()
    {
        List<String> listOfSentence = getTestData();
        List<String> listOfWords = function.listOfWords(listOfSentence);
        String repeatedWord = function.findWords(listOfWords);
        Assert.assertEquals(repeatedWord,"is");
    }

    @Test
    public void givenMethodToCheckMostPopularSentenceWithScore()
    {
        List<String> listOfSentence = getTestData();
        List<Integer> listOfPoints = new ArrayList<>();
        listOfPoints.add(50);
        listOfPoints.add(150);
        listOfPoints.add(100);

        Map<String,Integer> sentenceMap = new HashMap<>();

        for (int i=0;i<listOfSentence.size();i++)
        {
            sentenceMap.put(listOfSentence.get(i),listOfPoints.get(i));
        }

        List<String> listOfWords = function.listOfWords(listOfSentence);
        String words = function.findWords(listOfWords);
        String mostPopularSentence = function.getMostPopularNews(sentenceMap, words);
        System.out.println("most popular sentence "+mostPopularSentence);
    }

    @Test
    public void givenMethodToGetMostPopularNews()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        List<String> newsHeading = new ArrayList<>();
        List<String> newsPoints = new ArrayList<>();
        Map<String, Integer> newsMap = new HashMap<>();

        driver.get("https://news.ycombinator.com");
        driver.findElement(By.xpath("//a[@class='morelink' and @rel='next']")).click();
        driver.findElement(By.xpath("//a[@class='morelink' and @rel='next']")).click();
        driver.findElement(By.xpath("//a[@class='morelink' and @rel='next']")).click();
        List<WebElement> newsElements = driver.findElements(By.cssSelector("a.storylink"));
        List<WebElement> scorePoints = driver.findElements(By.cssSelector("tr td span.score"));
        for (WebElement webElement : newsElements)
        {
            System.out.println(webElement.getText());
            newsHeading.add(webElement.getText());
        }

        for (WebElement webElement : scorePoints)
        {
            System.out.println(webElement.getText().substring(0, webElement.getText().length() - 7));
            newsPoints.add(webElement.getText().substring(0, webElement.getText().length() - 7));
        }

        for (int i = 0; i < newsHeading.size(); i++)
        {
            System.out.println(newsHeading.get(i));
            System.out.println(Integer.parseInt(newsPoints.get(i)));
            newsMap.put(newsHeading.get(i), Integer.parseInt(newsPoints.get(i)));
        }

        List<String> listOfWords = function.listOfWords(newsHeading);
        String maxWord = function.findWords(listOfWords);
        System.out.println("The word occure maximum times is : " + maxWord);
        String maxNewsHeading = function.getMostPopularNews(newsMap, maxWord);
        System.out.println("Most popular news is : " + maxNewsHeading);
    }
}
