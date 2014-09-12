/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors;

import java.util.List;

/**
 *For each reactor is to list calls on similar reactors.
 * @author alexiz
 */

public abstract class CompositeReactor extends ReactorAdapter {

    private List<ReactorAdapter> reactorsList;

    public CompositeReactor(List<ReactorAdapter> reactorsList) {
        super();
        this.reactorsList = reactorsList;

    }

    public List<ReactorAdapter> getReactorsList() {
        return reactorsList;
    }

    public void setReactorsList(List<ReactorAdapter> reactorsList) {
        this.reactorsList = reactorsList;
    }

   
    @Override
    public void react() {
        for (ReactorAdapter re : reactorsList) {
            re.react();
        }

    }
}
