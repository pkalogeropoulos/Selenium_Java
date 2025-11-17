package com.example.demowebshop.pages;

import com.example.demowebshop.pages.menu.AccessoriesPage;
import com.example.demowebshop.pages.menu.DesktopsPage;
import com.example.demowebshop.pages.menu.NotebooksPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComputersPage extends BasePage<ComputersPage> {

    // ===== LOCATORS =====

    // Page title: "Computers"
    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    // Category grid in the main content
    private static final By CATEGORY_GRID =
            By.cssSelector("div.category-grid");

    // Individual subcategory tiles within the grid
    private static final By CATEGORY_TILES =
            By.cssSelector("div.category-grid div.item-box");

    // Each tile's title link (<h2> ... <a href="/desktops">Desktops</a>)
    private static final By CATEGORY_TITLE_LINKS =
            By.cssSelector("div.category-grid div.item-box h2 a");

    // Specific subcategory links
    private static final By DESKTOPS_LINK =
            By.cssSelector("div.sub-category-grid > div.item-box:first-child");

    private static final By NOTEBOOKS_LINK =
            By.cssSelector("div.category-grid a[href='/notebooks']");

    private static final By ACCESSORIES_LINK =
            By.cssSelector("div.category-grid a[href='/accessories']");

    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    // ===== SUBCATEGORY NAVIGATION (MAIN TILES) =====

    /**
     * Clicks the "Desktops" subcategory tile and returns the Desktops page.
     */
    public DesktopsPage goToDesktops() {
        ui.waitUntilClickable(DESKTOPS_LINK).click();
        return new DesktopsPage(driver);
    }

    /**
     * Clicks the "Notebooks" subcategory tile and returns the Notebooks page.
     */
    public NotebooksPage goToNotebooks() {
        ui.waitUntilClickable(NOTEBOOKS_LINK).click();
        return new NotebooksPage(driver);
    }

    /**
     * Clicks the "Accessories" subcategory tile and returns the Accessories page.
     */
    public AccessoriesPage goToAccessories() {
        ui.waitUntilClickable(ACCESSORIES_LINK).click();
        return new AccessoriesPage(driver);
    }
}
