package core.constants;

import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * Created by ceko on 09/08/2016.
 */
public enum Arg implements ServerArgument{

    TIMEOUT("--command-timeout"),
    LOG_LEVEL("--log-level"),
    LOCAL_TIME_ZONE("--local-timezone");

    private final String arg;

    Arg(String arg){
        this.arg = arg;
    }

    //@Override
    public String getArgument(){
        return arg;
    }
}
