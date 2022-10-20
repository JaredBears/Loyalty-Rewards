package com.jaredbears.loyalty.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import com.jaredbears.loyalty.Rewards;
import com.jaredbears.loyalty.entity.Payers;
import com.jaredbears.loyalty.entity.Transaction;
import com.jaredbears.loyalty.service.Service;

@RestController
public class DefaultController implements Controller {
  @Override
  public void incomingTransaction(int points, String payerName, String date) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Payers payer = Payers.valueOf(payerName.toUpperCase());
    Service service = new Service();
    try {
      Date time = formatter.parse(date);
      service.incomingTransaction(Rewards.user, payer, points, time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void outGoingTransaction(int points, String date) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Service service = new Service();
    try {
      Date time = formatter.parse(date);
      try {
        service.outGoingTransaction(Rewards.user, points * -1, time);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getPointBalance() {
    Service service = new Service();
    return service.getPointBalance(Rewards.user);

  }

  @Override
  public HashMap<Payers, Integer> getBalanceByPayer() {
    Service service = new Service();
    return service.getBalanceByPayer(Rewards.user);
  }

  @Override
  public ArrayList<Transaction> getTransactionHistory() {
    Service service = new Service();
    return service.getTransactionHistory(Rewards.user);
  }

  @Override
  public void exit() {
    System.exit(0);
    
  }

}
