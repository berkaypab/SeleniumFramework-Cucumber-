package diastek.stepdefinitions;

import diastek.context.TestContext;
import diastek.pages.CartPage;
import diastek.pages.HomePage;
import diastek.pages.ProductDetailPage;
import diastek.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HepsiburadaStepDefinitions {

    private final TestContext context;
    private HomePage homePage;
    private ProductPage tabletPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;

    private String productPrice;
    private String productName;

    public HepsiburadaStepDefinitions(TestContext context) {
        this.context = context;
        homePage = new HomePage(context.getDriver());
    }

    @Given("I navigate to Hepsiburada homepage")
    public void iNavigateToHepsiburadaHomepage() throws InterruptedException {
        homePage.load();
        homePage.acceptCookiesIfPresent();
    }

    @When("I navigate to AllCategories and Electronic category")
    public void iNavigateToCategoryPath() throws InterruptedException {
        homePage.navigateThroughCategories();
        tabletPage = new ProductPage(context.getDriver());
    }

    @And("I filter by brand Apple and screen size 13,3 inç")
    public void iFilterByBrandAndScreenSize() throws InterruptedException {
        tabletPage.filterByBrand()
                .filterByScreenSize();
    }

    @And("I select the most expensive product from the results")
    public void iSelectTheMostExpensiveProduct() throws InterruptedException {
        productDetailPage = tabletPage.selectMostExpensiveProduct();
        // Store product details for later verification
        productName = productDetailPage.getProductName();
        productPrice = productDetailPage.getProductPrice();
    }

    @And("I click on Add to Cart button on the product details page")
    public void iClickOnAddToCartButton() throws InterruptedException {
        productDetailPage.addToCart();
    }

    @Then("I should see the product is added to the cart")
    public void iShouldSeeTheProductIsAddedToTheCart() throws InterruptedException {
        cartPage = productDetailPage.goToCart();
        boolean productInCart = cartPage.isProductInCart(productName);
        Assert.assertTrue(productInCart, "Product was not found in the cart");
    }

    @And("The price in the cart should match the price on the product details page")
    public void thePriceInTheCartShouldMatchThePrice() {
        boolean priceMatches = cartPage.isProductPriceMatching(productPrice);
        Assert.assertTrue(priceMatches, "Product price in cart doesn't match the price on product page");
    }
}