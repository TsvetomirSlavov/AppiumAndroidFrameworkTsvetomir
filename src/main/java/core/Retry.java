package core;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Keep retrying a test before failing it a number of times
 *
 *  // https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
 compile group: 'com.googlecode.json-simple', name: 'json-simple', version: '1.1.1'
 */
public class Retry implements TestRule{

    //repeat count null by default
    private int rc;

    public Retry(int rc){
        this.rc = rc;
    }

    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, final Description description){
        return new Statement() {
            //retry logic
            @Override
            public void evaluate() throws Throwable {
                //store the exception shy the test failed
                Throwable throwable = null;
                for(int i = 0; i < rc; i++){
                    try{
                        //retest
                        base.evaluate();
                        //if everything is good retrun which means stop and move on to the next test
                        return;

                        //capture why it failed and assign it to our variable
                    }catch (Throwable e){
                        throwable = e;
                        //todo change to MyLogger.log.info
                        System.out.println("Run "+(i+1)+" failed!");
                    }
                }
                //if it fails for the rc retry count
                //todo change to MyLogger.log.info
                System.out.println("Giving up after "+rc+" failures!");
                throw throwable;

            }
        };
    }









}
