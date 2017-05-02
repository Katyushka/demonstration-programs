package com.demo.repository;

import com.demo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
