package com.example.demo.ordering.service.impl;

import com.example.demo.ordering.dtos.ProductRequest;
import com.example.demo.ordering.model.Product;
import com.example.demo.ordering.model.ProductType;
import com.example.demo.ordering.repository.ProductRepository;
import com.example.demo.ordering.repository.ProductTypeRepository;
import com.example.demo.ordering.service.ProductService;
import com.example.demo.ordering.validators.exceptions.ExcelException;
import com.example.demo.ordering.validators.exceptions.ProductAlreadyExistsException;
import com.example.demo.ordering.validators.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String JEDINICA_MERE = "Jedinica mere";

    private final ProductRepository productRepository;

    private final ProductTypeRepository productTypeRepository;

    @Override
    @Transactional
    public Product newProduct(ProductRequest productDTO) {
        String productName = productDTO.getName();
        String productType = productDTO.getProductType();

        Optional<Product> optionalProduct = productRepository.findProductByName(productName);

        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product with same name exist in database.");
        }

        Product product = new Product();

        product.setName(productName);
        product.setUnitOfMeasure(productDTO.getUnitOfMeasure());
        product.setProductType(productTypeRepository.findProductTypeByName(productType).orElse(new ProductType(productType)));

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product editProduct(Long id, ProductRequest productDTO) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with this id doesn't exist."));

        product.setName(productDTO.getName());
        product.setUnitOfMeasure(productDTO.getUnitOfMeasure());

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with this id doesn't exist.");
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void importProducts(byte[] file) {
        List<Product> products = new ArrayList<>();

        try {
            InputStream inputStream = new ByteArrayInputStream(file);

            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);

            ProductType productType = null;

            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    Cell nameCell = row.getCell(0);
                    Cell unitOfMeasuteCell = row.getCell(1);
                    if (nameCell != null && unitOfMeasuteCell != null && nameCell.getCellType() != CellType.BLANK
                        && unitOfMeasuteCell.getCellType() != CellType.BLANK) {
                        String name = nameCell.toString();
                        String unitOfMeasure = unitOfMeasuteCell.toString();

                        if (isRowEmpty(row)) {
                            continue;
                        }

                        if (row.getCell(1).toString().equalsIgnoreCase(JEDINICA_MERE)) {
                            String productName = row.getCell(0).toString();
                            Optional<ProductType> productTypeOptional = productTypeRepository.findProductTypeByName(
                                productName);
                            productType = productTypeOptional.orElseGet(
                                () -> productTypeRepository.save(new ProductType(productName)));
                            continue;
                        }

                        products.add(new Product(name, unitOfMeasure, productType));
                    }
                }
            }

            if (!CollectionUtils.isEmpty(products)) {
                productRepository.saveAll(products);
            }
        } catch (IOException e) {
            throw new ExcelException("Can not parse excel file");
        }
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
