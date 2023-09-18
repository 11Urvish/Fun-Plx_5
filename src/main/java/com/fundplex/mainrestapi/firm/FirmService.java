package com.fundplex.mainrestapi.firm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.response.FirmResponse;

@Service

public class FirmService {
    @Autowired
    public FirmRepo firmRepo;

    public void createFirm(Firm firm) {
        this.firmRepo.save(firm);
    }

    public void updateFirm(Firm firm, Long id) {
        this.firmRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Firm", "Id", id));
        firm.setId(id);
        this.firmRepo.save(firm);
    }

    public void deleteFirm(Long id) {
        this.firmRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Firm", "Id", id));
        this.firmRepo.deleteById(id);
    }

    public FirmResponse getAllFirms(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = null;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Firm> pageFirm = this.firmRepo.findAll(pageable);
        List<Firm> allFirms = pageFirm.getContent();

        FirmResponse firmResponse = new FirmResponse();
        firmResponse.setContent(allFirms);
        firmResponse.setTotalCount(firmRepo.count());
        firmResponse.setPageNumber(pageFirm.getNumber());
        firmResponse.setPageSize(pageFirm.getSize());
        firmResponse.setTotalElements(pageFirm.getNumberOfElements());
        firmResponse.setTotalPages(pageFirm.getTotalPages());
        firmResponse.setLastPage(pageFirm.isLast());

        return firmResponse;
    }

    public Optional<Firm> getFirmById(Long id) {
        return this.firmRepo.findById(id);
    }

    public List<Firm> getFirmByValue(String filter) {
        return firmRepo.findByValue(filter);
    }

}
