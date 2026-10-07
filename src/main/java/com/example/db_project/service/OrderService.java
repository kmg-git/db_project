package com.example.db_project.service;

import com.example.db_project.Repository.*;
import com.example.db_project.domain.*;
import com.example.db_project.dto.OrderLineRequest;
import com.example.db_project.exception.BookNotFoundException;
import com.example.db_project.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;


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

        if (true) throw new RuntimeException("결제 직전 실패");
        paymentRepository.save(new Payment(order,order.getTotalPrice(),method));
        return order.getId();
    }


}
