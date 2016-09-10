package core.managers;

import api.android.Android;
import core.MyLogger;
import core.Retry;
import core.TestInfo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Set JUnit rules what happens when test fails or passes
 */
public class TestManager {

    public static TestInfo testInfo = new TestInfo();

    @Rule
    public Retry retry = new Retry(3);

    @Before
    public void before(){
        testInfo.reset();
    }

    @Rule
    public TestRule listen = new TestWatcher(){
        //Handle tests that fail. For example take screenshots and logs and report to the database
        @Override
        public void failed(Throwable t, Description description){
            MyLogger.log.info(" __________________________________");
            MyLogger.log.info("|   !!!     TEST FAILED     !!!    |");
            MyLogger.log.info("|__________________________________|");
            TestInfo.printResults();
            //mockup
            //todo implement screenshots and logs to the database
            //Android.adb.takeScreenShot("target1");
            //send to a database, integrate with a database the framework
        }

        //Handle tests that pass. For example add a record to a database of successful tests.
        @Override
        public void succeeded (Description description){
            MyLogger.log.info(" ________________________________________");
            MyLogger.log.info("|              TEST PASSED               |");
            MyLogger.log.info("|________________________________________|");
            TestInfo.printResults();
        }
    };















}
