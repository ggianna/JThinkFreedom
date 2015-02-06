/* 
 * Copyright 2014 SciFY.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            if (application != null) {
                Process p = Runtime.getRuntime().exec(application);
            }
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

    @Override
    public String getCanonicalString() {
        return "Launch Application";
    }

    @Override
    public String getDescription() {
        return "Launch an application";
    }

}
