package controllers;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.parser.Parser;
import models.A19Request;
import models.A19Response;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;

public class Application extends Controller {

    public static Result makeRequest() throws IOException, HL7Exception {
        A19Request request = new A19Request("123");
        return ok(request.toString());
    }

    public static Result parseResponse() throws HL7Exception {
        String str = "MSH|^~\\&|USER|IMPULSE|ROQUA|RGOC|201405231934||ADR^A19|00000000000003631654|P|2.4|||NE|NE|NLD|UNICODE||||\r\n" +
                "MSA|CA|af10d58b07|||||\r\n" +
                "QAK||OK|||||\r\n" +
                "QRD|201405230000|R|I|af10d58b07|||1^RD|123456^&&&&^^^^^^^^^^^^^^^^|DEM||||\r\n" +
                "PID|1||123456^^^^PI^^^||van de Achternaam&van de&Achternaam&&^Voornaam^V.A.^^van de^^L^^^^~&&&&^Voornaam^^^^^N^^^^||19231220|M|||Straatnaam 123B&Straatnaam&123^B^PLAATSNAAM^^1234 AB^6030^L^^^^^||||||||||||||||||||Y|US|||||||\r\n" +
                "ROL||UP|PP^Primary Care Provider|3994^Zwajo&&Zwajo&&^^^^^^^^^^^^^^^^|||||01^Huisarts^Broddelaar||Huisartsweg 34&Huisartsweg&34^^DUCKSTAD^^5678 ZXK^NL^L^^^^^|012-3456789^PRN^PH^^^^^^|\r\n" +
                "PV1|1|O|03120002||||||||||||||||001&052^^^^^^^|||||||||||||||||||||||||201405231600||||||19^^^^^^^|||\r\n" +
                "PV2||||||||20140523||||||||||||||||||||||||||||||||||||||||\r\n" +
                "IN1|1|\"\"|3337^^^BRODDELAAR^^^^|AGIS^^^^^^^|Postbus 12&Postbus&12^^DUCKSTAD^^9999 AA^NL^L^^^^^||012  -  3456789^PRN^PH^^^^^^|||||20130101||||||||||||||||||||||||12336334||||||||||||||\u001C\n";
        A19Response response = new A19Response(str);
        HapiContext context = new DefaultHapiContext();
        Parser parser = context.getXMLParser();
        String res = parser.encode(response.toMessage());

        return ok(res);
    }

}
