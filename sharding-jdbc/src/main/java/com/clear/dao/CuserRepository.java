package com.clear.dao;

import com.clear.entity.CUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuserRepository extends JpaRepository<CUser,Long> {
    List<CUser> findByPwd(String abc);
}
