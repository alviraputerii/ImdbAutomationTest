package org.blibli.future.tests;

import org.blibli.future.pages.ImdbSearchResultPage;
import org.blibli.future.pages.ImdbHomePage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest 
{
    private WebDriver driver;
    private ImdbHomePage homePage;
    private ImdbSearchResultPage searchPage;
    private String validWord = "lupin";
    private String randomWord = "asdf12345678";
    private String simbol = "@#$%^";

    @BeforeClass
    public static void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\BliBli Future Program\\Technical\\chromedriver_win32\\chromedriver.exe");
    }

    @Before
    public void before(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com");
    }

    @Test
    public void validSearchByCategoryAll() {
        homePage = new ImdbHomePage(driver);
        homePage.inputKeyword(validWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + validWord + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("titles", validWord));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("names", validWord));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("keyowrds", validWord));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("companies", validWord));
        Assert.assertEquals("Titles",searchPage.viewMore("Titles"));
        homePage.changeCategory("All");
        homePage.inputKeyword(validWord);
        Assert.assertEquals("Celebs",searchPage.viewMore("Celebs"));
    }

    @Test
    public void randomSearchByCategoryAll() {
        homePage = new ImdbHomePage(driver);
        homePage.inputKeyword(randomWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + randomWord + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void simbolSearchByCategoryAll() {
        homePage = new ImdbHomePage(driver);
        homePage.inputKeyword(simbol);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + simbol + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void validSearchByCategoryTitles() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Titles");
        homePage.inputKeyword(validWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + validWord + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkSubTitles("Titles"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("titles", validWord));
        Assert.assertEquals(searchPage.checkTotalResult() , searchPage.checkDisplayedTotalResult());
        Assert.assertTrue(searchPage.viewMatches("Exact"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("titles", validWord));
        Assert.assertTrue(searchPage.viewMatches("Popular"));
    }

    @Test
    public void randomSearchByCategoryTitles() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Titles");
        homePage.inputKeyword(randomWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + randomWord + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void simbolSearchByCategoryTitles() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Titles");
        homePage.inputKeyword(simbol);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + simbol + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void validSearchByCategoryTvEpisodes() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("TV Episodes");
        homePage.inputKeyword(validWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + validWord + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkSubTitles("TV Episodes"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("titles", validWord));
        Assert.assertEquals( searchPage.checkTotalResult() , searchPage.checkDisplayedTotalResult());
    }

    @Test
    public void randomSearchByCategoryTvEpisodes() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("TV Episodes");
        homePage.inputKeyword(randomWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + randomWord + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void simbolSearchByCategoryTvEpisodes() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("TV Episodes");
        homePage.inputKeyword(simbol);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + simbol + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void validSearchByCategoryCelebs() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Celebs");
        homePage.inputKeyword(validWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + validWord + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkSubTitles("Celebs"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("namesPage", validWord));
        Assert.assertEquals( searchPage.checkTotalResult() , searchPage.checkDisplayedTotalResult());
        Assert.assertTrue(searchPage.viewMatches("Exact"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("namesPage", validWord));
        Assert.assertTrue(searchPage.viewMatches("Popular"));
    }

    @Test
    public void randomSearchByCategoryCelebs() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Celebs");
        homePage.inputKeyword(randomWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + randomWord + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void simbolSearchByCategoryCelebs() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Celebs");
        homePage.inputKeyword(simbol);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + simbol + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void validSearchByCategoryCompanies() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Companies");
        homePage.inputKeyword(validWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + validWord + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkSubTitles("Companies"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("companiesPage", validWord));
        Assert.assertEquals( searchPage.checkTotalResult() , searchPage.checkDisplayedTotalResult());
    }

    @Test
    public void randomSearchByCategoryCompanies() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Companies");
        homePage.inputKeyword(randomWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + randomWord + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void simbolSearchByCategoryCompanies() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Companies");
        homePage.inputKeyword(simbol);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + simbol + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void validSearchByCategoryKeywords() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Keywords");
        homePage.inputKeyword(validWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + validWord + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkSubTitles("Keywords"));
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("keywordsPage", validWord));
        Assert.assertEquals( searchPage.checkTotalResult() , searchPage.checkDisplayedTotalResult());

    }

    @Test
    public void randomSearchByCategoryKeywords() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Keywords");
        homePage.inputKeyword(randomWord);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + randomWord + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @Test
    public void simbolSearchByCategoryKeywords() {
        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Keywords");
        homePage.inputKeyword(simbol);

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("No results found for \"" + simbol + "\"", searchPage.checkInvalidResultTitle());
        Assert.assertNotNull(searchPage.checkNoTable());
    }

    @After
    public void after(){
        driver.quit();
    }
}
