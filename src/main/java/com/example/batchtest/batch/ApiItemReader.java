package com.example.batchtest.batch;

import com.example.batchtest.dto.AladinApiDto;
import com.example.batchtest.dto.AladinItemDto;
import com.example.batchtest.service.AladinApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

@RequiredArgsConstructor
public class ApiItemReader implements ItemReader<AladinItemDto> {
    private final AladinApiService aladinApiService;

    private int nextIdx = 0;
    private List<AladinItemDto> items;

    @Override
    public AladinItemDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (items == null){
            AladinApiDto aladinApiDto = aladinApiService.findBestseller();
            items = aladinApiDto.getItem();
        }

        AladinItemDto nextItemDto = null;
        if (nextIdx < items.size()){
            nextItemDto = items.get(nextIdx);
            nextIdx++;
        }else{
            items = null;
            nextIdx = 0;
        }

        return nextItemDto;
    }
}
