package com.myapp.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 *
 * @author Posudevskiy
 */
public final class ChartPanel extends Panel {

    private final Image imageBlockShow;
    private final Image imageBlockHide;

    public ChartPanel(String id) {
        super(id);

        imageBlockShow = new Image("image_block_show_chart", new PackageResourceReference(ChartPanel.class, "images/block_show.gif"));
        imageBlockHide = new Image("image_block_hide_chart", new PackageResourceReference(ChartPanel.class, "images/block_hide.gif"));
        add(imageBlockShow);
        add(imageBlockHide);
        imageBlockHide.setOutputMarkupPlaceholderTag(true);
        imageBlockShow.setOutputMarkupPlaceholderTag(true);
        imageBlockHide.setVisible(false);

        final Form form = new Form("chartForm");
        final Image image = new Image("chartData", new PackageResourceReference(MapPanel.class, "images/chart.png"));
        form.add(image);



        form.setOutputMarkupPlaceholderTag(true);

        AjaxLink link = new AjaxLink("chartLink") {

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
