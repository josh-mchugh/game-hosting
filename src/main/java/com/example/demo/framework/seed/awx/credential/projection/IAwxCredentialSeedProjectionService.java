package com.example.demo.framework.seed.awx.credential.projection;

import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialQuery;
import com.example.demo.framework.seed.awx.credential.projection.model.ExistsAnyAwxCredentialResponse;

public interface IAwxCredentialSeedProjectionService {

    ExistsAnyAwxCredentialResponse existsAny(ExistsAnyAwxCredentialQuery query);
}
