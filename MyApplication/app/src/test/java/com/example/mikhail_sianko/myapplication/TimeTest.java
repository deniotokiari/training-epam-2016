package com.example.mikhail_sianko.myapplication;

import com.example.mikhail_sianko.myapplication.examples.utils.CustomSimpleDayFormat;
import com.example.mikhail_sianko.myapplication.examples.utils.ITime;
import com.example.mikhail_sianko.myapplication.examples.utils.Time;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TimeTest {

    private ITime mITime;

    @Mock
    private CustomSimpleDayFormat mCustomSimpleDayFormat;

    @BeforeClass
    public static void setUp() {
        //open database connection
        System.err.println("setUp");
    }

    @AfterClass
    public static void destroy() {
        //close database connection;
        System.err.println("destroy");
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mITime = Mockito.spy(new Time(mCustomSimpleDayFormat));
        System.err.println("init");

        //init some resources before running test
    }

    @After
    public void close() {
        //release some resoures after test finish
        System.err.println("close");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTimeFormat() {
        Mockito.when(mITime.getTimeFormat()).thenReturn("HH:mm");
        Mockito.when(mITime.getCurrentTimeInMillis()).thenReturn(1475812800000L);
        System.err.println(mITime.getFriendlyFormattedTime());

        assertEquals(mITime.getDayPart(), "morning");

        Mockito.when(mITime.getCurrentTimeInMillis()).thenReturn(1475812740000L);
        System.err.println(mITime.getFriendlyFormattedTime());
        assertEquals(mITime.getDayPart(), "night");

        //TODO investigate inOrder
        // Mockito.inOrder(mITime.getFriendlyFormattedTime(),
        // mITime.getTimeFormat(), mITime.getCurrentTimeInMillis());

//        Mockito.when(mITime.getTimeFormat()).thenReturn("22");
//        mITime.getFriendlyFormattedTime();
        //TODO throw exception

    }

    @Test
    public void testMockObject() {
        Mockito.when(mCustomSimpleDayFormat.getFormattedTime(Matchers.anyString(),
                Matchers.anyLong())).thenReturn("18:00:00");
        System.out.println(mITime.getFriendlyFormattedTime());
    }
}