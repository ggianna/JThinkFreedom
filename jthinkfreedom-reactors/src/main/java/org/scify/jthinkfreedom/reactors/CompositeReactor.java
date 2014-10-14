package org.scify.jthinkfreedom.reactors;

import java.util.List;
import org.scify.jthinkfreedom.skeleton.reactors.Reactor;
import org.scify.jthinkfreedom.skeleton.reactors.ReactorAdapter;

/**
 * A reactor that makes chain calls of other reactors.
 *
 * @author peustr
 */
public class CompositeReactor extends ReactorAdapter {

    private List<Reactor> reactorList;

    public CompositeReactor(List<Reactor> reactorList) {
        this.reactorList = reactorList;
    }

    public List<Reactor> getReactorList() {
        return reactorList;
    }

    public void setReactorList(List<Reactor> reactorList) {
        this.reactorList = reactorList;
    }

    @Override
    public void react() {
        for (Reactor curReactor : reactorList) {
            curReactor.react();
        }
    }

}
