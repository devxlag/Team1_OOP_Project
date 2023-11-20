package team1project;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for the {@link Evaluator} class.
 * Test not fully implemented
 * However the components are tested in other test classes
 */
 
public class EvaluatorTest {

    private Evaluator evaluator;
    private ZipFile mockCompositeTreeRoot;

    @Before
    public void setUp() {
        evaluator = new Evaluator();
        mockCompositeTreeRoot = createMockCompositeTree();
    }

    //@Test
    public void testEvaluate() throws IOException {
        // Set up a mock composite tree with a ZipFile object containing JavaFile objects
        evaluator.setCompositeTreeRoot(mockCompositeTreeRoot);

        // Mock the cleanTargetDirectory method to avoid actual file deletion
        Evaluator spyEvaluator = spy(evaluator);
        //doNothing().when(spyEvaluator).cleanTargetDirectory(anyString());

        // Call the evaluate method
        boolean result = spyEvaluator.evaluate();

        // Verify that the cleanTargetDirectory method is called once
       // verify(spyEvaluator, times(1)).cleanTargetDirectory(anyString());

        // Verify the result
        assertTrue(result);
    }

    private ZipFile createMockCompositeTree() {
        // Create a mock ZipFile
        ZipFile mockZipFile = mock(ZipFile.class);
        when(mockZipFile.getPath()).thenReturn("mock/path");
        when(mockZipFile.getFileName()).thenReturn("mock_file.zip");

        // Create mock JavaFile objects
        JavaFile mockJavaFile1 = mock(JavaFile.class);
        when(mockJavaFile1.getFileName()).thenReturn("Flight.java");
        when(mockJavaFile1.getContents()).thenReturn("mock Java file contents 1");

        JavaFile mockJavaFile2 = mock(JavaFile.class);
        when(mockJavaFile2.getFileName()).thenReturn("Passenger.java");
        when(mockJavaFile2.getContents()).thenReturn("mock Java file contents 2");

        // Set up the mock composite tree
        List<AbstractFile> children = Arrays.asList(mockJavaFile1, mockJavaFile2);
        when(mockZipFile.getChildren()).thenReturn(children);

        return mockZipFile;
    }
}

