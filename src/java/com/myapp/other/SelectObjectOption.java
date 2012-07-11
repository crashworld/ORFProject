package com.myapp.other;

import com.myapp.other.data.Variables;
import java.util.List;

/**
 *
 * @author Posudevskiy
 */
public class SelectObjectOption {

    private String key;
    private List<String> value;

    public SelectObjectOption(String key, List<String> value) {
        this.key = key;
        this.value = value;
    }

    public SelectObjectOption() {
    }

    public void LoadDataFromDBObject(String autoparkKey, String reportKey) {
        int type_report = tempCheckReport(reportKey);
        value = tempCheckAutopark(autoparkKey, type_report);
        if (value.isEmpty()) {
            setValue(value);
        }

    }

    public String getKey() {
        return key;
    }

    public List<String> getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    private List<String> tempCheckAutopark(String key, int type_report) {
        switch (type_report) {
            case 1:
                if (key.equals("Автопарк 2")) {
                    return Variables.OBJECT_CARS_2;
                } else if (key.equals("Технотон КБ")) {
                    return Variables.OBJECT_CARS_1;
                }
                break;
            case 2:
                if (key.equals("Автопарк 2")) {
                    return Variables.OBJECT_CARS_GROUP_2;
                } else if (key.equals("Технотон КБ")) {
                    return Variables.OBJECT_CARS_GROUP_1;
                }
                break;
            case 3:
                if (key.equals("Автопарк 2")) {
                    return Variables.OBJECT_GEOZONE_2;
                } else if (key.equals("Технотон КБ")) {
                    return Variables.OBJECT_GEOZONE_1;
                }
                break;
            default:
                break;
        }

        return null;
    }

    private int tempCheckReport(String key) {
        if (key.equals("Отчет по ТС")) {
            return 1;
        } else if (key.equals("Отчет по группе ТС")) {
            return 2;
        } else if (key.equals("Отчет по геозонам")) {
            return 3;
        }

        return 0;
    }
}
