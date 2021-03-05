package com.example.demo.framework.seed.ovh.credential.projection;

import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialQuery;
import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialResponse;

public interface ICredentialSeedProjectionService {

    ExistsAnyCredentialResponse existsAny(ExistsAnyCredentialQuery query);
}
