package hiveCommon;

import java.util.HashMap;

public class VariableInj {
    HashMap<Object,Object> inj;

    public VariableInj()
    {
       inj = new HashMap<>();
    }

    public void setVar(Object key, Object value)
    {
        inj.put(key,value);
    }

    public Object getVar(Object key)
    {
        return inj.get(key);
    }

}
