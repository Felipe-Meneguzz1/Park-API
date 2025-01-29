package com.FMeneguzzi.demo_park.api.web.dto.mapper;

import com.FMeneguzzi.demo_park.api.web.dto.PagebleDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableMapper {

    public static PagebleDto toDto(Page page){
        return new ModelMapper().map(page, PagebleDto.class);
    }
}
