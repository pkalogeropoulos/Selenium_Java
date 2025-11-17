package com.example.demowebshop.components;

import com.example.demowebshop.pages.ComputersPage;
import com.example.demowebshop.pages.menu.*;
import com.example.demowebshop.ui.UserActionsUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderMenuBar<T> {

    private final WebDriver driver;
    private UserActionsUI ui;

    // ===== TOP-LEVEL MENU LOCATORS =====
    private static final By BOOKS_LINK =
            By.cssSelector("ul.top-menu a[href='/books']");

    private static final By COMPUTERS_LINK =
            By.cssSelector("ul.top-menu a[href='/computers']");

    private static final By ELECTRONICS_LINK =
            By.cssSelector("ul.top-menu a[href='/electronics']");

    private static final By APPAREL_SHOES_LINK =
            By.cssSelector("ul.top-menu a[href='/apparel-shoes']");

    private static final By DIGITAL_DOWNLOADS_LINK =
            By.cssSelector("ul.top-menu a[href='/digital-downloads']");

    private static final By JEWELRY_LINK =
            By.cssSelector("ul.top-menu a[href='/jewelry']");

    private static final By GIFT_CARDS_LINK =
            By.cssSelector("ul.top-menu a[href='/gift-cards']");

    // ===== SUB-MENU LOCATORS (UNDER COMPUTERS & ELECTRONICS) =====
    private static final By COMPUTERS_DESKTOPS_LINK =
            By.cssSelector("ul.top-menu a[href='/desktops']");

    private static final By COMPUTERS_NOTEBOOKS_LINK =
            By.cssSelector("ul.top-menu a[href='/notebooks']");

    private static final By COMPUTERS_ACCESSORIES_LINK =
            By.cssSelector("ul.top-menu a[href='/accessories']");

    private static final By ELECTRONICS_CAMERA_LINK =
            By.cssSelector("ul.top-menu a[href='/camera-photo']");

    private static final By ELECTRONICS_CELL_PHONES_LINK =
            By.cssSelector("ul.top-menu a[href='/cell-phones']");

    public HeaderMenuBar(WebDriver driver, UserActionsUI ui) {
        this.driver = driver;
        this.ui = ui;
    }


    // ===== TOP-LEVEL NAVIGATION =====

    public BooksPage goToBooks() {
        ui.waitUntilClickable(BOOKS_LINK).click();
        return new BooksPage(driver);
    }

    public ComputersPage goToComputers() {
        ui.waitUntilClickable(COMPUTERS_LINK).click();
        return new ComputersPage(driver);
    }

    public ElectronicsPage goToElectronics() {
        ui.waitUntilClickable(ELECTRONICS_LINK).click();
        return new ElectronicsPage(driver);
    }

    public ApparelShoesPage goToApparelAndShoes() {
        ui.waitUntilClickable(APPAREL_SHOES_LINK).click();
        return new ApparelShoesPage(driver);
    }

    public DigitalDownloadsPage goToDigitalDownloads() {
        ui.waitUntilClickable(DIGITAL_DOWNLOADS_LINK).click();
        return new DigitalDownloadsPage(driver);
    }

    public JewelryPage goToJewelry() {
        ui.waitUntilClickable(JEWELRY_LINK).click();
        return new JewelryPage(driver);
    }

    public GiftCardsPage goToGiftCards() {
        ui.waitUntilClickable(GIFT_CARDS_LINK).click();
        return new GiftCardsPage(driver);
    }

    // ===== SUB-MENU NAVIGATION (WITH HOVER) =====

    public DesktopsPage goToDesktops() {
        ui.hover(COMPUTERS_LINK);
        ui.waitUntilClickable(COMPUTERS_DESKTOPS_LINK).click();
        return new DesktopsPage(driver);
    }

    public NotebooksPage goToNotebooks() {
        ui.hover(COMPUTERS_LINK);
        ui.waitUntilClickable(COMPUTERS_NOTEBOOKS_LINK).click();
        return new NotebooksPage(driver);
    }

    public AccessoriesPage goToAccessories() {
        ui.hover(COMPUTERS_LINK);
        ui.waitUntilClickable(COMPUTERS_ACCESSORIES_LINK).click();
        return new AccessoriesPage(driver);
    }

    public CameraPhotoPage goToCameraPhoto() {
        ui.hover(ELECTRONICS_LINK);
        ui.waitUntilClickable(ELECTRONICS_CAMERA_LINK).click();
        return new CameraPhotoPage(driver);
    }

    public CellPhonesPage goToCellPhones() {
        ui.hover(ELECTRONICS_LINK);
        ui.waitUntilClickable(ELECTRONICS_CELL_PHONES_LINK).click();
        return new CellPhonesPage(driver);
    }
}
