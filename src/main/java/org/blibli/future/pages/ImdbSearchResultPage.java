package org.blibli.future.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class ImdbSearchResultPage {

    private final WebDriver driver;
    private String keyword = "lupin";
    private By title = By.xpath("//*[@id='main']/div/h1/span");
    private By fullTitle = By.xpath("//*[@id='main']/div/h1");
    private By subTitles = By.xpath("//*[@id='findSubHeader']");
    private By noTable = By.xpath("//*[@id='main']/div/div");
    private WebElement tableNull = null;
    private By tableTitles = By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr/td[2]/a");
    private By tableNames = By.xpath("//*[@id='main']/div/div[3]/table/tbody/tr/td[2]/a");
    private By tableNamesPage = By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr[1]/td[2]/a");
    private By tableKeywords = By.xpath("//*[@id='main']/div/div[4]/table/tbody/tr/td/a");
    private By tableKeywordsPage = By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr[1]/td/a");
    private By tableCompanies = By.xpath("//*[@id='main']/div/div[5]/table/tbody/tr/td/a");
    private By tableCompaniesPage = By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr[1]/td/a");
    private By tableOdd = By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr[@class= 'findResult odd']");
    private By tableEven = By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr[@class= 'findResult even']");
    private By moreTitles = By.xpath("//*[@id='main']/div/div[2]/div/a[1]");
    private By moreCelebs = By.xpath("//*[@id='main']/div/div[3]/div/a[1]");
    private By cbCategory = By.xpath("//*[@id='nav-search-form']/div[1]/div/label/div");

    private By exactTitleMatches = By.xpath("//*[@id='main']/div/div[2]/div/a");

    private List<WebElement> listOfElementsOdd;
    private List<WebElement> listOfElementsEven;
    private List<WebElement> listOfData;

    public ImdbSearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String checkValidResultTitle(){
        return driver.findElement(title).getText();
    }

    public String checkInvalidResultTitle(){
        return driver.findElement(fullTitle).getText();
    }


    public boolean checkTableDataContainKeyword(String category, String keyword){
        setElement(category);
        for (WebElement o : listOfData){
            if (o.getText().toLowerCase().contains(keyword.toLowerCase()))
                return true;
        }
        return false;
    }

    public  Boolean checkNoTable(){
        tableNull = driver.findElement(noTable);
        if ( tableNull != null)
            return true;
        return false;
    }

    public void setElement(String category){
        if (category.equals("titles"))
            listOfData = driver.findElements(tableTitles);
        else if (category.equals("names"))
            listOfData = driver.findElements(tableNames);
        else if (category.equals("namesPage"))
            listOfData = driver.findElements(tableNamesPage);
        else if (category.equals("keywords"))
            listOfData = driver.findElements(tableKeywords);
        else if (category.equals("keywordsPage"))
            listOfData = driver.findElements(tableKeywordsPage);
        else if (category.equals("companies"))
            listOfData = driver.findElements(tableCompanies);
        else if (category.equals("companiesPage"))
            listOfData = driver.findElements(tableCompaniesPage);
    }

    public Integer checkTotalResult(){
        listOfElementsOdd = driver.findElements(tableOdd);
        listOfElementsEven = driver.findElements(tableEven);
        return listOfElementsEven.size() + listOfElementsOdd.size();
    }

    public Integer checkDisplayedTotalResult(){
        String [] jmlh = driver.findElement(fullTitle).getText().split(" ");
        return Integer.parseInt(jmlh[1]);
    }

    public String viewMore(String category){
        if (category.equals("Titles"))
            driver.findElement(moreTitles).click();
        else
            driver.findElement(moreCelebs).click();
        return driver.findElement(cbCategory).getText();
    }

    public Boolean viewMatches(String detail){
        if (detail.equals("Exact"))
            driver.findElement(exactTitleMatches).click();
        return driver.findElement(subTitles).getText().contains("Exact Matches");
    }

    public Boolean checkSubTitles(String category){
        String subTitle = driver.findElement(subTitles).getText();
        if (category.equals("Titles"))
            return subTitle.contains("Titles");
        else if (category.equals("TV Episodes"))
            return subTitle.contains("TV Episode");
        else if (category.equals("Celebs"))
            return subTitle.contains("Names");
        else if (category.equals("Companies"))
            return subTitle.contains("Companies");
        else
            return subTitle.contains("Keywords");
    }

}
