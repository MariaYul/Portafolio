package com.telcel.gsrh.solicitudcurso.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTagLib extends SimpleTagSupport {
    
    public static final int STEPS = 5;
    private String uri;
    private int offset;
    private int count;
    private int max = STEPS;
    private String previous = "Previous";
    private String next = "Next";
    
    private Writer getWriter() {
        return getJspContext().getOut();
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        Writer writer = getWriter();
        
        try {
            writer.write("<nav>");
            writer.write("<ul class=\"pagination\">");
            
            //Cuando se está en la primer página
            if(offset < STEPS) {
                writer.write(constructLink(1, previous, "disabled", true));
            } else {
                writer.write(constructLink(offset - STEPS, previous, null, false));
            }
            
            
            for(int itr = 0; itr < count; itr += STEPS) {
                if(offset == itr) {
                    writer.write(constructLink((itr/STEPS+1)-1 *STEPS, String.valueOf(itr/STEPS+1), "active", true));
                } else {
                    writer.write(constructLink(itr/STEPS*STEPS, String.valueOf(itr/STEPS+1), null , false));
                }
            }
            
            //Cuando está en la última página
            if(offset + STEPS >= count) {
                writer.write(constructLink(offset + STEPS, next, "disabled", true));
            } else {
                writer.write(constructLink(offset + STEPS, next, null , false));
            }
            
            writer.write("</ul>");
            writer.write("</nav>");
        } catch(Exception e) {
            throw new JspException("Error en el tag Paginator", e);
        }
    }
    
    private String constructLink(int page, String text, String className, boolean disabled) {
        StringBuilder link = new StringBuilder("<li");
        
        if(className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        
        link.append(">");
        
        if(disabled) {
            link.append("<a href=\"#\">");
            link.append(text);
            link.append("</a>");
        } else {
            link.append("<a href=\"javascript:paginarResultados(");
            link.append(page);
            link.append(")");
            link.append("\">");
            link.append(text);
            link.append("</a>");
        }
        
        link.append("</li>");
        
        return link.toString();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
