package com.fundplex.mainrestapi.state;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

@Service

public class StateService {
    @Autowired
    public StateRepo stateRepo;

    public void createState(State state) {
        this.stateRepo.save(state);
    }

    public void updateState(State state, Long id) {
        this.stateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("State", "Id", id));
        state.setId(id);
        this.stateRepo.save(state);
    }

    public void deleteState(Long id) {
        this.stateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("State", "Id", id));
        this.stateRepo.deleteById(id);
    }

    public List<State> getAllStates() {
        return this.stateRepo.findAll();
    }

    public Optional<State> getStateById(Long id) {
        return this.stateRepo.findById(id);
    }

    public List<State> getStateByCountryId(Long id) {
        return this.stateRepo.findByCountryId(id);
    }

}
