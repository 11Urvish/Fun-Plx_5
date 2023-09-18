package com.fundplex.mainrestapi.state;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.exceptions.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/state")

public class StateController {

    @Autowired
    public StateService stateService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createState(@RequestBody State state) {
        this.stateService.createState(state);
        return new ResponseEntity<ApiResponse>(new ApiResponse("State Added Successfully!!", state, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateState(@RequestBody State state) {
        this.stateService.updateState(state, state.id);
        return new ResponseEntity<>(new ApiResponse("State Updated Successfully!!!", state, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteState(@PathVariable("id") Long id) {
        this.stateService.deleteState(id);
        return new ResponseEntity<>(new ApiResponse("State Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllStates() {
        List<State> states = this.stateService.getAllStates();
        return new ResponseEntity<>(new ApiResponse("List of All States!!!", states, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getStateById(@PathVariable("id") Long id) {
        Optional<State> state = this.stateService.getStateById(id);
        return new ResponseEntity<>(new ApiResponse("State Found with this Id", state, true), HttpStatus.OK);
    }

    @PostMapping("/findByCountryId/{id}")
    public ResponseEntity<ApiResponse> getStateByCountryId(@PathVariable("id") Long id) {
        List<State> state = this.stateService.getStateByCountryId(id);
        return new ResponseEntity<>(new ApiResponse("State Found with this Id", state, true), HttpStatus.OK);
    }

}
