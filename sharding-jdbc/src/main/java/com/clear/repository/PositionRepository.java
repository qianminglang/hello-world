package com.clear.repository;

import com.clear.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position,Long> {
    @Query(nativeQuery = true,value = "select p.id,d.description from position p join position_detail  d on (p.id=d.pid) where p.id=:id")
    Object findPositionsById(@Param("id") long l);
}
