package by.prokhorenko.textparser.reader;

import by.prokhorenko.textparser.exception.ReaderException;
import by.prokhorenko.textparser.reader.impl.FileReaderImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileReaderImplTest {


    Reader fileReader;
    String expectedLine;
    String linkToFile;

    @BeforeClass
    public void setUp(){
        fileReader = new FileReaderImpl();
        expectedLine = "Hello world!";
        linkToFile = "file/ReaderDataTest";
    }

    @Test
    public void readAllTest() throws ReaderException {
        String actualLine = fileReader.readAll(linkToFile);
        Assert.assertEquals(actualLine,expectedLine);
    }

    @AfterClass
    public void tierDown(){
        fileReader = null;
        expectedLine = null;
        linkToFile = null;
    }
}
