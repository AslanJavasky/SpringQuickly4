package com.example.purchasedb.TRANSACTIONS.services;

import com.example.purchasedb.TRANSACTIONS.model.Account;
import com.example.purchasedb.TRANSACTIONS.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount){
        Account sender=accountRepository.findAccountById(idSender);
        Account receiver=accountRepository.findAccountById(idReceiver);

        BigDecimal senderNewAmount=sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount=receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender,senderNewAmount);
        accountRepository.changeAmount(idReceiver,receiverNewAmount);

    }

    public List<Account> getAllAccount(){
        return accountRepository.findAllAccounts();
    }

}
