package com.myapp.other;

import java.util.List;
import java.util.Set;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import wickettree.ITreeProvider;
import wickettree.TableTree;

/**
 *
 * @author Posudevskiy
 */
public class TableTreeSimple extends TableTree<SimpleData> {

    private Content content = new LabelContent();

    public TableTreeSimple(String id, List<IColumn<SimpleData>> columns, ITreeProvider<SimpleData> provider, int itemsPerPage, IModel<Set<SimpleData>> state) {
        super(id, columns, provider, itemsPerPage, state);
    }

    @Override
    protected Component newContentComponent(String string, IModel<SimpleData> imodel) {
        return content.newContentComponent(string, this, imodel);
    }

    @Override
    protected Item<SimpleData> newRowItem(String id, int index, IModel<SimpleData> model) {
        return new OddEvenItem<SimpleData>(id, index, model);
    }
}
