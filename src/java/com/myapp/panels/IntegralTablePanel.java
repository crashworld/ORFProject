package com.myapp.panels;

import com.myapp.other.SimpleData;
import com.myapp.other.TableTreeSimple;
import com.myapp.other.models.DetachModel;
import com.myapp.other.providers.SimpleDataProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import wickettree.TableTree;
import wickettree.table.HeadersToolbar;
import wickettree.table.NoRecordsToolbar;
import wickettree.table.TreeColumn;
import wickettree.theme.HumanTheme;
import wickettree.util.ProviderSubset;

/**
 *
 * @author Posudevskiy
 */
public final class IntegralTablePanel extends Panel {
private ResourceReference theme = new HumanTheme();
    private TableTree<SimpleData> treeSimpleTemp;
    private SimpleDataProvider providerSimple = new SimpleDataProvider();
    private Set<SimpleData> stateSimple = new ProviderSubset<SimpleData>(providerSimple);
    private final Image imageBlockShow;
    private final Image imageBlockHide;
   
    public IntegralTablePanel(String id) {
        super(id);

        imageBlockShow = new Image("image_block_show_intetable", new PackageResourceReference(IntegralTablePanel.class, "images/block_show.gif"));
        imageBlockHide = new Image("image_block_hide_intetable", new PackageResourceReference(IntegralTablePanel.class, "images/block_hide.gif"));
        add(imageBlockShow);
        add(imageBlockHide);
        imageBlockHide.setOutputMarkupPlaceholderTag(true);
        imageBlockShow.setOutputMarkupPlaceholderTag(true);
        imageBlockHide.setVisible(false);

        treeSimpleTemp = new TableTreeSimple("table", createColumnsSimple(), providerSimple, Integer.MAX_VALUE, new DetachModel(stateSimple));
        treeSimpleTemp.add(new Behavior() {

            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                response.renderCSSReference(theme);

            }
        });
        treeSimpleTemp.addTopToolbar(new HeadersToolbar(treeSimpleTemp));
        treeSimpleTemp.addBottomToolbar(new NoRecordsToolbar(treeSimpleTemp));
        
        final Form form = new Form("integralForm");
        form.add(treeSimpleTemp);
        form.setOutputMarkupPlaceholderTag(true);

        AjaxLink link = new AjaxLink("integralLink") {

            @Override
            public void onClick(AjaxRequestTarget art) {
                art.add(imageBlockHide.setVisible(form.isVisible()));
                art.add(imageBlockShow.setVisible(!form.isVisible()));
                art.add(form.setVisible(!form.isVisible()));
            }
        };

        add(form);
        add(link);
    }
    
    private List<IColumn<SimpleData>> createColumnsSimple() {
        List<IColumn<SimpleData>> columns = new ArrayList<IColumn<SimpleData>>();

        columns.add(new TreeColumn<SimpleData>(Model.of("State TC")));
        columns.add(new PropertyColumn<SimpleData>(Model.of("ID"), "id"));
//        columns.add(new PropertyColumn<SimpleData>(Model.of("Begin"), "beginTime"));
//        columns.add(new PropertyColumn<SimpleData>(Model.of("End"), "endTime"));
        columns.add(new PropertyColumn<SimpleData>(Model.of("Duration"), "duration"));
        columns.add(new PropertyColumn<SimpleData>(Model.of("Speed"), "speed"));
        columns.add(new PropertyColumn<SimpleData>(Model.of("Track"), "track"));

        return columns;
    }
}
