package jar;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dk.yousee.vod.kiosk.adi.generated.ADI;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Usage: TestADI <adiFile.xml>");
		testADI(args[0]);
		System.out.println("Done. Everything looks fine!");
	}

	public static void testADI(String file) throws Exception {
		if (file==null)
			throw new Exception("File is missing as input");
		
		InputStream adiInput = null;
		adiInput = new FileInputStream(file);
		
		
		StringWriter writer = new StringWriter();
		IOUtils.copy(adiInput, writer, "UTF-8");
		String adiString = writer.toString();		
		ADI adi = AdiUtils.transformXml(adiString, true, false);
		adiInput.close();
		/*
		 * Assert.assertNotNull(adi); Assert.assertEquals("CMORE",
		 * adi.getMetadata().getAMS().getProvider()); List<AppData> appData =
		 * adi.getMetadata().getAppData(); for (AppData a : appData) { if
		 * (a.getName().equals("Metadata_Spec_Version")) {
		 * Assert.assertEquals("CableLabsVOD1.1", a.getValue()); } }
		 */
		// Assert.assert("Package asset for Hooligans",
		// adi.getMetadata().getAMS().getDescription());
	}

}
