package com.monk.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.monk.entity.Coupon;
import com.monk.entity.CouponType;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, String> {

	Optional<Coupon> findByType(CouponType type);

	Optional<List<Coupon>> findByExpiresAtAfter(LocalDateTime now);

}