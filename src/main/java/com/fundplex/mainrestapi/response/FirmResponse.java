package com.fundplex.mainrestapi.response;

import java.util.List;

import com.fundplex.mainrestapi.firm.Firm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirmResponse {

    private List<Firm> content;

    private Long totalCount;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;

}
