package RedmayneEDI.Formats.Fortras100.Tests;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/// <summary>
/// Tests that populate a Fortras model, convert to text, and then import the text back to a model.
/// </summary>

public class FortrasTests
{
    /// <summary>
    /// Creates a sample BORD512 object, write to text file and then read into an object.
    /// </summary>
    @Test
    public void BasicFortrasBORD512Test() throws Exception
    {
        // Build output directory under the current working directory
        File outputDir = new File(System.getProperty("user.dir"), "edi-generated-samples");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Create model1 of a document and write to file1
        var fortrasbord = new RedmayneEDI.Formats.Fortras100.Tests.BORD512.BasicSampleMessage();
        var createdMessage = fortrasbord.CreateMessage(outputDir.toString());

        // Read in file1 as new model2
        var bordInterpreter = new RedmayneEDI.Formats.Fortras100.BORD512.Interpreter();
        bordInterpreter.FileFormatEncoding = StandardCharsets.ISO_8859_1; // Use standard Windows ISO-8859-1 format to read the file.
        var bordDocument = bordInterpreter.ReadFile(createdMessage);

        // Write model2 to file2
        File outputFile = new File(outputDir, "fortras512.bord.sample2.txt");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.ISO_8859_1)) {
            writer.write(bordDocument.toString());
        }

        // Read in file1 as text1
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(createdMessage), StandardCharsets.ISO_8859_1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        String originalBord = sb.toString();

        // Compare text1 to model2.ToString()
        Assert.assertEquals("Original model and interpreted model do not match!", originalBord, bordDocument.toString());
    }

    /// <summary>
    /// Creates a sample STAT512 object, write to text file and then read into an object.
    /// </summary>
    @Test
    public void BasicFortrasSTAT512Test() throws Exception
    {
        // Build output directory under the current working directory
        File outputDir = new File(System.getProperty("user.dir"), "edi-generated-samples");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Create model1 of a document and write to file1
        var fortrasbord = new RedmayneEDI.Formats.Fortras100.Tests.STAT512.BasicSampleMessage();
        var createdMessage = fortrasbord.CreateMessage(outputDir.toString());

        // Read in file1 as new model2
        var bordInterpreter = new RedmayneEDI.Formats.Fortras100.STAT512.Interpreter();
        bordInterpreter.FileFormatEncoding = StandardCharsets.US_ASCII; // Use standard Windows ISO-8859-1 format to read the file.
        var bordDocument = bordInterpreter.ReadFile(createdMessage);

        // Write model2 to file2
        File outputFile = new File(outputDir, "fortras512.stat.sample2.txt");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.US_ASCII)) {
            writer.write(bordDocument.toString());
        }

        // Read in file1 as text1
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(createdMessage), StandardCharsets.US_ASCII))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        String originalBord = sb.toString();

        // Compare text1 to model2.ToString()
        Assert.assertEquals("Original model and interpreted model do not match!", originalBord, bordDocument.toString());
    }

    /// <summary>
    /// Creates a sample ENTL512 object, write to text file and then read into an object.
    /// </summary>
    @Test
    public void BasicFortrasENTL512Test() throws Exception
    {
        // Build output directory under the current working directory
        File outputDir = new File(System.getProperty("user.dir"), "edi-generated-samples");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Create model1 of a document and write to file1
        var fortrasbord = new RedmayneEDI.Formats.Fortras100.Tests.ENTL512.BasicSampleMessage();
        var createdMessage = fortrasbord.CreateMessage(outputDir.toString());

        // Read in file1 as new model2
        var bordInterpreter = new RedmayneEDI.Formats.Fortras100.ENTL512.Interpreter();
        bordInterpreter.FileFormatEncoding = StandardCharsets.UTF_8; // Use standard Windows ISO-8859-1 format to read the file.
        var bordDocument = bordInterpreter.ReadFile(createdMessage);

        // Write model2 to file2
        File outputFile = new File(outputDir, "fortras512.entl.sample2.txt");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            writer.write(bordDocument.toString());
        }

        // Read in file1 as text1
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(createdMessage), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        String originalBord = sb.toString();

        // Compare text1 to model2.ToString()
        Assert.assertEquals("Original model and interpreted model do not match!", originalBord, bordDocument.toString());
    }
}
