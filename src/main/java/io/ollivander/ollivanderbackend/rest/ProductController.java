package io.ollivander.ollivanderbackend.rest;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.model.dto.BaseListRequest;
import io.ollivander.ollivanderbackend.model.dto.ResponseObject;
import io.ollivander.ollivanderbackend.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productSv;

    @GetMapping()
    public ResponseEntity<Object> getAllProduct(BaseListRequest request) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        Object product = productSv.getAllProducts(request);
        response.setResponseData(product);
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable(name = "productId") Integer productId) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        try {
            Object product = productSv.getProductById(productId);
            response.setResponseData(product);
        } catch (BaseException e) {
            response.setError(e.getError());
        }
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
