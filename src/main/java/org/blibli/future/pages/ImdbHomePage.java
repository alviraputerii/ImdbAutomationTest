package org.blibli.future.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImdbHomePage {
    private WebDriver driver;
    private By cbCategory = By.xpath("//*[@id='nav-search-form']/div[1]/div/label/div");
    private By all = By.xpath("//*[@id='navbar-search-category-select-contents']/ul/a[1]");
    private By titles = By.xpath("//*[@id='navbar-search-category-select-contents']/ul/a[2]");
    private By tvEpisodes = By.xpath("//*[@id='navbar-search-category-select-contents']/ul/a[3]");
    private By celebs = By.xpath("//*[@id='navbar-search-category-select-contents']/ul/a[4]");
    private By companies = By.xpath("//*[@id='navbar-search-category-select-contents']/ul/a[5]");
    private By keywords = By.xpath("//*[@id='navbar-search-category-select-contents']/ul/a[6]");
    private By search = By.xpath("//*[@id='suggestion-search']");

    public ImdbHomePage(WebDriver driver){
        this.driver = driver;
    }

    public void changeCategory(String category){
        driver.findElement(cbCategory).click();
        if (category.equals("All"))
            driver.findElement(all).click();
        else if (category.equals("Titles"))
            driver.findElement(titles).click();
        else if (category.equals("TV Episodes"))
            driver.findElement(tvEpisodes).click();
        else if (category.equals("Celebs"))
            driver.findElement(celebs).click();
        else if (category.equals("Companies"))
            driver.findElement(companies).click();
        else
            driver.findElement(keywords).click();

    }

    public void inputKeyword(String keyword){
        WebElement inputSearch = driver.findElement(search);
        inputSearch.sendKeys(keyword);
        inputSearch.sendKeys(Keys.ENTER);
    }

}
