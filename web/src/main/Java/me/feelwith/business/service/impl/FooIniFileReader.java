package me.feelwith.business.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Administrator on 2016/6/12.
 */

public class FooIniFileReader {
    private Hashtable paramTable;
    private String conf_filename;

    public FooIniFileReader(String conf_filename) throws FileNotFoundException, IOException {
        this.conf_filename = conf_filename;
        this.loadFromFile(conf_filename);
    }

    public String getConfFilename() {
        return this.conf_filename;
    }

    public String getStrValue(String name) {
        Object obj = this.paramTable.get(name);
        return obj == null?null:(obj instanceof String?(String)obj:(String)((ArrayList)obj).get(0));
    }

    public int getIntValue(String name, int default_value) {
        String szValue = this.getStrValue(name);
        return szValue == null?default_value:Integer.parseInt(szValue);
    }

    public boolean getBoolValue(String name, boolean default_value) {
        String szValue = this.getStrValue(name);
        return szValue == null?default_value:szValue.equalsIgnoreCase("yes") || szValue.equalsIgnoreCase("on") || szValue.equalsIgnoreCase("true") || szValue.equals("1");
    }

    public String[] getValues(String name) {
        Object obj = this.paramTable.get(name);
        if(obj == null) {
            return null;
        } else {
            String[] values;
            if(obj instanceof String) {
                values = new String[]{(String)obj};
                return values;
            } else {
                Object[] objs = ((ArrayList)obj).toArray();
                values = new String[objs.length];
                System.arraycopy(objs, 0, values, 0, objs.length);
                return values;
            }
        }
    }

    private void loadFromFile(String conf_filename) throws FileNotFoundException, IOException {
//            FileReader fReader = new FileReader(
        Reader reader = new InputStreamReader(FooIniFileReader.class.getClassLoader().getResourceAsStream(conf_filename));
        BufferedReader buffReader = new BufferedReader(reader);
        this.paramTable = new Hashtable();

        String line;
        try {
            while((line = buffReader.readLine()) != null) {
                line = line.trim();
                if(line.length() != 0 && line.charAt(0) != 35) {
                    String[] parts = line.split("=", 2);
                    if(parts.length == 2) {
                        String name = parts[0].trim();
                        String value = parts[1].trim();
                        Object obj = this.paramTable.get(name);
                        if(obj == null) {
                            this.paramTable.put(name, value);
                        } else {
                            ArrayList valueList;
                            if(obj instanceof String) {
                                valueList = new ArrayList();
                                valueList.add(obj);
                                valueList.add(value);
                                this.paramTable.put(name, valueList);
                            } else {
                                valueList = (ArrayList)obj;
                                valueList.add(value);
                            }
                        }
                    }
                }
            }
        } finally {
            reader.close();
        }

    }
}
