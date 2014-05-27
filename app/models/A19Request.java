package models;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.QRY_A19;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.QRD;
import ca.uhn.hl7v2.parser.Parser;

import java.io.IOException;
import java.util.Date;

public class A19Request {
    private QRY_A19 qry;

    public A19Request(String patientId) throws IOException, HL7Exception {
        qry = new QRY_A19();
        qry.initQuickstart("QRY", "A19", "P");

        MSH mshSegment = qry.getMSH();
        mshSegment.getSendingApplication().getNamespaceID().setValue("ROQUA");
        mshSegment.getSendingFacility().getNamespaceID().setValue("ROM");

        QRD qrdSegment = qry.getQRD();
        qrdSegment.getQueryDateTime().getTimeOfAnEvent().setValue(new Date());
        qrdSegment.getQueryFormatCode().setValue("R");
        qrdSegment.getQueryPriority().setValue("I");
        qrdSegment.getQueryID().setValue(mshSegment.getMessageControlID().getValue());
        qrdSegment.getQuantityLimitedRequest().getQuantity().setValue("1");
        qrdSegment.getQuantityLimitedRequest().getUnits().getIdentifier().setValue("NM");
        qrdSegment.insertWhoSubjectFilter(0).getIDNumber().setValue(patientId);
        qrdSegment.insertQrd9_WhatSubjectFilter(0).getIdentifier().setValue("DEM");
    }

    public String toString() {
        HapiContext context = new DefaultHapiContext();
        Parser parser = context.getPipeParser();
        try {
            return parser.encode(qry);
        } catch (HL7Exception e) {
            e.printStackTrace();
            return "ERR";
        }
    }

    public Message toMessage() {
        return qry;
    }
}
