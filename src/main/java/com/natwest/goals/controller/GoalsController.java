package com.natwest.goals.controller;

import com.natwest.goals.model.AccountDTO;
import com.natwest.goals.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class GoalsController {

    @Autowired
    GoalsService goalsService;

    @GetMapping("/goals")
    public List<AccountDTO> getAllGoals() {
        return goalsService.getAllGoals();
    }

    @PostMapping("/goals")
    public boolean addGoals(@RequestBody String goalName){
        return goalsService.addGoal(goalName);
    }
}
