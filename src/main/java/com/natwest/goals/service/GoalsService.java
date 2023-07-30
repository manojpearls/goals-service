package com.natwest.goals.service;

import com.natwest.goals.client.AuthClient;
import com.natwest.goals.model.AccessToken;
import com.natwest.goals.model.Account;
import com.natwest.goals.model.AccountDTO;
import com.natwest.goals.model.WellKnownEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalsService {

    @Autowired
    AuthClient authClient;

    public boolean addGoal(String goalName){
        WellKnownEndPoint wellKnownEndPoint = authClient.getWellKnownEndPoint();
        AccessToken accessToken = authClient.getAccessToken("accounts",wellKnownEndPoint.getToken_endpoint());
        String consentId= authClient.getAccountConsent(accessToken.getAccess_token());
        String authorizationCode = authClient.approveConsent(wellKnownEndPoint.getAuthorization_endpoint(),consentId);
        authorizationCode = authClient.getExchangeAccessToken(wellKnownEndPoint.getToken_endpoint(), authorizationCode);
        return authClient.addAccount(goalName,authorizationCode);
    }

    public List<AccountDTO> getAllGoals(){
        WellKnownEndPoint wellKnownEndPoint = authClient.getWellKnownEndPoint();
        AccessToken accessToken = authClient.getAccessToken("accounts",wellKnownEndPoint.getToken_endpoint());
        String consentId= authClient.getAccountConsent(accessToken.getAccess_token());
        String authorizationCode = authClient.approveConsent(wellKnownEndPoint.getAuthorization_endpoint(),consentId);
        authorizationCode = authClient.getExchangeAccessToken(wellKnownEndPoint.getToken_endpoint(), authorizationCode);

        List<Account> accounts = authClient.getAccountList(authorizationCode);
        accounts = accounts.stream().filter(a -> a.getNickname().startsWith("Goal")).map(acc -> {
            acc.setNickname(acc.getNickname().replace("Goal","").trim());
            return acc;
        }).collect(Collectors.toList());

        List<AccountDTO> accountDTOList = new ArrayList<>();

        if (accounts != null) {
            for (Account account : accounts) {
                AccountDTO accountDTO = new AccountDTO(account.getNickname(), account.getAccountId(),account.getAccountType(),account.getAccountSubType(),account.getDescription(),authorizationCode);
                accountDTOList.add(accountDTO);
            }
        }
        return accountDTOList;
    }

}
