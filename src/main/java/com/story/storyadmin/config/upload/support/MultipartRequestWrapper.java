package com.story.storyadmin.config.upload.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

public class MultipartRequestWrapper {

    private HttpServletRequest request;

    public MultipartRequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isMultipartRequest() {
        return (this.request != null)
                && (this.request instanceof MultipartHttpServletRequest || this.request instanceof StandardMultipartHttpServletRequest);
    }

    public MultipartHttpServletRequest getRequest() {
        return (MultipartHttpServletRequest) request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Return the value of the given name, if any.
     * @param name the name of the attribute
     * @return the current attribute value, or {@code null} if not found
     */
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    /**
     * Set the value of the given name, replacing an existing value (if any).
     * @param name the name of the attribute
     * @param value the value for the attribute
     */
    // void setAttribute(String name, Object value) {
    // map.put(name, value);
    // }

    /**
     * Remove the attribute of the given name, if it exists.
     * <p>
     * Note that an implementation should also remove a registered destruction callback
     * for the specified attribute, if any. It does, however, <i>not</i> need to
     * <i>execute</i> a registered destruction callback in this case, since the object
     * will be destroyed by the caller (if appropriate).
     * @param name the name of the attribute
     */
    // void removeAttribute(String name) {
    // map.remove(name);
    // }

}
