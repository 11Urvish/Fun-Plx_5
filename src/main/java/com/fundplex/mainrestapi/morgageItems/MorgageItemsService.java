package com.fundplex.mainrestapi.morgageItems;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

@Service
public class MorgageItemsService {
    @Autowired
    public MorgageItemsRepo morgageItemsRepo;

    public void createMorgageItems(MorgageItems morgageItems) {
        this.morgageItemsRepo.save(morgageItems);
    }

    public void updateMorgageItems(MorgageItems morgageItems, Long id) {
        this.morgageItemsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        morgageItems.setId(id);
        this.morgageItemsRepo.save(morgageItems);
    }

    public void deleteMorgageItems(Long id) {
        this.morgageItemsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.morgageItemsRepo.deleteById(id);
    }

    public List<MorgageItems> getAllMorgageItemss() {
        return this.morgageItemsRepo.findAll();
    }

    public Optional<MorgageItems> getMorgageItemsById(Long id) {
        return this.morgageItemsRepo.findById(id);
    }

}
