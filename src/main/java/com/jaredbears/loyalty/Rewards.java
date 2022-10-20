package com.jaredbears.loyalty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jaredbears.loyalty.entity.User;

@SpringBootApplication
public class Rewards {
  
  public static User user;

  public static void main(String[] args) {
    user = new User("TestUser");
    SpringApplication.run(Rewards.class, args);
  }

}
