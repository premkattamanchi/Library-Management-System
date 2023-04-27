package com.example.Library_management.Controllers;

import com.example.Library_management.DTOs.RequestDto.IssueBookRequestDto;
import com.example.Library_management.Service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionsService transactionsService;
    @PostMapping("issue_book")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        try{
            return transactionsService.issueBook(issueBookRequestDto);
        }
        catch(Exception e){
            return e.toString();
        }
    }
    @PostMapping("/return_book")
    public String returnBook(@RequestParam int cardId,@RequestParam int bookId){
        try{
            return transactionsService.returnBook(cardId,bookId);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/calculate_fine")
    public int calculateFine(@RequestParam int cardId,@RequestParam int bookId){
        int fine=0;
        try{
            fine=transactionsService.calculateFine(cardId,bookId);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return fine;
    }
}
