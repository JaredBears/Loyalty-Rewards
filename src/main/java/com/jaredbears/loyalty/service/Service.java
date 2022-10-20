package com.jaredbears.loyalty.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import com.jaredbears.loyalty.entity.Incoming;
import com.jaredbears.loyalty.entity.Outgoing;
import com.jaredbears.loyalty.entity.Payers;
import com.jaredbears.loyalty.entity.Transaction;
import com.jaredbears.loyalty.entity.User;

public class Service{
  
  public void incomingTransaction(User user, Payers payer, int points, Date date){
    Transaction trans = new Incoming(payer, points, date);
    user.newTransaction(trans);
  }
  
  public void outGoingTransaction(User user, int points, Date date) throws Exception {
    if (Math.abs(points) > user.getPointBalance()) {
      throw new Exception("Insufficient Point Balance");
    }
    Transaction currTransaction = user.getTransactions().get(user.getTransactionIndex());
    while(currTransaction instanceof Outgoing) {
      user.setTransactionIndex(user.getTransactionIndex() + 1);
      currTransaction = user.getTransactions().get(user.getTransactionIndex());
    }
    if(((Incoming) currTransaction).getPointsRemaining() >= Math.abs(points)) {
      ((Incoming) currTransaction).setPointsRemaining(((Incoming) currTransaction).getPointsRemaining() + points);
      Transaction trans = new Outgoing(currTransaction.getPayer(), points, date);
      user.newTransaction(trans);
    }
    else {
      int transBalance = ((Incoming) currTransaction).getPointsRemaining();
      points += transBalance;
      ((Incoming) currTransaction).setPointsRemaining(0);
      user.setTransactionIndex(user.getTransactionIndex() + 1);
      
      Transaction trans = new Outgoing(currTransaction.getPayer(), transBalance * -1, date);
      user.newTransaction(trans);
      outGoingTransaction(user, points, date);
    }
  }
  
  public int getPointBalance(User user) {
    return user.getPointBalance();
  }
  
  public HashMap<Payers, Integer> getBalanceByPayer(User user){
    return user.getPayerBalance();
  }
  
  public ArrayList<Transaction> getTransactionHistory(User user){
    return user.getTransactions();
  }

}
