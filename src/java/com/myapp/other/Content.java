package com.myapp.other;

import org.apache.wicket.Component;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

import wickettree.AbstractTree;

public abstract class Content implements IDetachable {

    public abstract Component newContentComponent(String id, AbstractTree<SimpleData> tree,
            IModel<SimpleData> model);

    public void detach() {
    }
}