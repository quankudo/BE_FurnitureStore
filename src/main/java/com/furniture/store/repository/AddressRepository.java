package com.furniture.store.repository;

import com.furniture.store.entity.Address;
import com.furniture.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT 1 " +
            "FROM tbl_address a " +
            "WHERE a.user_id = :userId " +
            "  AND a.detail = :detail " +
            "  AND a.city_id = :cityId " +
            "  AND a.district_id = :districtId " +
            "  AND a.phone_number = :phoneNumber " +
            "  AND a.address_name = :addressName " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<Integer> existsAddress(
            @Param("userId") String userId,
            @Param("detail") String detail,
            @Param("cityId") Long cityId,
            @Param("districtId") Long districtId,
            @Param("phoneNumber") String phoneNumber,
            @Param("addressName") String addressName);

    Optional<Address> findByUserAndIsDefaultTrue(User user);

    List<Address> findByUser_Id(String userId);

    Optional<Address> findByIdAndUser_Id(Long addressId, String userId);
}
