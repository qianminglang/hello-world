package com.clear.dao;

import com.clear.entity.BOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BOrderRepository extends JpaRepository<BOrder,Long> {
}
