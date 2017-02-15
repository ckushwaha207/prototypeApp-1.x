package com.app.proto.service.mapper;

import com.app.proto.domain.*;
import com.app.proto.service.dto.StoreGroupDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity StoreGroup and its DTO StoreGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StoreGroupMapper {

    @Mapping(source = "organization.id", target = "organizationId")
    StoreGroupDTO storeGroupToStoreGroupDTO(StoreGroup storeGroup);

    List<StoreGroupDTO> storeGroupsToStoreGroupDTOs(List<StoreGroup> storeGroups);

    @Mapping(target = "stores", ignore = true)
    @Mapping(source = "organizationId", target = "organization")
    @Mapping(target = "user", ignore = true)
    StoreGroup storeGroupDTOToStoreGroup(StoreGroupDTO storeGroupDTO);

    List<StoreGroup> storeGroupDTOsToStoreGroups(List<StoreGroupDTO> storeGroupDTOs);

    default Organization organizationFromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
