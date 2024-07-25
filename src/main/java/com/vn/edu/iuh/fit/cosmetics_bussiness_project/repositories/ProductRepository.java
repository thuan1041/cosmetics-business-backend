package com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}