package models;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADR_A19;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.validation.ValidationContext;
import ca.uhn.hl7v2.validation.impl.ValidationContextFactory;

public class A19Response {
    ADR_A19 adr;

    public A19Response(String response) throws HL7Exception {
        HapiContext context = new DefaultHapiContext();
        context.setValidationContext((ValidationContext) ValidationContextFactory.noValidation());
        Parser p = context.getPipeParser();
        Message hapiMsg;
        hapiMsg = p.parse(response);
        adr = (ADR_A19) hapiMsg;
    }

    public ADR_A19 toMessage() {
        return adr;
    }
}
