package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.tic1.controllers.StockController;


@RestController
@RequestMapping("/stock")

public class StockRestController {

    @Autowired
    private StockController stockController;





}
