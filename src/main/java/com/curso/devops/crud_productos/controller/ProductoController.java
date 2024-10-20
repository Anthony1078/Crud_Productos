package com.curso.devops.crud_productos.controller;

import com.curso.devops.crud_productos.model.Producto;
import com.curso.devops.crud_productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Producto> listarTodosApi() {
        return productoService.listarTodos();
    }

    @PostMapping("/api")
    @ResponseBody
    public Producto crearProductoApi(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Producto obtenerProductoPorIdApi(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Producto actualizarProductoApi(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoService.guardarProducto(producto);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void eliminarProductoApi(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }


}

