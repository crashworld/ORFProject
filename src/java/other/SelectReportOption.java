/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import java.util.List;

/**
 *
 * @author Posudevskiy
 */
public class SelectReportOption {
    
    private String key;
    private List<String> value;
    
    public SelectReportOption(String key, List<String> value) {
        this.key = key;
        this.value = value;
    }

    public SelectReportOption() {
    }
    
    
    public void LoadDataFromDBReports(String key) {
        value = tempCheckAutopark(key);        
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
    
    private List<String> tempCheckAutopark(String key) {
        if (key.equals("Автопарк 2")) {
            return Variables.REPORTS_AUTOPARK_2;
        } else if (key.equals("Технотон КБ")) {
            return Variables.REPORTS_TECHNOTON;
        }
        
        return null;
    }
}
