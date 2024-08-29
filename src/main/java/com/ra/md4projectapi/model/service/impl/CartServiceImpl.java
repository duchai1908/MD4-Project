package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.model.dto.request.CartItemRequest;
import com.ra.md4projectapi.model.entity.CartItem;
import com.ra.md4projectapi.model.entity.Orders;
import com.ra.md4projectapi.model.repository.ICartUserRepository;
import com.ra.md4projectapi.model.service.ICartService;
import com.ra.md4projectapi.model.service.IProductService;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final ICartUserRepository cartUserRepository;
    private final IUserService userService;
    private final IProductService productService;

    // Add to cart
    @Override
    public CartItem add(CartItemRequest cartItemRequest) {
        CartItem cartItem = new CartItem();
        if(cartUserRepository.findCartItemByUserIdAndProductId(cartItemRequest.getUser(),cartItemRequest.getProduct()) != null){
               cartItem = cartUserRepository.findCartItemByUserIdAndProductId(cartItemRequest.getUser(),cartItemRequest.getProduct());
               cartItem.setQuantity(cartItemRequest.getQuantity() + cartItem.getQuantity());
        }else{
            cartItem = CartItem.builder()
                    .user(userService.getUserById(cartItemRequest.getUser()))
                    .product(productService.findByProductId(cartItemRequest.getProduct()))
                    .quantity(cartItemRequest.getQuantity())
                    .build();
        }

       return cartUserRepository.save(cartItem);
    }

    // List Cart
    @Override
    public List<CartItem> getCartItems(Long userId) {
        return cartUserRepository.findCartItemByUserId(userId);
    }

    // Change Quantity
    @Override
    public CartItem changeQuantity(Long cartItemId, Integer quantity) {
        CartItem item = cartUserRepository.findById(cartItemId).orElseThrow(()-> new NoSuchElementException("Cart not found"));
        item.setQuantity(quantity);
        return cartUserRepository.save(item);
    }

    // Delete CartItem By Id
    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem item = cartUserRepository.findById(cartItemId).orElseThrow(()-> new NoSuchElementException("Cart not found"));
        cartUserRepository.delete(item);
    }

    // Delete All CartItem
    @Override
    public void deleteAllCartItems(Long userId) {
        cartUserRepository.deleteAllByUserId(userId);
    }

    // Add to Orders
    @Override
    public Orders addOrders(Long userId) {
        Orders orders = new Orders();
        return orders;
    }
}
