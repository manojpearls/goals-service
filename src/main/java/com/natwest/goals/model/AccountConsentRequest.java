package com.natwest.goals.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountConsentRequest {
    private Data data;
    private Risk risk;
    private Links links;
    private Meta meta;
}

