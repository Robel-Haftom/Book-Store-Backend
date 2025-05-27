package com.robel.bookstore.repository;

import com.robel.bookstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//    @Query("SELECT ci FROM CartItem ci WHERE ci.book.bookId = :bookId AND ci.cart.cartId = :cartId")
//    Optional<CartItem> findByBookIdAndCartId(@Param("bookId") Long bookId, @Param("cartId") Long cartId);

//    @Query("SELECT ci FROM CartItem ci WHERE ci.book.bookId = ?1 AND ci.cart.cartId = ?2")
//    Optional<CartItem> findByBookIdAndCartId(Long bookId, Long cartId);

//    @Query( value = "SELECT * FROM cart_items WHERE book_bookId = ?1 AND cart_cartId = ?2", nativeQuery = true)
//    Optional<CartItem> findByBookIdAndCartId(Long bookId, Long cartId);

//   @Query( value = "SELECT * FROM cart_items WHERE book_bookId = :bookId AND cart_cartId = :cartId", nativeQuery = true)
//    Optional<CartItem> findByBookIdAndCartId(@Param("bookId") Long bookId, @Param("cartId") Long cartId);

    Optional<CartItem> findByBook_BookIdAndCart_CartId(Long bookId, Long cartId);

}
