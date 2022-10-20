package com.jaredbears.loyalty.entity;

import java.util.Comparator;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Transaction implements Comparable<Transaction>{
  
  private Payers payer;
  private int points;
  private Date date;

  @Override
  public int compareTo(Transaction that) {
    return Comparator.comparing(Transaction::getDate).compare(this, that);
  }
}
