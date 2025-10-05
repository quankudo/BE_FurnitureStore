package com.furniture.store.specification;

import com.furniture.store.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasName(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) return null;
            return cb.or(
                    cb.like(cb.lower(root.get("firstName")), "%" + keyword.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("lastName")), "%" + keyword.toLowerCase() + "%")
            );
        };
    }

    public static Specification<User> hasRole(String roleName) {
        return (root, query, cb) -> {
            if (roleName == null || roleName.isBlank()) return null;
            assert query != null;
            query.distinct(true);
            return cb.equal(root.join("roles").get("name"), roleName);
        };
    }

    public static Specification<User> hasPermission(String permissionName) {
        return (root, query, cb) -> {
            if (permissionName == null || permissionName.isBlank()) return null;
            assert query != null;
            query.distinct(true);
            return cb.equal(root.join("permissions").get("name"), permissionName);
        };
    }
}
