package com.eightxeight.fibonacci.data;

import com.eightxeight.fibonacci.model.BlacklistedNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedRepository extends JpaRepository<BlacklistedNumberEntity, Integer> {
}
