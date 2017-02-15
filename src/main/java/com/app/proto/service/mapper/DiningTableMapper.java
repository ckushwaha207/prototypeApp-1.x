package com.app.proto.service.mapper;

import com.app.proto.domain.*;
import com.app.proto.service.dto.DiningTableDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity DiningTable and its DTO DiningTableDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiningTableMapper {

    @Mapping(source = "store.id", target = "storeId")
    DiningTableDTO diningTableToDiningTableDTO(DiningTable diningTable);

    List<DiningTableDTO> diningTablesToDiningTableDTOs(List<DiningTable> diningTables);

    @Mapping(source = "storeId", target = "store")
    DiningTable diningTableDTOToDiningTable(DiningTableDTO diningTableDTO);

    List<DiningTable> diningTableDTOsToDiningTables(List<DiningTableDTO> diningTableDTOs);

    default Store storeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Store store = new Store();
        store.setId(id);
        return store;
    }
}
