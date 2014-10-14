package org.scify.jthinkfreedom.reactors;

import java.io.IOException;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 * A reactor that can be parameterized with an application name, that launches
 * said application.
 *
 * @author peustr
 */
public class ApplicationLaunchReactor extends ReactorAdapter {

    private String application;

    public ApplicationLaunchReactor(String application) {
        this.application = application;
    }

    @Override
    public void react() {
        try {
            Process p = Runtime.getRuntime().exec(application);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

}
