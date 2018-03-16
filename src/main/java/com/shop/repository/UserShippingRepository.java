package com.shop.repository;

import com.shop.domain.UserShipping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShippingRepository extends CrudRepository<UserShipping,Long>{

    UserShipping findById(Long id);
}
