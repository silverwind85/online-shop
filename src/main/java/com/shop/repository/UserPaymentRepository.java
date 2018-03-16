package com.shop.repository;

import com.shop.domain.UserPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentRepository extends CrudRepository<UserPayment,Long> {

    UserPayment findById(Long id);
}
