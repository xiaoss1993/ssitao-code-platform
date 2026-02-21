
package com.ssitao.code.frame.mybatisflex.annotation;

/**
 * 空监听器。
 */
public final class NoneListener implements InsertListener, UpdateListener, SetListener {

    @Override
    public void onInsert(Object entity) {
        // do nothing here.
    }

    @Override
    public void onUpdate(Object entity) {
        // do nothing here.
    }

    @Override
    public Object onSet(Object entity, String property, Object value) {
        return value;
    }

}
