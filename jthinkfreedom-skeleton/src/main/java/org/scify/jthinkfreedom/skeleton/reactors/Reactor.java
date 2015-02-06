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
package org.scify.jthinkfreedom.skeleton.reactors;

/**
 *
 * @author peustr
 */
public interface Reactor {

    /**
     * Simple method that triggers one single reaction (a click of the mouse,
     * the launch of an application etc.)
     */
    public void react();

    /**
     * Returns a simple string representation of the reactor.
     *
     * @return
     */
    public String getCanonicalString();

    /**
     * Returns a string that describes the reactor.
     *
     * @return
     */
    public String getDescription();
}
