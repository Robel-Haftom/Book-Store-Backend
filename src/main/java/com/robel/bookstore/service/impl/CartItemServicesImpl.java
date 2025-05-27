package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.CartItemCreateDTO;
import com.robel.bookstore.dto.CartItemResponseDTO;
import com.robel.bookstore.entity.Book;
import com.robel.bookstore.entity.Cart;
import com.robel.bookstore.entity.CartItem;
import com.robel.bookstore.exception.*;
import com.robel.bookstore.mapper.CartItemMapper;
import com.robel.bookstore.repository.BookRepository;
import com.robel.bookstore.repository.CartItemRepository;
import com.robel.bookstore.repository.CartRepository;
import com.robel.bookstore.service.CartItemServices;
import com.robel.bookstore.service.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CartItemServicesImpl implements CartItemServices {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartServices cartServices;

    @Override
    public CartItemResponseDTO addCartItem(CartItemCreateDTO cartItemCreateDTO) {
        Cart cart = cartRepository.findByUser_UserId(cartItemCreateDTO.getUserId())
                .orElseThrow(() -> new CartNotFoundException("No cart found with this user id: " + cartItemCreateDTO.getUserId()));

        cartItemRepository.findByBook_BookIdAndCart_CartId(cartItemCreateDTO.getBookId(), cart.getCartId())
                .ifPresent((cartItem) ->{
                    throw new CartItemExistException("cart item is already  in the cart");
                });
        Book book = bookRepository.findById(cartItemCreateDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("No book found with this id: " + cartItemCreateDTO.getBookId()));

        if(book.getStockQuantity() == 0 ){ throw new BookOutOfStockException("this book is sold out");}
        else if(book.getStockQuantity() < cartItemCreateDTO.getQuantity()) { throw new InsufficientStockException("Only " + book.getStockQuantity() + " item of this book left in stock");}
        CartItem cartItem = CartItemMapper.mapToCartItem(cartItemCreateDTO);
        cartItem.setBook(book);
        cartItem.setCart(cart);
        cartItem.setUnitePrice(book.getBookPrice());
        cartItem.setTotalPrice(cartItemCreateDTO.getQuantity() * book.getBookPrice());
        cartItem.setAddedAt(LocalDateTime.now());
        cartItem.setUpdatedAt(LocalDateTime.now());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cartServices.updateCartStatus(cartItem.getCart());

        book.setStockQuantity(book.getStockQuantity()-cartItem.getQuantity());
        bookRepository.save(book);

        return CartItemMapper.mapToCartItemResponseDTO(savedCartItem);
    }

    @Override
    public CartItemResponseDTO updateCartItem(Long cartItemId, Integer quantity) {
        CartItem existingCartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("No cart item found with this id: " + cartItemId));

        if(existingCartItem.getBook().getStockQuantity() == 0 ){ throw new BookOutOfStockException("this book is sold out");}
        else if(existingCartItem.getBook().getStockQuantity() < quantity - existingCartItem.getQuantity()) { throw new InsufficientStockException("Only " + existingCartItem.getBook().getStockQuantity() + " item of this book left in stock");}

        if(!"".equalsIgnoreCase(quantity.toString())){
            existingCartItem.setTotalPrice(quantity * existingCartItem.getBook().getBookPrice());

            existingCartItem.getBook().setStockQuantity(existingCartItem.getBook().getStockQuantity() + existingCartItem.getQuantity() - quantity);

            existingCartItem.setQuantity(quantity);
            existingCartItem.setUpdatedAt(LocalDateTime.now());

            bookRepository.save(existingCartItem.getBook());
            CartItem updatedCartItem = cartItemRepository.save(existingCartItem);

            cartServices.updateCartStatus(existingCartItem.getCart());
            return CartItemMapper.mapToCartItemResponseDTO(updatedCartItem);
        }

        return CartItemMapper.mapToCartItemResponseDTO(existingCartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("No cart item found with this id: " + cartItemId));
        cartItem.getBook().setStockQuantity(cartItem.getBook().getStockQuantity() + cartItem.getQuantity());
        bookRepository.save(cartItem.getBook());
        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);
        cartServices.updateCartStatus(cart);
    }
}
