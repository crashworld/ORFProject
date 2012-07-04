/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.wicket;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import other.ReportData;
import other.SortableContactDataProvider;

/**
 *
 * @author Posudevskiy
 */
public final class IntegralTablePanel extends Panel {

    private final Image imageBlockShow;
    private final Image imageBlockHide;

    public IntegralTablePanel(String id) {
        super(id);

        imageBlockShow = new Image("image_block_show_intetable", new PackageResourceReference(IntegralTablePanel.class, "block_show.gif"));
        imageBlockHide = new Image("image_block_hide_intetable", new PackageResourceReference(IntegralTablePanel.class, "block_hide.gif"));
        add(imageBlockShow);
        add(imageBlockHide);
        imageBlockHide.setOutputMarkupPlaceholderTag(true);
        imageBlockShow.setOutputMarkupPlaceholderTag(true);
        imageBlockHide.setVisible(false);


        List<IColumn<ReportData>> columns = new ArrayList<IColumn<ReportData>>();
        columns.add(new PropertyColumn<ReportData>(new Model<String>("ID"), "id"));
        columns.add(new PropertyColumn<ReportData>(new Model<String>("First Name"), "firstName",
                "firstName"));
//        columns.add(new PropertyColumn<ReportData>(new Model<String>("Last Name"), "lastName",
//                "lastName"));
        columns.add(new PropertyColumn<ReportData>(new Model<String>("Home Phone"), "homePhone"));
        columns.add(new PropertyColumn<ReportData>(new Model<String>("Cell Phone"), "cellPhone"));
        AjaxFallbackDefaultDataTable table = new AjaxFallbackDefaultDataTable<ReportData>("table", columns,
                new SortableContactDataProvider(), 8);

        final Form form = new Form("integralForm");
        form.add(table);
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
}
