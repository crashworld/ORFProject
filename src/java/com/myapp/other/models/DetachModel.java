package com.myapp.other.models;

import java.util.Set;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IDetachable;
import com.myapp.other.SimpleData;

/**
 *
 * @author Posudevskiy
 */
public class DetachModel extends AbstractReadOnlyModel<Set<SimpleData>> {

    private Set<SimpleData> stateSimple;

    public DetachModel(Set<SimpleData> state) {
        this.stateSimple = state;
    }

    @Override
    public Set<SimpleData> getObject() {
        return stateSimple;
    }

    @Override
    public void detach() {
        ((IDetachable) stateSimple).detach();
    }
}
