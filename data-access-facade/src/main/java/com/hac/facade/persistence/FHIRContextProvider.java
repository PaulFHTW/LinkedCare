package com.hac.facade.persistence;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.hac.facade.configuration.FHIRProperties;
import org.springframework.stereotype.Component;

@Component
public class FHIRContextProvider {

    private FHIRProperties fhirProperties;

    private final FhirContext ctx;

    public FHIRContextProvider(FHIRProperties fhirProperties) {
        this.fhirProperties = fhirProperties;
        ctx = FhirContext.forR4();
    }

    public FhirContext getCtx() {
        return ctx;
    }

    public IParser getJsonParser() {
        return ctx.newJsonParser().setPrettyPrint(true);
    }

    public IGenericClient getClient() {
        return ctx.newRestfulGenericClient(fhirProperties.getServerUrl());
        //return ctx.newRestfulGenericClient("https://hapi.fhir.org/baseR4");
    }


}
