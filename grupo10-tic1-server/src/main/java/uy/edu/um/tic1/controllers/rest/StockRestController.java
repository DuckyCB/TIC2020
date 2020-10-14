package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.tic1.controllers.ProductController;
import uy.edu.um.tic1.controllers.StockController;
import uy.edu.um.tic1.entities.Product;

@RestController
@RequestMapping("/stock")
public class StockRestController {
    @Autowired
    private StockController stockController;





}
