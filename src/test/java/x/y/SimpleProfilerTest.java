package x.y;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.StopWatch;

@RunWith(MockitoJUnitRunner.class)
public class SimpleProfilerTest {

    @Mock
    private ProceedingJoinPoint mockJoinPoint;

    private SimpleProfiler simpleProfiler;

    @Before
    public void setUp() {
        simpleProfiler = new SimpleProfiler();
    }

    @Test
    public void testGetOrder() {
        // Given
        int expectedOrder = 1;
        simpleProfiler.setOrder(expectedOrder);

        // When
        int actualOrder = simpleProfiler.getOrder();

        // Then
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    public void testSetOrder() {
        // Given
        int newOrder = 5;

        // When
        simpleProfiler.setOrder(newOrder);

        // Then
        assertEquals(newOrder, simpleProfiler.getOrder());
    }

    @Test
    public void testProfile_SuccessfulExecution() throws Throwable {
        // Given
        String joinPointSignature = "execution(void com.example.Service.doSomething())";
        Object expectedResult = "testResult";

        when(mockJoinPoint.toLongString()).thenReturn(joinPointSignature);
        when(mockJoinPoint.proceed()).thenReturn(expectedResult);

        // When
        Object result = simpleProfiler.profile(mockJoinPoint);

        // Then
        assertEquals(expectedResult, result);
        verify(mockJoinPoint).toLongString();
        verify(mockJoinPoint).proceed();
    }

    @Test
    public void testProfile_WithException() throws Throwable {
        // Given
        String joinPointSignature = "execution(void com.example.Service.doSomething())";

        when(mockJoinPoint.toLongString()).thenReturn(joinPointSignature);
        when(mockJoinPoint.proceed()).thenThrow(new RuntimeException("Test exception"));

        // When & Then
        try {
            simpleProfiler.profile(mockJoinPoint);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Test exception", e.getMessage());
        }

        verify(mockJoinPoint).toLongString();
        verify(mockJoinPoint).proceed();
    }

    @Test
    public void testProfile_CallsStopWatch() throws Throwable {
        // Given
        String joinPointSignature = "execution(void com.example.Service.doSomething())";
        Object expectedResult = "testResult";

        when(mockJoinPoint.toLongString()).thenReturn(joinPointSignature);
        when(mockJoinPoint.proceed()).thenReturn(expectedResult);

        // When
        Object result = simpleProfiler.profile(mockJoinPoint);

        // Then - We can't easily test StopWatch.printPretty() output without
        // mocking the entire StopWatch class, but we can verify the basic flow
        assertEquals(expectedResult, result);
        verify(mockJoinPoint).toLongString();
        verify(mockJoinPoint).proceed();
    }
}
