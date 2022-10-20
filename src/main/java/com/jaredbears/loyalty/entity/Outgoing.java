package com.jaredbears.loyalty.entity;

import java.util.Date;

public class Outgoing extends Transaction{

  public Outgoing(Payers payer, int points, Date date){
    this.setPayer(payer);
    this.setPoints(points);
    this.setDate(date);
  }

}
