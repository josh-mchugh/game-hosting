package com.example.demo.framework.seed.awx.credential.projection;

import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialResponse;
import com.example.demo.framework.seed.awx.credential.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.FetchAwxOrganizationIdByAwxIdResponse;

public interface IAwxCredentialSeedProjectionService {

    ExistsAnyAwxCredentialResponse existsAny(ExistsAnyAwxCredentialQuery query);

    FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query);
}
