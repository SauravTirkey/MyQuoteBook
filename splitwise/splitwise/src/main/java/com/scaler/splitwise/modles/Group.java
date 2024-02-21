package com.scaler.splitwise.modles;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> members;
   //whatever is the relation between group and expense
    //is also represented on the expense side as well. put that variable inside "mappedBy"
    //and we are always writing it on the ontto many
   @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
