package com.natwest.goals.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private String ConsentId;
    private String CreationDateTime;
    private String status;
    private String statusUpdateDateTime;
    private List<String> permissions;
    private List<Account> Account;
    private Links Links;
    private Meta Meta;
}