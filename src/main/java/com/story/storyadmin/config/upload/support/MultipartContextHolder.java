package com.story.storyadmin.config.upload.support;

import org.springframework.core.NamedThreadLocal;

public class MultipartContextHolder {
    private static final ThreadLocal<MultipartRequestWrapper> requestAttributesHolder = new NamedThreadLocal<MultipartRequestWrapper>(
            "Multipart Special Use");

    /**
     * Reset the MultipartRequestWrapper for the current thread.
     */
    public static void resetRequestWrapper() {
        requestAttributesHolder.remove();
    }

    /**
     * Bind the given MultipartRequestWrapper to the current thread, <i>not</i> exposing
     * it as inheritable for child threads.
     * @param request the MultipartRequestWrapper to expose
     */
    public static void setRequestWrapper(MultipartRequestWrapper request) {
        requestAttributesHolder.set(request);
    }

    /**
     * Return the MultipartRequestWrapper currently bound to the thread.
     * @return the MultipartRequestWrapper currently bound to the thread, or {@code null}
     * if none bound
     */
    public static MultipartRequestWrapper getRequestWrapper() {
        MultipartRequestWrapper request = requestAttributesHolder.get();
        return request;
    }

    /**
     * Return the MultipartRequestWrapper currently bound to the thread.
     * <p>
     * @return the MultipartRequestWrapper currently bound to the thread
     * @throws IllegalStateException if no MultipartRequestWrapper object is bound to the
     * current thread
     */
    public static MultipartRequestWrapper currentRequestAttributes()
            throws IllegalStateException {
        MultipartRequestWrapper request = getRequestWrapper();
        if (request == null) {
            // throw new IllegalStateException("No thread-bound request found");
        }
        return request;
    }
}
