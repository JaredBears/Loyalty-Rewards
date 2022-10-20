package com.jaredbears.loyalty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.jaredbears.loyalty.entity.Payers;
import com.jaredbears.loyalty.entity.Transaction;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@Validated
@RequestMapping("/loyalty")
@OpenAPIDefinition(info = @Info(title = "Loyalty Rewards Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
public interface Controller {

  // @formatter: off
  @Operation(summary = "Records an Incoming Transaction", description = "Assigns new points given valid input",
      responses = {@ApiResponse(responseCode = "201", description = "Points are assigned.",
          content = @Content(mediaType = "application/json"))},
      parameters = {
          @Parameter(name = "points", allowEmptyValue = false, required = true,
              description = "The amount of points earned, ie 100"),
          @Parameter(name = "payerName", allowEmptyValue = false, required = true,
              description = "The name of the payer, ie DANNON"),
          @Parameter(name = "date", allowEmptyValue = false, required = true,
              description = "The Timestamp, ie 2022-01-02 01:02:03")})
  @PostMapping("/earned")
  @ResponseStatus(code = HttpStatus.CREATED)
  void incomingTransaction(int points, String payerName, String date);

  @Operation(summary = "Records an Outgoing Transaction", description = "Deducts points given valid input",
      responses = {@ApiResponse(responseCode = "201", description = "Points are deducted.",
          content = @Content(mediaType = "application/json"))},
      parameters = {
          @Parameter(name = "points", allowEmptyValue = false, required = true,
              description = "The amount of points spent, ie 100"),
          @Parameter(name = "date", allowEmptyValue = false, required = true,
              description = "The Timestamp, ie 2022-01-02 01:02:03")})
  @PostMapping("/spent")
  @ResponseStatus(code = HttpStatus.CREATED)
  void outGoingTransaction(int points, String date);

  @Operation(summary = "Displays user's point balance",
      responses = {@ApiResponse(responseCode = "200", description = "Points are returned",
          content = @Content(mediaType = "application/json"))})
  @GetMapping("/points")
  @ResponseStatus(code = HttpStatus.OK)
  int getPointBalance();

  @Operation(summary = "Returns user's point balance by payer",
      responses = {@ApiResponse(responseCode = "200", description = "Points are returned",
          content = @Content(mediaType = "application/json"))})
  @GetMapping("/points_payer")
  @ResponseStatus(code = HttpStatus.OK)
  HashMap<Payers, Integer> getBalanceByPayer();

  @Operation(summary = "Displays user's transaction history",
      responses = {@ApiResponse(responseCode = "200", description = "Transactions are returned",
          content = @Content(mediaType = "application/json"))})
  @GetMapping("/transactions")
  @ResponseStatus(code = HttpStatus.OK)
  ArrayList<Transaction> getTransactionHistory();
  
  @Operation(summary = "Terminates the Application",
      responses = {@ApiResponse(responseCode = "200", description = "Process is Terminated",
          content = @Content(mediaType = "application/json"))})
  @GetMapping("/exit")
  @ResponseStatus(code = HttpStatus.OK)
  void exit();

}
