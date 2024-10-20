@productos
Feature: Gestión de Productos

  @agregarProducto
  Scenario: Agregar un nuevo producto
    Given Estoy en la página de creación de productos
    When Envío un nuevo producto con el nombre "Nuevo Producto Demo" y el precio 250
    Then Debería ver "Nuevo Producto Demo" en la lista de productos
