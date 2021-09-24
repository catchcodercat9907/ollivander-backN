package io.ollivander.ollivanderbackend.services;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.model.dto.BaseListRequest;
import io.ollivander.ollivanderbackend.model.entities.Product;
import io.ollivander.ollivanderbackend.model.repos.ProductRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepo;

    @Override
    public Optional<Product> getProductById(Integer productId) throws BaseException {

        return Optional.ofNullable(productRepo.findById(productId).orElseThrow(() -> new BaseException(ErrorInfo.PRODUCT_NOT_FOUND)));
    }

    @Override
    public Object getAllProducts(BaseListRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(),request.getPageSize());
        List<Product> products = productRepo.getAllProducts(pageable);
//        List<Map<Object, Object>> list = CollectionUtils.isEmpty(products) ? null :products.stream().map(product -> {
//            Map<Object, Object> mapPrd = new HashMap<>();
//            mapPrd = product.toSimpleObject();
//            return mapPrd;
//        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public List<Product> search(BaseListRequest request) {
        return null;
    }
}
