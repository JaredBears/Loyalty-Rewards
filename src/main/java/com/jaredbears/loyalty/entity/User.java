package com.jaredbears.loyalty.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  
  private String userName;
  private int userId;
  private int pointBalance;
  private ArrayList<Transaction> transactions;
  private int transactionIndex;
  private HashMap<Payers, Integer> payerBalance;
  
  public User(String userName){
    this.userName = userName;
    this.userId = ThreadLocalRandom.current().nextInt(1001, 9999);
    this.pointBalance = 0;
    this.transactions = new ArrayList<>();
    this.transactionIndex = 0;
    this.payerBalance = new HashMap<>();
  }
  
  public void newTransaction(Transaction trans){
    this.transactions.add(trans);
    this.pointBalance += trans.getPoints();
    if(this.payerBalance.containsKey(trans.getPayer())) {
      this.payerBalance.put(trans.getPayer(), this.payerBalance.get(trans.getPayer()) + trans.getPoints());
    } else {
      this.payerBalance.put(trans.getPayer(), trans.getPoints());
    }
    Collections.sort(this.transactions);
  }

}
