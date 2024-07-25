package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ProductRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Override
//    public Product updateProduct(Long id, Product product) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isPresent()) {
//            Product existingProduct = optionalProduct.get();
//            existingProduct.setName(product.getName());
//            existingProduct.setDescription(product.getDescription());
//            existingProduct.setPrice(product.getPrice(id));
//            existingProduct.setCategory(product.getCategory());
//            existingProduct.setBrand(product.getBrand());
//            existingProduct.setStockQuantity(product.getStockQuantity());
//            return productRepository.save(existingProduct);
//        } else {
//            return null;
//        }
//    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

	@Override
	public Product updateProduct(Long id, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<Product> getProductPagation(int page, int limit){
        String sql = "SELECT * FROM products LIMIT ? OFFSET ?";
        int offset = (page - 1) * limit;
        var result = jdbcTemplate.query(sql, new Object[]{limit, offset}, new ProductRowMapper());
        if(result != null){
            for(Product item : result) {
                String sql1 = "SELECT image_path FROM product_images where product_id = ?";
                var listImg = jdbcTemplate.queryForList(sql1, new Object[]{item.getId()}, String.class);
                item.setImagePaths(listImg);

                String sql2 = "SELECT type FROM product_types where product_id = ?";
                var ListType = jdbcTemplate.queryForList(sql2, new Object[]{item.getId()}, String.class);
                item.setTypes(ListType);
            }
        }
        return  result;
    }
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setBrand(resultSet.getString("brand"));
            product.setCategory(resultSet.getString("category"));
            product.setDetails(resultSet.getString("details"));
            product.setPrice(resultSet.getDouble("price"));
            product.setStockQuantity(resultSet.getInt("stock_quantity"));
            return  product;
        }
    }
}