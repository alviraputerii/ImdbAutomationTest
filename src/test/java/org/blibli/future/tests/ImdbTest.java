package org.blibli.future.tests;

import io.restassured.response.Response;
import org.blibli.future.controller.ImdbController;
import org.blibli.future.pages.ImdbHomePage;
import org.blibli.future.pages.ImdbSearchResultPage;
import org.blibli.future.responses.GetDirectorResponse;
import org.blibli.future.responses.GetFilmResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ImdbTest {
    private ImdbController imdbController;
    private WebDriver driver;
    private ImdbHomePage homePage;
    private ImdbSearchResultPage searchPage;

    @BeforeClass
    public static void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\BliBli Future Program\\Technical\\chromedriver_win32\\chromedriver.exe");
    }

    @Before
    public void setup(){
        imdbController = new ImdbController();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com");
    }

    @Test
    public void getFilmTest(){
        Response response = imdbController.getFilm();

        assertThat("Status code is not 200", response.getStatusCode(), equalTo(200));

        GetFilmResponse getRandomFilmResponse = response.getBody().as(GetFilmResponse.class);

        homePage = new ImdbHomePage(driver);
        homePage.inputKeyword(getRandomFilmResponse.getData());

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + getRandomFilmResponse.getData() + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("titles", getRandomFilmResponse.getData()));
    }

    @Test
    public void getDirectorTest(){
        Response film = imdbController.getFilm();
        GetFilmResponse getRandomFilmResponse = film.getBody().as(GetFilmResponse.class);

        Response director  = imdbController.getDirector(getRandomFilmResponse.getData());

        assertThat("Status code is not 200", director.getStatusCode(), equalTo(200));

        GetDirectorResponse getDirectorNameResponse = director.getBody().as(GetDirectorResponse.class);

        homePage = new ImdbHomePage(driver);
        homePage.changeCategory("Celebs");
        homePage.inputKeyword(getDirectorNameResponse.getData());

        searchPage = new ImdbSearchResultPage(driver);
        Assert.assertEquals("\"" + getDirectorNameResponse.getData() + "\"", searchPage.checkValidResultTitle());
        Assert.assertTrue(searchPage.checkTableDataContainKeyword("namesPage", getDirectorNameResponse.getData()));
    }

    @After
    public void after(){
        driver.quit();
    }
}
