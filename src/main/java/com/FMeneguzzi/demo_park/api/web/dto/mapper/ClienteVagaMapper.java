package com.FMeneguzzi.demo_park.api.web.dto.mapper;

import com.FMeneguzzi.demo_park.api.entities.ClienteVaga;
import com.FMeneguzzi.demo_park.api.web.dto.EstacionamentoCreateDto;
import com.FMeneguzzi.demo_park.api.web.dto.EstacionamentoResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteVagaMapper {

    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto dto){
        return new ModelMapper().map(dto, ClienteVaga.class);
    }

    public static EstacionamentoResponseDto toDto(ClienteVaga clienteVaga){
        return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
    }
}
