package com.telus.csm.ewlnsc.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class RecursiveToStringStyle extends ToStringStyle {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7457738932029464211L;

	private static final int    INFINITE_DEPTH  = -1;

    /**
     * Setting {@link #maxDepth} to 0 will have the same effect as using original {@link #ToStringStyle}: it will
     * print all 1st level values without traversing into them. Setting to 1 will traverse up to 2nd level and so
     * on.
     */
    private int                 maxDepth;

    private int                 depth;

    public RecursiveToStringStyle() {
        this(INFINITE_DEPTH);
    }

    public RecursiveToStringStyle(int maxDepth) {
    	
        super();
        this.setContentStart("[");
        this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
        this.setFieldSeparatorAtStart(true);
        this.setContentEnd(SystemUtils.LINE_SEPARATOR + "]");


        setUseShortClassName(true);
        setUseClassName(true);
        setUseIdentityHashCode(false);
//        setFieldSeparator(",");
//        setFieldSeparatorAtEnd(false);
        setArrayContentDetail(true);
//        setContentStart("["); 
//        setContentEnd("]");
        
        this.maxDepth = maxDepth;
     }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
    	
    	if (value == null) {
    		return;
    	}
    	
    	String className = value.getClass().getName();
    	
        if (className.startsWith("java.lang.")
        		|| ("java.sql.Timestamp".equals(className))
        		|| ("java.util.Date".equals(className))
        		|| ("java.math.BigInteger".equals(className))
                || (maxDepth != INFINITE_DEPTH && depth >= maxDepth)) {
 
        	buffer.append(value);
        } else {        	
            depth++;
            String x = ReflectionToStringBuilder.toString(value, this);

            x = x.replaceAll(SystemUtils.LINE_SEPARATOR, SystemUtils.LINE_SEPARATOR+"    ");
            buffer.append(x);
            depth--;
        }
    }

    // Format Collection
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll) {
         depth++;
         buffer.append(getArrayStart());
         String seperator = "";
         for (Iterator<?> iterator = coll.iterator(); iterator.hasNext();) {
        	buffer.append(seperator);
        	seperator = ",";
			Object value = (Object) iterator.next();
 			appendDetail(buffer, fieldName, value);
		}
         buffer.append(getArrayEnd());
         depth--;
    }

    // Format Map
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Map<?,?> map) {
         depth++;
         buffer.append("<");
         String seperator = "";
         Set<?> keySet = map.keySet();
         for (Object key : keySet) {
         	buffer.append(seperator);
         	seperator = ",";

			buffer.append(key.toString() + "=");
			appendDetail(buffer, fieldName, map.get(key));
		}
         buffer.append(">");
         depth--;
    }
     
}