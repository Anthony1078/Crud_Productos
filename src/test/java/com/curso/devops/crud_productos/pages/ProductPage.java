package com.curso.devops.crud_productos.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.By;

public class ProductPage extends PageObject {

    private static final String PAGE_URL = "/productos";

    public void openPage() {
        openAt(PAGE_URL);

        $(By.cssSelector("button.btn.btn-primary")).click();
    }

    public void enterProductName(String name) {
        $(By.id("newProductName")).type(name);
    }

    public void enterProductPrice(int price) {
        $(By.id("newProductPrice")).type(String.valueOf(price));
    }

    public void submitProduct() {
        $(By.xpath("//button[text()='Guardar']")).click();
    }

    public void shouldSeeProductInList(String productName) {
        $(By.xpath("//table//td[contains(text(), '" + productName + "')]")).waitUntilVisible();
    }
}
