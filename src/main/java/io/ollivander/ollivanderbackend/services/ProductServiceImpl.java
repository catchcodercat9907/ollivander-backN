package io.ollivander.ollivanderbackend.services;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.model.dto.BaseListRequest;
import io.ollivander.ollivanderbackend.model.entities.Product;
import io.ollivander.ollivanderbackend.model.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepo;

    @Override
    public Optional<Product> getProductById(Integer productId) throws BaseException {

        return Optional.ofNullable(productRepo.findById(productId).orElseThrow(() -> new BaseException(ErrorInfo.PRODUCT_NOT_FOUND)));
    }

    @Override
    public List<Product> getAllProducts(BaseListRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(),request.getPageSize());
        return productRepo.getAllProducts(pageable);
    }

    @Override
    public List<Product> search(BaseListRequest request) {
        return null;
    }
}
