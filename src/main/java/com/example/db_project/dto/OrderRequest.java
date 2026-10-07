package com.example.db_project.dto;

import com.example.db_project.domain.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequest {

    private Long memberId;
    private List<OrderLineRequest> lines;
    private Payment.PayMethod method;
}