package com.example.db_project.service;

import com.example.db_project.Repository.*;
import com.example.db_project.domain.*;
import com.example.db_project.dto.OrderLineRequest;
import com.example.db_project.dto.OrderResponse;
import com.example.db_project.exception.BookNotFoundException;
import com.example.db_project.exception.MemberNotFoundException;
import com.example.db_project.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;


    @Transactional
    public Long order(Long memberId, List<OrderLineRequest> lines, Payment.PayMethod method){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        Order order = Order.create(member);

        for(OrderLineRequest request : lines){
            Book book = bookRepository.findById(request.getBookId()).
                    orElseThrow(() -> new BookNotFoundException(request.getBookId()));
            book.removeStock(request.getQuantity());
            //orderItemRepository.save(new OrderItem(book,request.getQuantity()));
            order.addOrderItem(new OrderItem(book, request.getQuantity()));
        }

        orderRepository.save(order);

        paymentRepository.save(new Payment(order,order.getTotalPrice(),method));
        return order.getId();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId){
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new OrderNotFoundException(orderId));
        return OrderResponse.from(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders(){
        List<Order> orders = orderRepository.findAll();

        List<OrderResponse> responses = orders.stream().map(order -> OrderResponse.from(order)).toList();

        return responses;
    }



}
