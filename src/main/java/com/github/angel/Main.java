package com.github.angel;

import com.github.angel.entity.Product;
import com.github.angel.repository.impl.ProductRepositoryImpl;
import com.github.angel.service.ProductService;
import com.github.angel.service.impl.ProductServiceImpl;


public class Main {
    public static void main(String[] args) {
        Product product = new Product();
        ProductService service = new ProductServiceImpl(new ProductRepositoryImpl());
        // Buscar por Id de producto
       //System.out.println(service.getProductById(70L));


        // Buscar todos los productos
        System.out.println("======== Buscar todos los producto =======");
//        service.getAllProduct().stream()
//                .forEach(System.out::println);

        // Guardar un producto
        System.out.println("======== Guardar un producto =======");
//        service.addProduct(product);

        // Actualizar un producto
        System.out.println("======== Actualizar un producto =======");


        // Eliminar un producto
        System.out.println("======== Eliminar un producto =======");
//        boolean res = service.deleteProductById(70L);
//        System.out.println(res);


    }
}