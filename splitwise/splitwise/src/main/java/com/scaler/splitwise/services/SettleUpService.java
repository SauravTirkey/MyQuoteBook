package com.scaler.splitwise.services;

import com.scaler.splitwise.modles.Expense;
import com.scaler.splitwise.modles.ExpenseUser;
import com.scaler.splitwise.modles.Group;
import com.scaler.splitwise.modles.User;
import com.scaler.splitwise.repositories.ExpenseRepository;
import com.scaler.splitwise.repositories.ExpenseUserRepository;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.repositories.UserRepository;
import com.scaler.splitwise.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

@Autowired
    public SettleUpService( UserRepository userRepository
             ,ExpenseUserRepository expenseUserRepository
              , SettleUpStrategy settleUpStrategy
               ,GroupRepository groupRepository
    ,ExpenseRepository expenseRepository){

        this.userRepository=userRepository;
        this.expenseUserRepository=expenseUserRepository;
        this.settleUpStrategy=settleUpStrategy;
        this.groupRepository=groupRepository;
        this.expenseRepository=expenseRepository;
    }
    public List<Transaction> SettleUpUser(Long userId){

        /*
        * 1.Get the list of expense the user is part of.
        * 2.Iterate through all the expense and find out all the people involved
        * in those expense and generate who owes what. etc.
        * 3.use max and min heap approach to figure out the list of transactions
        * 4.Return the list of the transactions
        *
        * */

       Optional<User> userOptional=userRepository.findById(userId);

       if(userOptional.isEmpty()){
           throw  new RuntimeException();//todo :create specific type of exceptions
       }

       User user =userOptional.get();
       //now we will get the list of expenses
        List<ExpenseUser> expenseUsers=expenseUserRepository.findAllByUser(user);

        List<Expense>  expenses=new ArrayList<>();
        for(ExpenseUser expenseUser:expenseUsers){
            expenses.add(expenseUser.getExpense());
        }

        List<Transaction> transactions=settleUpStrategy.settleUp(expenses);

        /*
        * The List of transactions here will have transactions involving users
        * that are not the given user(userId parameter).So, we wil have to filter
        * out the transactions to only include the persons for whom we are calculating this.
        * */

        List<Transaction> filteredTransactions=new ArrayList<>();
        for(Transaction transaction:transactions){//With the present annotations @Getter and @Setter on
            // the modles the equals() comparison on user object would not work we
           // would need to replace @Getter and @Setter with @Data or we ca implement hashcode and equals.
            if(transaction.getFrom().equals(user)||transaction.getTo().equals(user)){
                filteredTransactions.add(transaction);
            }
        }
        return  filteredTransactions;
    }

    //To SettleUp a group (Everyone in the group can see the transaction)
    public List<Transaction> SettleUpGroup(Long groupId){

      Optional<Group> groupOptional=groupRepository.findById(groupId);
      if(groupOptional.isEmpty()){
          throw new RuntimeException();//In actual interview user defined exceptions
      }

      Group group=groupOptional.get();//we have crfeated the Expoense repository but we dont need that
        // because we have group repository.
        List<Expense> expenses=group.getExpenses();
        List<Transaction> transactions=settleUpStrategy.settleUp(expenses);


        return  transactions;
    }

}
