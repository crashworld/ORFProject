package com.myapp.panels;

import java.util.Date;
import java.util.Locale;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import com.myapp.other.SelectObjectOption;
import com.myapp.other.SelectReportOption;
import com.myapp.other.data.Variables;


/**
 *
 * @author Posudevskiy
 */
public final class SettingsPanel extends Panel {

    public String selected_autopark = Variables.AUTOPARKS.get(0);
    public String selected_report = Variables.REPORTS_TECHNOTON.get(0);
    public String selected_object = Variables.OBJECT_CARS_1.get(0);
    public String selected_period = Variables.PERIODS.get(0);
    public String selected_interval = Variables.INTERVALS.get(0);
    private DropDownChoice autoparkChoice;
    private DropDownChoice reportChoice;
    private DropDownChoice objectChoice;
    private DropDownChoice periodChoice;
    private DropDownChoice intervalChoice;
    private SelectReportOption temp_reports;
    private SelectObjectOption temp_objects;
    private final Date date = new Date();
    private final Date time = new Date();
    private static final Locale LOCALE_RU = new Locale("ru");
    private Locale selectedLocale = LOCALE_RU;
    private DatePicker datePickerTo;
    private DatePicker datePickerFrom;
    private DateTextField dateTextFieldFrom;
    private DateTextField dateTextFieldTo;
    private TextField timeHoursFrom;
    private TextField timeMinutesFrom;
    private TextField timeHoursTo;
    private TextField timeMinutesTo;
    private Label periodLabel;

    public SettingsPanel(String id) {
        super(id);
        createTimeFields();
        createCalendarFields();
        createChooseFields();
        createPeriodFields();
        selectedLocale = Session.get().getLocale();
    }

    private void createPeriodFields() {
        
        periodLabel = new Label("periodLabel","Период:");
        periodLabel.setVisible(false);
        add(periodLabel.setOutputMarkupPlaceholderTag(true));
        
        intervalChoice = new DropDownChoice("interval", new PropertyModel(this, "selected_interval"), Variables.INTERVALS);
        intervalChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                boolean bool = intervalChoice.getModelObject().equals(Variables.periodInterval);
                target.add(periodChoice.setVisible(bool)); 
                target.add(periodLabel.setVisible(bool));
            }
        });
        add(intervalChoice.setOutputMarkupId(true));

        periodChoice = new DropDownChoice("period", new PropertyModel(this, "selected_period"), Variables.PERIODS);
        periodChoice.setVisible(false);
        add(periodChoice.setOutputMarkupPlaceholderTag(true));
    }

    private void createTimeFields() {
        timeHoursFrom = new TextField("time_hours_from");
        timeMinutesFrom = new TextField("time_minutes_from");
        timeHoursTo = new TextField("time_hours_to");
        timeMinutesTo = new TextField("time_minutes_to");

        add(timeHoursFrom);
        add(timeMinutesFrom);
        add(timeHoursTo);
        add(timeMinutesTo);
    }

    private void createCalendarFields() {
        dateTextFieldFrom = new DateTextField("dateTextFieldFrom", new PropertyModel<Date>(
                this, "date"), new StyleDateConverter("S-", true));

        dateTextFieldTo = new DateTextField("dateTextFieldTo", new PropertyModel<Date>(
                this, "date"), new StyleDateConverter("S-", true));

        add(dateTextFieldFrom);
        add(dateTextFieldTo);

        datePickerFrom = new DatePicker() {

            @Override
            protected String getAdditionalJavaScript() {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        datePickerFrom.setShowOnFieldClick(true);
        datePickerFrom.setAutoHide(true);
        dateTextFieldFrom.add(datePickerFrom);

        datePickerTo = new DatePicker() {

            @Override
            protected String getAdditionalJavaScript() {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        datePickerTo.setShowOnFieldClick(true);
        datePickerTo.setAutoHide(true);
        dateTextFieldTo.add(datePickerTo);
    }

    private void createChooseFields() {
        temp_reports = new SelectReportOption();
        temp_objects = new SelectObjectOption();

        autoparkChoice = new DropDownChoice("autopark", new PropertyModel(this, "selected_autopark"), Variables.AUTOPARKS);
        reportChoice = new DropDownChoice("report", new PropertyModel(this, "selected_report"), Variables.REPORTS_TECHNOTON);
        objectChoice = new DropDownChoice("object", new PropertyModel(this, "selected_object"), Variables.OBJECT_CARS_1);
        add(autoparkChoice.setOutputMarkupId(true));
        add(reportChoice.setOutputMarkupId(true));
        add(objectChoice.setOutputMarkupId(true));

        autoparkChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                if (autoparkChoice.getModelObject() != null) {
                    String temp_autopark = (String) autoparkChoice.getModelObject();
                    temp_reports.LoadDataFromDBReports(temp_autopark);
                    temp_objects.LoadDataFromDBObject(temp_autopark, temp_reports.getValue().get(0));
                    target.addComponent(reportChoice.setChoices(temp_reports.getValue()).setDefaultModelObject(temp_reports.getValue().get(0)));
                    target.addComponent(objectChoice.setChoices(temp_objects.getValue()).setDefaultModelObject(temp_objects.getValue().get(0)));
                }
            }
        });

        reportChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                if (autoparkChoice.getModelObject() != null && reportChoice.getModelObject() != null) {
                    String temp_autopark = (String) autoparkChoice.getModelObject();
                    String temp_report = (String) reportChoice.getModelObject();
                    temp_objects.LoadDataFromDBObject(temp_autopark, temp_report);
                    target.addComponent(objectChoice.setChoices(temp_objects.getValue()).setDefaultModelObject(temp_objects.getValue().get(0)));
                }
            }
        });
    }
}
