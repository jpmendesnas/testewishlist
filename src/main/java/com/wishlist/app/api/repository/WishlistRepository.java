package com.wishlist.app.api.repository;

import com.wishlist.app.api.domain.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {


    Optional<Wishlist> findByClientId(String clientId);
}
