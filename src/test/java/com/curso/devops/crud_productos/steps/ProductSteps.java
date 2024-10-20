package com.curso.devops.crud_productos.steps;

import com.curso.devops.crud_productos.pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

//import net.thucydides.core.annotations.Step;
//import net.thucydides.core.annotations.Steps;

public class ProductSteps {

    @Steps
    ProductPage productPage;

    @Given("Estoy en la página de creación de productos")
    public void estoy_en_la_pagina_de_creacion_de_productos() {
        productPage.openPage();
    }

    @When("Envío un nuevo producto con el nombre {string} y el precio {int}")
    public void envio_un_nuevo_producto_con_nombre_y_precio(String name, int price) {
        productPage.enterProductName(name);
        productPage.enterProductPrice(price);
        productPage.submitProduct();
    }

    @Then("Debería ver {string} en la lista de productos")
    public void deberia_ver_el_producto_en_la_lista(String productName) {
        productPage.shouldSeeProductInList(productName);
    }
}
