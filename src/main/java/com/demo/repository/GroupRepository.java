package com.demo.repository;

import com.demo.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ekaterina Pyataeva on 30.04.2017.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{


}
