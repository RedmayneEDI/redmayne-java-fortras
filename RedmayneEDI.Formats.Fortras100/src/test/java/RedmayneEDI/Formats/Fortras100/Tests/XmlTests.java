package RedmayneEDI.Formats.Fortras100.Tests;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/// <summary>
/// Tests that perform conversion to and from Fortras objects and serialised XML model representations.
/// </summary>
public class XmlTests
{
	/// <summary>
	/// Converts a Sample BORD512 document from a BORD FortrasDocument to an XML document, and then back.
	/// </summary>
    @Test
	public void XmlFortrasBORD512Test() throws Exception
	{
        // Build output directory under the current working directory
        File outputDir = new File(System.getProperty("user.dir"), "edi-generated-samples");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
		// Create a test BORD512 model
		var fortrasbord = new RedmayneEDI.Formats.Fortras100.Tests.BORD512.BasicSampleMessage();

        File outputFile = new File(outputDir, "fortras-bord512-test.xml");

        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(RedmayneEDI.Formats.Fortras100.BORD512.FortrasDocument.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // Pretty prints output
        marshaller.marshal(fortrasbord.Document, outputFile); // Write to the file

        // Load from file
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile), StandardCharsets.US_ASCII))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        String originalBord = sb.toString();

        Unmarshaller unmarshaller = context.createUnmarshaller();
        RedmayneEDI.Formats.Fortras100.BORD512.FortrasDocument fromXmlFile = (RedmayneEDI.Formats.Fortras100.BORD512.FortrasDocument) unmarshaller.unmarshal(new StringReader(originalBord));

        // Compare text1 to model2.ToString()
        Assert.assertEquals("Original model and interpreted model do not match!", fortrasbord.Document.toString(), fromXmlFile.toString());
	}

	/// <summary>
	/// Converts a Sample STAT512 document from a STAT FortrasDocument to an XML document, and then back.
	/// </summary>
	@Test
	public void XmlFortrasSTAT512Test() throws Exception
	{
        // Build output directory under the current working directory
        File outputDir = new File(System.getProperty("user.dir"), "edi-generated-samples");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        // Create a test STAT512 model
        var fortrasstat = new RedmayneEDI.Formats.Fortras100.Tests.STAT512.BasicSampleMessage();

        File outputFile = new File(outputDir, "fortras-stat512-test.xml");

        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(RedmayneEDI.Formats.Fortras100.STAT512.FortrasDocument.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // Pretty prints output
        marshaller.marshal(fortrasstat.Document, outputFile); // Write to the file

        // Load from file
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile), StandardCharsets.US_ASCII))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        String originalStat = sb.toString();

        Unmarshaller unmarshaller = context.createUnmarshaller();
        RedmayneEDI.Formats.Fortras100.STAT512.FortrasDocument fromXmlFile = (RedmayneEDI.Formats.Fortras100.STAT512.FortrasDocument) unmarshaller.unmarshal(new StringReader(originalStat));

        // Compare text1 to model2.ToString()
        Assert.assertEquals("Original model and interpreted model do not match!", fortrasstat.Document.toString(), fromXmlFile.toString());
    }

	/// <summary>
	/// Converts a Sample ENTL512 document from a ENTL FortrasDocument to an XML document, and then back.
	/// </summary>
	@Test
	public void XmlFortrasENTL512Test() throws Exception
	{
        // Build output directory under the current working directory
        File outputDir = new File(System.getProperty("user.dir"), "edi-generated-samples");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        // Create a test STAT512 model
        var fortrasentl = new RedmayneEDI.Formats.Fortras100.Tests.ENTL512.BasicSampleMessage();

        File outputFile = new File(outputDir, "fortras-entl512-test.xml");

        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(RedmayneEDI.Formats.Fortras100.ENTL512.FortrasDocument.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // Pretty prints output
        marshaller.marshal(fortrasentl.Document, outputFile); // Write to the file

        // Load from file
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile), StandardCharsets.US_ASCII))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        String originalEntl = sb.toString();

        Unmarshaller unmarshaller = context.createUnmarshaller();
        RedmayneEDI.Formats.Fortras100.ENTL512.FortrasDocument fromXmlFile = (RedmayneEDI.Formats.Fortras100.ENTL512.FortrasDocument) unmarshaller.unmarshal(new StringReader(originalEntl));

        // Compare text1 to model2.ToString()
        Assert.assertEquals("Original model and interpreted model do not match!", fortrasentl.Document.toString(), fromXmlFile.toString());
    }
}
