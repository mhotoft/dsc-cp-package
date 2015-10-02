package jar;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import dk.yousee.vod.kiosk.adi.generated.ADI;

public class AdiUtils {
	private static final String SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
	public static final URL CABLELABS_ADI_SCHEMA_URL = AdiUtils.class.getResource("/providerADI.xsd");

	
	/**
	 * 
	 * @param the ADI xml
	 * @param validate (check against the providerADI.xsd)
	 * @param stripDTDinProlog (strip <!DOCTYPE ADI SYSTEM "ADI.DTD"> from the XML)
	 * @param encoding 
	 * @return ADI
	 * @throws BusinessValidationException
	 */
	public static ADI transformXml(InputStream in, boolean validate, boolean stripDTDinProlog, String encoding) throws Exception {
		return transformXml(getFile(in, encoding), validate, stripDTDinProlog);		
	}
	
	/**
	 * 
	 * @param the ADI xml
	 * @param validate (check against the providerADI.xsd)
	 * @param stripDTDinProlog (strip <!DOCTYPE ADI SYSTEM "ADI.DTD"> from the XML)
	 * @return ADI
	 * @throws BusinessValidationException
	 */
    public static ADI transformXml(String adiXML, boolean validate, boolean stripDTDinProlog) throws Exception {
        try {
        	String xml = adiXML;
        	if (stripDTDinProlog) {
        		xml = removeDTDInProlog(adiXML);        		
        	}
        		
        	if (validate) {
        		schemaValidate(CABLELABS_ADI_SCHEMA_URL, xml);
        	}
        	StreamSource src = new StreamSource(new StringReader(xml), "utf-8");
        	JAXBContext jc = JAXBContext.newInstance(ADI.class.getPackage().getName());
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<ADI> adi = unmarshaller.unmarshal(src, ADI.class);
            
            return adi.getValue();
        } catch (Exception e) {
            throw new Exception("error in transformation" + e.getMessage(), e);
        }
    }
    
    private static String removeDTDInProlog(String xmlString) {
       return xmlString.replace("<!DOCTYPE ADI SYSTEM \"ADI.DTD\">", ""); //<!DOCTYPE ADI SYSTEM "ADI.DTD">
    }


    private static String getFile(InputStream file, String encoding) {
        Scanner scanner = new Scanner(file, encoding);
        try {
        	return scanner.useDelimiter("\\A").next();
        } finally {
        	scanner.close();
        }
    }

    
    /**
     * Validates given xml against given schema and throws exception in case of validation error.
     *
     * @param schemaUrl the URL to load schema from
     * @param xml       the xml document to validate
     * @throws SAXException 
     * @throws IOException 
     * @throws SchemaValidateException in case of validation errors
     */
    private static void schemaValidate(URL schemaUrl, String xml) throws SAXException, IOException {    	
    	 	SchemaFactory sf = SchemaFactory.newInstance(SCHEMA_LANGUAGE);
    	 	Schema schema = sf.newSchema(schemaUrl);    	
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new CharArrayReader(xml.toCharArray()));
            validator.validate(source);        
    }

    
}


