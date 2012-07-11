package com.myapp.other;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import wickettree.AbstractTree;

public class LabelContent extends Content {

    private static final long serialVersionUID = 1L;

    @Override
    public Component newContentComponent(String id, AbstractTree<SimpleData> tree, IModel<SimpleData> model) {
        return new Label(id, model);
    }
}