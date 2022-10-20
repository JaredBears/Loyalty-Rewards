package com.jaredbears.loyalty.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Incoming extends Transaction{
  
  private int pointsRemaining;
  
  public Incoming(Payers payer, int points, Date date){
    
    this.setPayer(payer);
    this.setPoints(points);
    this.setDate(date);
    this.setPointsRemaining(points);
  }

}